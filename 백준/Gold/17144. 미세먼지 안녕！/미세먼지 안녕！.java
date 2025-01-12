import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int R, C, T, map[][], airRLocation;
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		airRLocation = -1;
		Queue<Node> dustList = new LinkedList<>();
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 4) dustList.add(new Node(r, c));
				else if(map[r][c] == -1) airRLocation = r;
			}
		}
		
		for(int t = 0; t < T; t++) {
			// 1) 먼지 확산
			spreadDust(dustList);
			
			// 2) 공기청정기 작동
			moveAir();
			
			// 3) 먼지 좌표 수집 (5 이상만, 그 이하는 먼지가 확산되지 않음)
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(map[r][c] > 4) dustList.add(new Node(r, c));
				}
			}
		}
		
		int dustCount = 0;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] > 0) dustCount += map[r][c];
			}
		}	
		System.out.println(dustCount);
	}
	
	static void moveAir() {
		
		// 에어컨 상단부분 순환 
		for(int r = airRLocation - 3; r >= 0; r--) {
			map[r+1][0] = map[r][0];
		}
		
		for(int c = 1; c < C; c++) {
			map[0][c-1] = map[0][c];
		}
		
		for(int r = 0; r < airRLocation - 1; r++) {
			map[r][C-1] = map[r+1][C-1];
		}
		
		for(int c = C-2; c > 0; c--) {
			map[airRLocation - 1][c+1] = map[airRLocation - 1][c];
		}
		map[airRLocation - 1][1] = 0;
	
		// 에어컨 하단부분 순환
		for(int r = airRLocation + 2; r < R; r++) {
			map[r-1][0] = map[r][0];
		}
		
		for(int c = 1; c < C; c++) {
			map[R-1][c-1] = map[R-1][c];
		}
		
		for(int r = R - 2; r >= airRLocation; r--) {
			map[r+1][C-1] = map[r][C-1];
		}
		
		for(int c = C-2; c > 0; c--) {
			map[airRLocation][c+1] = map[airRLocation][c];
		}
		map[airRLocation][1] = 0;
	}
	
	static void spreadDust(Queue<Node> list) {
		int[][] tmpMap = new int[R][C];
		
		while(!list.isEmpty()) {
			Node curr = list.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				// 새로운 좌표가 경계를 벗어나거나, 공기청정기와 만났다면 패쓰
				if(!checkBoundary(nr, nc) || checkAir(nr, nc)) continue;
				
				// 미세먼지를 5로 나누고, 주변에 퍼뜨린다.
				int divideDust = map[curr.r][curr.c] / 5; 
				tmpMap[curr.r][curr.c] -= divideDust;
				tmpMap[nr][nc] += divideDust;
			}
		}
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				map[r][c] += tmpMap[r][c];
			}
		}
	}
	
	// 경계조건 확인
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
	
	// 에어컨이 위치한 곳인지 확인
	static boolean checkAir(int r, int c) {
		return c == 0 && (r == airRLocation || r == (airRLocation - 1));
	}
}