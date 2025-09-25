import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int r, c, d;
		
		Node(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int R, C, map[][];
	static boolean visited[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		visited = new boolean[R+1][C+1];
		for(int r = 1; r <= R; r++) {
			String str = br.readLine();
			for(int c = 1; c <= C; c++) {
				map[r][c] = str.charAt(c-1) - '0';
			}
		}
				
		System.out.println(bfs(new Node(1, 1, 1)));
		
	}
	
	public static int bfs(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		visited[start.r][start.c] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			if(curr.r == R && curr.c == C) return curr.d;
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				if(!checkBoundary(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
				
				q.add(new Node(nr, nc, curr.d+1));
				visited[nr][nc] = true;
				
			}
		}
		
		return -1;
	}
	
	public static boolean checkBoundary(int r, int c) {
		return r > 0 && r <= R && c > 0 && c <= C;
	}
}