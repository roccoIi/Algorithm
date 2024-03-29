import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] arr, dist;
	static int N;
	static class BlackRupee implements Comparable<BlackRupee>{
		int r, c, value;

		public BlackRupee(int r, int c, int value) {
			this.r = r;
			this.c = c;
			this.value = value;
		}
		
		@Override
		public int compareTo(BlackRupee o) {
			return Integer.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st; 
		
		int idx = 1;
		
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			
			sb.append("Problem ").append(idx++).append(": ");
			arr = new int[N][N];
			dist = new int[N][N];
			
			// 주어진 배열 저장
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
					dist[r][c] = INF;
				}
			}
			BlackRupee r = new BlackRupee(0,  0,  0);
			
			escape(r);
			// [N-1][N-1]까지 도착한 값 + [0][0]값
			sb.append(dist[N-1][N-1] + arr[0][0]).append("\n");
		}
		System.out.println(sb);
	}
	
	// 우선순위큐 사용한 다익스트라
	static void escape(BlackRupee rupee) {
		PriorityQueue<BlackRupee> pq = new PriorityQueue<>();
		pq.offer(rupee);
		
		while(!pq.isEmpty()) {
			BlackRupee blackRupee = pq.poll();
			if(blackRupee.r == N-1 && blackRupee.c == N-1) return;
			if(blackRupee.value > dist[blackRupee.r][blackRupee.c]) continue;
			
			int value = blackRupee.value;
			
			for(int d = 0; d < 4; d++) {
				int nr = blackRupee.r + dr[d];
				int nc = blackRupee.c + dc[d];
				
				if(check(nr, nc) && dist[nr][nc] > arr[nr][nc] + value) {
					dist[nr][nc] = arr[nr][nc] + value;
					pq.offer(new BlackRupee(nr, nc, arr[nr][nc] + value));
				}
			}
		}
	}
	// 방문확인
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}