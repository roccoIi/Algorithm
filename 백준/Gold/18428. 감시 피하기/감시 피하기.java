import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static char map[][];
	static boolean answer;
	static ArrayList<Node> TList = new ArrayList<>();
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		
        N = Integer.parseInt(br.readLine());
        map = new char[N+1][N+1];
        Node start = null;
        
        for(int r = 1; r <= N; r++) {
        	String[] temp = br.readLine().split(" ");
        	for(int c = 1; c <= N; c++) {
        		map[r][c] = temp[c-1].charAt(0);
        		
        		if(map[r][c] == 'T') TList.add(new Node(r, c));
        		if(start == null && map[r][c] == 'X') start = new Node(r, c);
        	}
        }
        
        System.out.println(dfs(start.r, 0) ? "YES" : "NO");
	}
	
	static boolean dfs(int row, int count) {
		if(count >= 3) {
			if(answer) return true;
			return answer = play();
		}
		
		for(int r = row; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				if(map[r][c] != 'X') continue;
				
				map[r][c] = 'O';
				dfs(r, count + 1);
				map[r][c] = 'X';
			}
		}
		
		return answer;
	}
	
	static boolean play() {
		for(Node curr : TList) {
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				while(checkBoundary(nr, nc)) {
					if(map[nr][nc] == 'S') return false;
					else if(map[nr][nc] == 'O') break;
					
					nr += dir[0][d];
					nc += dir[1][d];
				}
			}
		}
		
		return true;
	}
	
	// 범위 내 존재할때만 True 반환
	static boolean checkBoundary(int r, int c) {
		return r > 0 && r <= N && c > 0 && c <= N;
	}
}