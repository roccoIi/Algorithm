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
	static int N, M, map[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int cheeseCnt = 0;
		int time = 0;
		
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) cheeseCnt++;
			}
		}
		
		while(true) {
			// list는 삭제할 치즈 리스트다.
			ArrayList<Node> list = bfs(new Node(0, 0));
			cheeseCnt -= list.size();
			
			// 리스트에 있는 치즈들을 제거한다.
			while(list.size() > 0) {
				Node node = list.remove(0);
				map[node.r][node.c] = 0; 
			}
			
			// 시간 증가
			time++;
			
			// 더 이상 남아있는 치즈가 없다면 종료
			if(cheeseCnt <= 0) break;
		}
		
		if(sb.length() == 0) sb.append(time);
		System.out.println(sb);
	}
	
	static ArrayList<Node> bfs(Node start) {
		boolean[][] visited = new boolean[N][M]; // 방문체크 배열
		int[][] countMap = new int[N][M]; // 공기과 접촉한 횟수 배열
		ArrayList<Node> list = new ArrayList<>(); // 녹아 없어지는 치즈 리스트
		Queue<Node> q = new LinkedList<>(); // bfs를 위한 큐
		
		visited[start.r][start.c] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				if(!checkBoundary(nr, nc) || visited[nr][nc]) continue;
						
				// 해당 위치에 치즈가 있다면 공기 접촉횟수를 판단하여
				// 2회 이상일 경우 녹아 없어지는 치즈 리스트에 추가
				// 치즈가 아닐경우에는 방문체크 후 큐에 추가
				if(map[nr][nc] == 1) {
					if(++countMap[nr][nc] == 2) list.add(new Node(nr, nc));
				} else {
					visited[nr][nc] = true;
					q.add(new Node(nr, nc));
				}
			}
		}
		return list;
	}
	
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}