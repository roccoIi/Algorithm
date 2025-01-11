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
	static int R, C, answer, map[][];
	static boolean[][] visited;
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static HashMap<Integer, Integer> group = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		ArrayList<Node> zeroList = new ArrayList<>();
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 0) zeroList.add(new Node(r, c));
			}
		}
		
		answer = -1;
		int index = 2;
		visited = new boolean[R][C];
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 1) {
					findShapeGroup(new Node(r, c), index++);
				}
			}
		}
		
		for(Node node : zeroList) {
			HashSet<Integer> set = new HashSet<>();
			int count = 1;
			for(int d = 0; d < 4; d++) {
				int nr = node.r + dir[0][d];
				int nc = node.c + dir[1][d];
				
				if(!checkBoundary(nr, nc) || map[nr][nc] == 0) continue;
				
				// 해당 위치에서 동일한 그룹넘버의 모양을 추가했는지 set으로 캐시
				int groupNum = map[nr][nc];
				if(!set.contains(groupNum)) {
					count += group.get(groupNum);
					set.add(groupNum);
				}
			}
			answer = answer < count ? count : answer;
		}
		System.out.println(answer);	
	}

	/**
	 * 이어져있는 모양을 찾고 해당 모양에 동일한 그룹넘버를 부여한다. (2부터 시작)
	 * @param start, index, list
	 */
	static void findShapeGroup(Node start, int index) {
		Queue<Node> q = new LinkedList<>();
		visited[start.r][start.c]= true; 
		map[start.r][start.c]= index; // 현재 시작점의 그룹넘버부터 변경 
		q.add(start);
		
		int count = 1; // 현재 인덱스 그룹에 총 몇칸이 있는지 카운팅
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				if(!checkBoundary(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
				visited[nr][nc] = true;
				
				// 1이라면 카운트를 올리고 해당 위치에 인덱스 번호 부여, 큐에 넣는다.
				count++;
				map[nr][nc] = index;
				q.add(new Node(nr, nc));
			}
		}
		group.put(index, count);
	}
	
	/**
	 * 현재 좌표값이 경계를 벗어났는지 확인
	 * @param r
	 * @param c
	 * @return true/false
	 */
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}