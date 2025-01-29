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
	static int R, C, N, bombCnt[][];
	static char map[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static ArrayList<Node> bombList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		bombCnt = new int[R][C];
		
		map = new char[R][C];
		for(int r = 0; r < R; r++) {
			String temp = br.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = temp.charAt(c);
				if(map[r][c] == 'O') bombCnt[r][c] = 1;
			}
		}
		
		if(N == 1) {
			System.out.print(print());
			return;
		}
				
		switch(N % 4) {
			case 0: case 2:
				addBomb(1);
				break;	
			case 1:
				addBomb(2);
				explodeBomb(1);
				addBomb(1);
				explodeBomb(2);
				break;
			case 3:
				addBomb(2);
				explodeBomb(1);
				break;	
		}

		System.out.println(print());
	}
	
	static void addBomb(int idx) {
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == '.') {
					bombCnt[r][c] = idx;
					map[r][c] = 'O';
				}
			}
		}
	}
	
	static void explodeBomb(int idx){
		boolean[][] visited = new boolean[R][C];
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(bombCnt[r][c] != idx) continue;
				map[r][c] = '.';
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dir[0][d];
					int nc = c + dir[1][d];
					
					if(!checkBoundary(nr, nc) || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					map[nr][nc] = '.';
				}
			}
		}
	}
	
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
	
	static StringBuilder print() {
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				sb.append(map[r][c]);
			}
			sb.append('\n');
		}
		return sb;
	}
}