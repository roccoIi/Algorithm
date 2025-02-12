import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c;
		int virusNum;
		int time;
		
		Node(int r, int c, int virusNum, int time){
			this.r = r;
			this.c = c;
			this.virusNum = virusNum;
			this.time = time;
		}
	}
	static int R, C, map[][], answer[];
	static boolean[][] visited;
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static Queue<Node> checkListQ = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		answer = new int[4];
		
		map = new int[R][C];
		Queue<Node> q = new LinkedList<>();
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 0) {
					q.add(new Node(r, c, map[r][c], 0));
				}
				if(map[r][c] != 0) visited[r][c] = true;
			}
		}
		
		spreadVirus(q);
		
		for(int i = 1; i <= 3; i++) {
			sb.append(answer[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void spreadVirus(Queue<Node> q) {
		boolean[][] tempVisited = new boolean[R][C];
		Queue<Node> list = new LinkedList<>();
		int nowTime = 0;
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			if(curr.time > nowTime) {
				nowTime = curr.time;
				while(!list.isEmpty()) {
					Node tmp = list.poll();
					visited[tmp.r][tmp.c] = true; 
				}
			}
			
			answer[map[curr.r][curr.c]]++;
			if(map[curr.r][curr.c] == 3) continue;
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				// 범위를 벗어나거나, 이미 방문했거나, 백신이 있으면 continue;
				if(!checkBoundary(nr, nc) || visited[nr][nc] || map[nr][nc] == -1) continue;
				
				if(map[nr][nc] == 0 || (map[nr][nc] == 1 && curr.virusNum == 2) || (map[nr][nc] == 2 && curr.virusNum == 1))
					map[nr][nc] += curr.virusNum;
				
				if(tempVisited[nr][nc]) continue;
				tempVisited[nr][nc] = true;
				q.add(new Node(nr, nc, curr.virusNum, curr.time + 1));
				list.add(new Node(nr, nc, curr.virusNum, curr.time + 1));	
				
			}	
		}
	}
	
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}