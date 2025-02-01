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
	static int N, M, K, map[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, 1, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// 이분탐색을 위한 조건을 입력받는다.
		int left = Integer.MAX_VALUE;
		int right = Integer.MIN_VALUE;
		
		// 문제에서 주어진 입력조건을 입력받으면서 이분탐색 조건도 갱신한다.
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				left = left > map[r][c] ? map[r][c] : left;
				right = right < map[r][c] ? map[r][c] : right;
			}
		}
		
		System.out.println(binarySearch(left, right));
	}
	
	/**
	 * 이분탐색을 통해 범위를 탐색하면서 주어진 조건을 만족하는 최소 강도를 판단하고,
	 * 그때의 강도를 반환한다.
	 * 
	 * @param left  암석 강도의 최솟값
	 * @param right 암석 강도의 최대값
	 * @return 총 파괴된 암석의 수
	 */
	static int binarySearch(int left, int right) {
		int answer = 0;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;

			if(countStones(mid) >= K) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return answer;
	}
	
	/**
	 * 바닥면을 제외한 가장자리에서 뚫을 수 있는 진입점을 Node형태로 저장한다.
	 * 
	 * @param  D 암석을 뚫을 수 있는 채굴강도
	 * @return Node가 저장되어있는 리스트 반환
	 */
	static ArrayList<Node> findStart(int D){
		ArrayList<Node> list = new ArrayList<>();
		
		for(int r = 0; r < N; r++) {
			if(map[r][0] <= D) list.add(new Node(r, 0));
			if(map[r][M-1] <= D) list.add(new Node(r, M-1));
		}
		
		for(int c = 1; c < M-1; c++) {
			if(map[0][c] <= D) list.add(new Node(0, c));
		}
		return list;
	}
	
	/**
	 * 주어진 조건을 만족하는 암석이 몇개인지 카운팅한다.
	 * 
	 * @param D 암석을 뚫을 수 있는 채굴강도
	 * @return 총 파괴한 암석의 수
	 */
	static int countStones(int D) {
		boolean[][] visited = new boolean[N][M];
		Queue<Node> q = new LinkedList<>();
		ArrayList<Node> list = findStart(D);
		for(Node node : list) {
			visited[node.r][node.c] = true;
			q.add(node);
		}
		
		int count = q.size();		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				if(!checkBoundary(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				
				if(map[nr][nc] <= D) {
					q.add(new Node(nr, nc));
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 주어진 암석의 범위를 벗어나는지 확인하는 함수
	 * 
	 * @param  r 열(세로)의 현재 좌표를 입력받는다.
	 * @param  c 행(가로)의 현재 좌표를 입력받는다.
	 * @return 범위내 존재한다면 Ture, 범위를 벗어났다면 False를 반환한다.
	 */
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}