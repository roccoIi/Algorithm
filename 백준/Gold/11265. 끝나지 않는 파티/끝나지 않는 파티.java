import java.io.*;
import java.util.*;

public class Main {
	static int N, M, dist[][];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				dist[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		FloydWarshall(dist);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			if(dist[start][end] <= time) sb.append("Enjoy other party\n");
			else sb.append("Stay here\n");
		}
		
		System.out.println(sb);
		
	}
	
	// 플로이드-워셜 알고리즘
	// 기존 다익스트라 알고리즘은 시작점을 기준으로 다음 노드까지 가는 가장 짧은 거리를 갱신했다면
	// 플로이드-워셜 알고리즘은 중간 경유지를 기준으로 거리를 갱신하게 된다.
	// 예를들어 1번 -> 2번 으로 가게 되는 경로가 있다면 1번 -> 3번 + 3번 -> 2번 을 계산해보는 것이다.
	static void FloydWarshall(int[][] dist){
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}