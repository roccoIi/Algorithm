import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * [진행방식]
 * 1. 입력 받으면서 가장 높은 봉우리 확인 후
 *    다시 반복 돌면서 해당봉우리의 좌표(r, c), 현재길이(1), 높이(value), 깍아낼기회(true)를 노드로 만들어 리스트에 넣는다.
 * 2. 총 리스트의 크기 (최대 봉우리의 크기) 만큼 반복을 돌면서 makePath 함수를 진행한다.
 * 
 * [makePath]
 * 3. 사방탐색을 하면서 2가지 경우로 나뉘었다. (기본으로 충족해야하는 조건 : 경계를 벗어나지 않는가, 방문하지 않았는가)
 * 4-1. 내 현재 높이(node.value) 보다 진행하려는 위치의 높이가 더 낮은경우 => 길이+1, 높이 변경한 노드 만들어 재귀 진행
 * 4-2. 내 현재 높이보다 진행하려는 위치가 같거나 높은경우 => 아직 기회(node.chance = true)가 남아있고 깍을 수 있는 범위에 있다면
 * 		내 높이를 -1(가장 멀리가려면 최소한으로 깍는다.) 한 후에 기회를 false로 변경하고 노드에 넣어 재귀에 들어간다.
 * 5. 사방탐색이 끝났다는 의미는 사방을 둘러봤을때 더이상 나아갈 곳이 없다는 의미로 기저조건에 해당한다. 길이의 최댓값을 갱신한다.
 */ 


public class Solution {
	// N: 지도크기(3~8), K: 최대 공사가능 깊이 (1~5), highest: 가장 높은 봉우리, maxLength: 최대 길이
	static int N, K, highest, maxLength; 
	static boolean[][] visit;			 // 방문배열
	static int[][] map;					 // 입력받을 지도
	static int[] dr = {-1, 1, 0, 0};	 // 사방탐색
	static int[] dc = {0, 0, -1, 1};
	static class Node{
		int r, c, length, value;
		boolean chance;

		public Node(int r, int c, int length, int value, boolean chance) {
			this.r = r;
			this.c = c;
			this.length = length;
			this.value = value;
			this.chance = chance;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc);
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			highest = 0;
			map = new int[N][N];
			maxLength = Integer.MIN_VALUE;
			
			
			// 1. 입력받으며 최대봉우리를 찾고 
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c] > highest) highest = map[r][c];
				}
			} 
			
			// 다시 돌면서 최대 봉우리의 정보를 노드로 만들어 리스트(start)에 넣는다.
			List<Node> start = new ArrayList<>();
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(map[r][c] == highest) 
						start.add(new Node(r, c, 1, highest, true));
				}
			}
			// 2. 리스트 크기만큼 진행한다.
			for(int i = 0; i < start.size(); i++) {
				visit = new boolean[N][N];
				makePath(start.get(i));
			}
			
			sb.append(" ").append(maxLength).append("\n");			
		}// testCase 종료
		System.out.println(sb);
	}// main 종료
	
	static void makePath(Node node) {
		// 들어가자마자 방문표시
		visit[node.r][node.c] = true; 
		
		// 재귀조건
		for(int d = 0; d < 4; d++) { // 3. 사방탐색을 하면서 2가지 경우로 나뉘었다.
			int nr = node.r + dr[d];
			int nc = node.c + dc[d];
			
			// 4-1. 내 현재 높이(node.value) 보다 진행하려는 위치의 높이가 더 낮은경우
			if(check(nr, nc) && !visit[nr][nc] && node.value > map[nr][nc]) {
				makePath(new Node(nr, nc, node.length+1, map[nr][nc], node.chance));
				visit[nr][nc] = false;
			// 4-2. 내 현재 높이보다 진행하려는 위치가 같거나 높은경우
			} else if(check(nr, nc) && !visit[nr][nc] && node.value <= map[nr][nc]) {
				if(node.chance && map[nr][nc] - node.value < K) {		
					makePath(new Node(nr, nc, node.length+1, node.value-1, false));
					visit[nr][nc] = false;
				}
			}
		}
		// 기저조건
		//5. 사방탐색이 끝났다는 의미는 사방을 둘러봤을때 더이상 나아갈 곳이 없다는 의미로 기저조건에 해당한다. 길이의 최댓값을 갱신한다.
		maxLength = Math.max(maxLength, node.length);
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}