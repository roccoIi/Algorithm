import java.io.*;
import java.util.*;

public class Main {
	static class Token{
		int r, c, direction;
		
		Token(int r, int c, int direction){
			this.r = r;
			this.c = c;
			this.direction = direction;
		}
	}
	static int[][] dir = {{0, 0, 0, -1, 1}, {0, 1, -1, 0, 0}};
	static int N, K, map[][],up[];
	static Token tokens[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[][] lineUp = new ArrayList[N+1][N+1];
		up = new int[K+1];
		int phase = 0;
		
		// 1) 체스말들이 쌓여있는 순서를 저장할 리스트 2차원 배열 초기화
		// 2) 지도정보 입력받기
		map = new int[N+1][N+1];
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				lineUp[r][c] = new ArrayList<>();
			}
		}
		
		// 말의 정보 입력받기 (지도좌표, 방향)
		tokens = new Token[K+1];
		for(int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			tokens[i] = new Token(r, c, dir);
			lineUp[r][c].add(i);
		}
		
		// 체스판 이동 시작
		while(++phase <= 1000) {
			
			// 1번 말부터 이동을 시작한다.
			for(int i = 1; i <= K; i++) {
				Token curr = tokens[i];
				
				// 해당 말의 새로운 위치를 생성한다.
				int nr = curr.r + dir[0][curr.direction];
				int nc = curr.c + dir[1][curr.direction];

				// 1) 파란색 벽을 마주했거나 경계면을 마주했을 경우 방향을 재설정한다.
				if(!checkBoundary(nr, nc) || map[nr][nc] == 2) {
					// [BLUE] 홀수일경우 +1, 짝수일경우 -1을 통해서 방향을 제어한다.
					curr.direction = (curr.direction & 1) == 0 ? curr.direction - 1 : curr.direction + 1;
					nr = curr.r + dir[0][curr.direction];
					nc = curr.c + dir[1][curr.direction];
				}

				// 1 - 1) 재설정했음에도 불구하고 이동할 수 없다면 그 자리에 위치시킨다.
				if(!checkBoundary(nr, nc) || map[nr][nc] == 2) {
					nr = curr.r;
					nc = curr.c;
					
				// 2) 빨간색 벽을 마주했을 경우 리스트를 반전시킨 후 새로운 리스트와 합한다.
				} else if (map[nr][nc] == 1) {
					ArrayList<Integer> nowList = removeList(lineUp, curr.r, curr.c, i);
					moveToken(nowList, nr, nc, curr.direction);
					lineUp[nr][nc] = addList(reverse(nowList), lineUp[nr][nc]);
				
				// 3) 빈 벽을 만났을 경우 특이사항 없이 이동한다.
				} else if(map[nr][nc] == 0){
					ArrayList<Integer> nowList = removeList(lineUp, curr.r, curr.c, i);
					moveToken(nowList, nr, nc, curr.direction);
					lineUp[nr][nc] = addList(nowList, lineUp[nr][nc]);
				} 

				// 최종적으로 이동한 벽에 위치한 말들이 4개 이상일 경우 현재 페이즈를 출력하고 종료한다.
				if(lineUp[nr][nc].size() >= 4) {
					System.out.println(phase);
					return;
				}
			}
		}
		
		// 끝까지 출력하지 못했을 경우 종료가 불가능하므로 -1를 출력한다.
		System.out.println("-1");
	}
	
	// [RED] 쌓여있는 말의 순서를 뒤집는다.
	static ArrayList<Integer> reverse (ArrayList<Integer> list){		
		Collections.reverse(list);
		return list;
	}
	
	// [ALL] 해당 좌표가 경계를 벗어났는지 체크한다.(정상일 경우에만 True 반환)
	static boolean checkBoundary(int r, int c) {
		return r > 0 && r <= N && c > 0 && c <= N;
	}
	
	// [ALL] nowList 항목들을 newList 위에 추가한다.
	static ArrayList<Integer> addList (ArrayList<Integer> nowList, ArrayList<Integer> newList){
		for(int i = 0; i < nowList.size(); i++) {
			newList.add(nowList.get(i));
		}
		return newList;
	}
	
	// [ALL] 현재 idx 말이 다른 말 위에 올라가 있을 경우 따로 분리한다.
	//       기존 위치에선 말들을 제거하고, 나머지 말들의 리스트를 따로 반환한다.
	static ArrayList<Integer> removeList(ArrayList<Integer>[][] nowMap, int r, int c, int targetIdx){
		// 필요한 객체 생성
		ArrayList<Integer> answer = new ArrayList<>();
		int size = nowMap[r][c].size();
		boolean find = false;
		int nowIdx = 0;
		
		for(int i = 0; i < size; i++) {
			if(find || nowMap[r][c].get(nowIdx) == targetIdx) {
				answer.add(nowMap[r][c].remove(nowIdx));
				if(!find) find = true;
			} else {
				nowIdx++;
			}
		}
		return answer;
	}
	
	// [ALL] 토큰들의 좌표를 수정한다.
	//       가장 아래에 있는 토큰(방금 방향이 재설정된 토큰)만 새로운 방향으로 생성하고
	//       이후의 토큰들은 본래의 방향으로 설정한다.
	static void moveToken(ArrayList<Integer> moveList, int nr, int nc, int dir) {
		for(int i = 0; i < moveList.size(); i++) {
			if(i == 0) tokens[moveList.get(i)] = new Token(nr, nc, dir); 
			else tokens[moveList.get(i)] = new Token(nr, nc, tokens[moveList.get(i)].direction);
		}
	}
}