import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * [접근]
 * 
 * 1. 각 배열을 입력받으면서 가장 맛있는 정도가 높은 maxTaste를 구했다.
 * 2. 최소 맛 (0)부터 maxTaste까지 돌면서 가장 덩어리가 많을때를 구한다.
 * 3. 이때 for문 안에서 bfs를 돌며 for문의 index보다 큰 숫자(맛)의 방문배열에 true를 한다.
 * 	  (방문이 false인 곳만 들어가서 탐색 할 예정이다.)
 * 4. bfs가 끝났다는 것은 한 덩어리를 모두 true로 바꿨다는 의미이다. 계속 탐색하면서 false를 찾는다.
 * 5. 그렇게 주어진 배열 모두를 탐색했을 때 실행된 bfs의 수가 덩어리의 수이다.
 * 
 * */

class Node {
	int r;
	int c;
	
	Node(int r, int c){
		this.r = r;
		this.c = c;
	}
}

public class Solution {
	static int N;
	static boolean[][] visit;
	static int[][] arr;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= testCase; T++) {
			int maxCheese = 0;
			int maxTaste = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			// 주어진 배열을 입력받으면서 최대 맛(maxTaste)를 구한다.
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());
					arr[r][c] = num;
					if(num > maxTaste) maxTaste = num;
				}
			}
			
			// 최소 맛(0)부터 최대 맛(maxTaste)까지 돌면서 덩어리를 확인한다.
			for(int taste = 0; taste <= maxTaste; taste++) {
				visit = new boolean[N][N];
				int count = 0;
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						if(arr[r][c] > taste && !visit[r][c]) {
							bfs(taste, r, c);
							count++;
						}
					}
				}
				if(count > maxCheese) maxCheese = count;
			}
			
			System.out.printf("#%d %d\n", T, maxCheese);
		}
		
		
	}
	static void bfs(int taste, int rPoint, int cPoint) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(rPoint, cPoint));
		visit[rPoint][cPoint] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 경계를 벗어나지 않고, 기준이 되는 맛보다 크면서 방문하지 않았다면 큐에 넣는다.
				if(check(nr, nc) && arr[nr][nc] > taste && !visit[nr][nc]) {
					visit[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
	}
	
	// 경계값 조건
	static boolean check(int r, int c) {
		return r >=0 && c >=0 && r < N && c < N;
	}
}