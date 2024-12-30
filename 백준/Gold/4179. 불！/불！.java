import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char map[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static Queue<Node> fire = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Node startNode = null;
		
		map = new char[R][C];
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				
				// J(지훈)거나, F(불)일 경우 해당 위치 저장
				if(map[r][c] == 'J') startNode = new Node(r, c);
				else if(map[r][c] == 'F') fire.add(new Node(r, c));
			}
		}
		
		BFS(startNode);
		
		// sb에 아무런 결과값이 없을 경우 탈출실패로 간주, IMPOSSIBLE 출력
		if(sb.length() == 0) sb.append("IMPOSSIBLE");
		
		System.out.println(sb);
	}
	
	static void BFS(Node start) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		q.add(start);
		visited[start.r][start.c]= true;
		int timeCnt = 0;
		
		while(!q.isEmpty()) {
			// 0) 시간 증가
			timeCnt++;
			
			// 1) 불을 확산시킨다.
			int fireCnt = fire.size();
			for(int i = 0; i < fireCnt; i++) {
				Node oneFire = fire.poll();
				for(int d = 0; d < 4; d++) {
					int nr = oneFire.r + dir[0][d];
					int nc = oneFire.c + dir[1][d];
					
					// 경계를 넘어가지 않고, 이미 불타고 있지 않고, 벽이 아닐경우에만 확산
					if(check(nr, nc) && map[nr][nc] != '#' && map[nr][nc] != 'F') {
						map[nr][nc] = 'F';
						fire.add(new Node(nr, nc));
					}
				}
			}
			
			// 2) 지훈이를 이동시킨다.
			int hoonSize = q.size();
			for(int i = 0; i < hoonSize; i++) {
				Node curr = q.poll();
				for(int d = 0; d < 4; d++) {
					int nr = curr.r + dir[0][d];
					int nc = curr.c + dir[1][d];
					
					// 경계를 벗어났다면 탈출에 성공했다.
					if(!check(nr, nc)) {
						sb.append(timeCnt);
						return;
					}
					
					// 경계를 벗어나지 않고, 방문기록이 없고, 방문가능한곳(.) 일때
					if(check(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
						visited[nr][nc] = true;
						q.add(new Node(nr, nc));
					}	
				}
			}	
		}	
	}
	
	// 경계를 벗어나지 않을때만 true 반환.
	static boolean check(int r, int c) {
		return r < R && c < C && r >= 0 && c >=0 ;
	}
	
}