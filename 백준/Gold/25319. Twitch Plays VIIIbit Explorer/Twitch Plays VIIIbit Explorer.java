import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c;
		String root;
		
		Node(int r, int c, String root){
			this.r = r;
			this.c = c;
			this.root = root;
		}
	}
	static int R, C, S, totalAlpabetNum[], idNum[];
	static char map[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static char[] dirCharAt = {'U', 'D', 'L', 'R'};
	static StringBuilder order = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		totalAlpabetNum = new int[26];
		map = new char[R][C];
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				totalAlpabetNum[changeNum(map[r][c])]++;
			}
		}
		
		// 아이디에 들어가는 각 철자의 개수 저장하는 배열 idNum
		idNum = new int[26];
		String id = br.readLine();
		HashSet<Character> set = new HashSet<>();
		for(int i = 0; i < id.length(); i++) {
			char idChar = id.charAt(i);
			idNum[changeNum(idChar)]++;
			set.add(idChar);
		}
		
		// 초기 좌표값은 가장 왼쪽 위 (0, 0) 이다.
		int[] location = {0, 0};
		int upgrade = 0;
		while(isEnough(set)) {			
			for(int i = 0; i < id.length(); i++) {
				if(map[location[0]][location[1]] == id.charAt(i)) {
					totalAlpabetNum[changeNum(id.charAt(i))]--;
					map[location[0]][location[1]] = '+';
					order.append('P');
					continue;
				}
				location = findAlpabet(new Node(location[0], location[1], ""), id.charAt(i));
				if(location == null) break;
			}
			upgrade++;
		}
		
		// 종료지점을 향해서 이동
		for(int i = 0; i < R - location[0] - 1; i++) {
			order.append('D');
		}
		
		for(int i = 0; i < C - location[1] - 1; i++) {
			order.append('R');
		}
		
		// 정답 출력
		StringBuilder answer = new StringBuilder();
		answer.append(upgrade).append(" ").append(order.toString().length()).append('\n').append(order);
		System.out.println(answer);
	}
	
	// 현재 지도에 ID를 만들 수 있을만큼 알파벳이 충분히 있는가
	static boolean isEnough(HashSet<Character> set) {
		for(char id : set) {
			if(totalAlpabetNum[changeNum(id)] < idNum[changeNum(id)]) return false;
		}
		return true;
	}
	
	// target 알파벳을 찾아 bfs로 탐색한다.
	static int[] findAlpabet(Node node, char target){
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		visited[node.r][node.c] = true; 
		q.add(node);

		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				if(!boundaryCheck(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				
				StringBuilder sb = new StringBuilder(curr.root).append(dirCharAt[d]);
				if(map[nr][nc] == target) {
					order.append(sb).append('P');
					totalAlpabetNum[changeNum(target)]--;
					map[nr][nc] = '+';
					return new int[] {nr, nc};
				} else {
					q.add(new Node(nr, nc, sb.toString()));
				}
			}
		}
		return null;
	}
	
	// 범위체크
	static boolean boundaryCheck(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
	
	// char -> int 변환
	static int changeNum(char alpabet) {
		return alpabet - 'a';
	}
}