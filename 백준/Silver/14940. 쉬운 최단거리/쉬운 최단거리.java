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
	static int N, M, dist[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        dist = new int[N][M];
        visited = new boolean[N][M];
        Node start = null;
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		dist[r][c] = Integer.parseInt(st.nextToken());
        		if(dist[r][c] == 2) {
        			dist[r][c] = 0;
        			start = new Node(r, c);
        		}
        	}
        }
        
        bfs(start);
        
        for(int r = 0; r < N; r++) {
        	for(int c = 0; c < M; c++) {
        		if(r == start.r && c == start.c) sb.append(0).append(" ");
        		else sb.append(!visited[r][c] && dist[r][c] != 0 ? -1 : dist[r][c]).append(" ");
        	}
        	sb.append('\n');
        }
        
        System.out.println(sb);
	}
	
	static void bfs(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		visited[start.r][start.c] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				if(checkBoundary(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				
				if(dist[nr][nc] == 0) continue;		
				dist[nr][nc] += dist[curr.r][curr.c];
				q.add(new Node(nr, nc));
			}
		}
	}
	
	// 경계를 벗어나면 true
	static boolean checkBoundary(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}