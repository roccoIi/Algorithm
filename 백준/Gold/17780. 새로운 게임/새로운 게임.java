import java.io.*;
import java.util.*;


public class Main {
	static Deque<Integer>[][] order;
	static int[][] dir = {{0, 0, 0, -1, 1}, {0, 1, -1, 0, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] pieces = new int[K][3];
		
		for(int r = 0; r < K; r++) {
			pieces[r] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		}
		
		// 각 좌표별로 올라가있는 원판의 순서 저장할 2차원 배열
		order = new Deque[N+1][N+1];
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				order[r][c] = new ArrayDeque<>();
			}
		}
		
		System.out.println(mainSolution(N, K, map, pieces));
	}
	
	static int mainSolution(int N, int K, int[][] map, int[][] pieces) {
		int turn = 0;
		
		for(int i = 0; i < pieces.length; i++) {
			int r = pieces[i][0];
			int c = pieces[i][1];
			order[r][c].add(i);
		}
		
		while(turn++ <= 1000) {
			for(int i = 0; i < K; i++) {
				// 해당 말이 보드판 맨 아래에 위치하는지 먼저 확인해야한다.
				int r = pieces[i][0];
				int c = pieces[i][1];
				
				if(order[r][c].peek() != i) continue;
				
				if(movePiece(map, pieces, i)) return turn;
			}
		}

		return -1;
	}
	
	static boolean movePiece(int[][] map, int[][] pieces, int index) {
		// 현재 좌표
		int r = pieces[index][0];
		int c = pieces[index][1];
		int direction = pieces[index][2];
		
		// 이동하게될 좌표
		int nr = r + dir[0][direction];
		int nc = c + dir[1][direction];
		
		// 해당방향으로 진행 가능할경우
		if(isPossible(map.length - 1, map, nr, nc)) {
			
			// 이동
			redOrWhite(map, pieces, r, c, nr, nc);
			
			// 모두 한곳에 쌓여있으면 당장 끝낸다.
			if(order[nr][nc].size() >= 4) return true;
		}
		
		// 불가능할경우 (파랑을 만났거나 벽을 만났다)
		else {
			
			// 새로운 방향 갱신
			direction = direction % 2 == 0 ? direction - 1 : direction + 1;
			
			// 새로 이동하게될 좌표 갱신
			nr = r + dir[0][direction];
			nc = c + dir[1][direction];
			
			if(isPossible(map.length - 1, map, nr, nc)) {
				redOrWhite(map, pieces, r, c, nr, nc);
				if(order[nr][nc].size() >= 4) return true;
			}
			
			// 바뀐 위치와 방향은 저장한다.
			pieces[index][2] = direction;
		}
		
		return false;
	}

	static void redOrWhite(int[][] map, int[][] pieces, int r, int c, int nr, int nc) {
		//빨강일때
		if(map[nr][nc] == 1) { 
			while(!order[r][c].isEmpty()) {
				int index = order[r][c].pollLast();
				pieces[index][0] = nr;
				pieces[index][1] = nc;
				
				order[nr][nc].add(index);
			}	
		} 
		
		// 하양일때
		else { 
			while(!order[r][c].isEmpty()) {
				int index = order[r][c].pollFirst();
				pieces[index][0] = nr;
				pieces[index][1] = nc;
				
				order[nr][nc].add(index);
			}
		}
	}
	
	
	static boolean isPossible(int N, int[][] map, int r, int c) {
		return r > 0 && c > 0 && r <= N && c <= N && map[r][c] != 2;
	}
	
}