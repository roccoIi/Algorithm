import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int value;
		
		public Node(int r, int c, int value) {
			this.r = r;
			this.c = c;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}
	
	
	static int[][] arr, dist;
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= testCase; T++) {
			sb.append("#").append(T).append(" ");
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N][N]; // 제시되는 값 입력할 배열
			dist = new int[N][N]; // 최소값 저장할 배열
			
			for(int r = 0; r < N; r++) {
				String str = br.readLine();
				for(int c = 0; c < N; c++) {
					arr[r][c] = str.charAt(c) - '0';
					dist[r][c] = INF;
				}
			}
			
			Node node = new Node(0, 0, 0);
			
			dijkstra(node);
			
			sb.append(dist[N-1][N-1]).append("\n");
		}
		
		System.out.println(sb);	
	}
	
	static void dijkstra(Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(start);
		
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(node.r == N-1 && node.c == N-1) return; // 끝지점에 도착했으면 가차없이 종료
			if(node.value > dist[node.r][node.c]) continue; // 최소값보다 크면 가차없이 패쓰
			
			for(int d = 0; d < 4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				
				// 최소값 저장할 배열의 해당위치 값이 (내 현재 위치 값(사실상 누적된 값) + 이동할 위치의 값) 보다 크면 갱신
				if(check(nr, nc) && dist[nr][nc] > arr[nr][nc] + node.value) {
					dist[nr][nc] = arr[nr][nc] + node.value;
					pq.offer(new Node(nr, nc, arr[nr][nc] + node.value));
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}