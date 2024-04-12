import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * [회고]
 * 1. 키를 주울때마다 3차원 배열에 한칸씩 올려가며 방문배열을 체크하려고 했으니 각 문마다 맞는 키를 일치시켜야하는 것이 문제였다.
 * 2. 두번째로 1-1 bfs 진행과정에서 얻은 키가 1-2의 진행과정에 놓여있는 bfs에서 본인이 획득한 키인줄 알고 문을 열어버리는 문제가 생겼다.(1-1, 1-2는 예시, 한 사이클 여러 분기)
 * 3. 각 사이클별, 각 분기별 가지고 있는 키의 종류와 개수를 각각 체크해줘야 된다고 생각했다.
 * 4. Node 클래스 안에 인벤토리 배열을 생성하여 각 노드별 인벤토리를 따로 들고다니도록 구성했다.
 * 5. 그 전 static으로 관리했을 때는 여러 분기들이 섞여 관리할 수 없었다.
 * 6. ** 키를 획득하고 큐에 넣을때 기존 minsik.inventory에 넣어버리면 4방탐색이 끝나기도전에 업데이트되어 섞이는 문제가 발생했다.
 *		그러므로 키를 획득할땐 배열을 복사하여 해당 키를 넣고 복사한 배열을 큐에 넣는 방식으로 진행하였다.**
 * 7. 이후는 똑같다.
 * --------------------------------------------------------------------
 * 8. 아래 테스트케이스를 넣었을때 서로 다른 키를 획득했음에도 불구하고 받은 키의 갯수가 같아 중복방문처리되는 문제가 발생했다.
 * 9. 이를 해결하기위해 visit 3차원배열의 마지막값을 받은 키의 갯수 -> 키들의 해시값 으로 변경하여 키별 중복이 발생하지 않도록 처리했다.
4 3
a0b
#A#
#B#
#1#
 * */
public class Main {
	static int N, M, minMove;
	static int[][] move = {{-1, 1, 0, 0},
						   {0, 0, -1, 1}};
	static char[][] map;
	static boolean[][][] visit;
	static Map<Character, Integer> keyIndex = new HashMap<>() {{
		put('A', 0); // 각 문, 열쇠별 인덱스 저장 (inventory 배열을 관리하기 위하여)
		put('B', 1);
		put('C', 2);
		put('D', 3);
		put('E', 4);
		put('F', 5);
	}};
	
	static class Node{
		int r, c, movement, keyCount;
		int[] inventory = new int[6]; // 각 사람별 들고있는 키는 서로 다르다.
		int code = hash(inventory);

		public Node(int r, int c, int movement, int keyCount, int[] inventory) {
			this.r = r;
			this.c = c;
			this.movement = movement;
			this.keyCount = keyCount;
			this.inventory = inventory;
		}
		
		boolean checkKey(char door) { // 해당하는 키가 있는지 확인
			if(inventory[keyIndex.get(door)] > 0) return true;
			return false;
		}
		
		int hash(int[] inventory) {
			int sum = 0;
			for(int i = 0; i < inventory.length; i++) {
				sum += inventory[i] * (1<<i);
			}
			return sum;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][(int) (Math.pow(2, 6)+1)]; // 0: 아무 키도 없을 때 ~ 6: 최대 6개 키를 얻었을 때
		minMove = Integer.MAX_VALUE;
		Node minsik = null;
		
		for(int r = 0; r < N; r++) {
			String str = br.readLine();
			for(int c = 0; c < M; c++) {
				char tmp = str.charAt(c);
				map[r][c] = tmp;
				if(tmp == '0') minsik = new Node(r, c, 0, 0, new int[6]); // new int[6]은 그냥 배열 초기화와 동시에 넣어준다.
			}
		}
	
		bfs(minsik);	
		System.out.println(minMove == Integer.MAX_VALUE? -1 : minMove);
		// 끝!
	}

	static void bfs(Node start) {
		Queue<Node> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			Node minsik = q.poll();			
			
			for(int d = 0; d < 4; d++) {
				int nr = minsik.r + move[0][d];
				int nc = minsik.c + move[1][d];
				
				// 경계조건 벗어나면 continue, 방문했으면 continue
				if(!check(nr, nc)) continue;
				if(visit[nr][nc][minsik.hash(minsik.inventory)])continue;

				char curr = map[nr][nc];
				
				// 평범한 길은 이동횟수만 증가시킨채 큐에 넣는다. 
				if(curr == '.' || curr == '0') {
					q.offer(new Node(nr, nc, minsik.movement+1, minsik.keyCount, minsik.inventory));
					visit[nr][nc][minsik.hash(minsik.inventory)] = true;
					
				// 키를 주웠다면!!
				} else if(curr == 'a' || curr == 'b' || curr == 'c'|| curr == 'd'|| curr == 'e'|| curr == 'f') {
					// 기존에 있던 키라면 keyCount를 올리지 않고 큐에 넣는다.
					// 만일 새롭게 얻게 된 키라면 KeyCount +1하여 큐에 넣는다.
					// keyCount가 올라갔다면 방문채크도 +1 후 새롭게 시작
					// 키는 확인 후 바로 넣어줘야 큐에 제대로 들어간다. if문 끝나고 넣으면 키 넣지 않은 상태로 들어감
					if(minsik.checkKey((char)(curr - ' '))) { // 아스키코드 소문자-> 대문자 변환은 공백을 뺀다.(-' ')
						q.offer(new Node(nr, nc, minsik.movement+1, minsik.keyCount, minsik.inventory));
						visit[nr][nc][minsik.hash(minsik.inventory)] = true;
					} else {
						// 새롭게 키를 넣은 인벤토리를 저장하기 위해 임시배열에 기존 인벤토리를 복사하고 증가
						// 복사된 배열을 객체에 추가하여 큐에 넣는다.
						int[] tmp = Arrays.copyOf(minsik.inventory, minsik.inventory.length);
						tmp[keyIndex.get((char)(curr - ' '))]++;

						q.offer(new Node(nr, nc, minsik.movement+1, minsik.keyCount+1, tmp));
						visit[nr][nc][minsik.hash(tmp)] = true;
					}
				// 문에 맞는 열쇠가 있다면 열고 들어간다. (객체 내 인벤토리 확인)
				} else if(curr == 'A' || curr == 'B' || curr == 'C'|| curr == 'D'|| curr == 'E'|| curr == 'F') {
					if(minsik.checkKey(curr)) {
						q.offer(new Node(nr, nc, minsik.movement+1, minsik.keyCount, minsik.inventory));
						visit[nr][nc][minsik.hash(minsik.inventory)] = true;
					}
				// 출구에 도착했다면 최솟값 갱신
				} else if(curr == '1') {
					minMove = Math.min(minMove, minsik.movement+1);
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >=0 && c >=0 && r < N && c < M;
	}
}