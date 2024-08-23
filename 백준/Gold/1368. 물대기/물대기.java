import java.util.*;
import java.io.*;

public class Main {
	static int N, dig[], arrIdx[][];
	
	public static void main(String[] args) throws IOException{
//		System.setIn(new java.io.FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
			
		N = Integer.parseInt(br.readLine());
		dig = new int[N];
		arrIdx = new int[N][N];
		int minValue = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			dig[i] = Integer.parseInt(br.readLine());
		}
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arrIdx[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			int tmp = bestValue(i);
//			System.out.println((i+1) + "번째 경우 : "+tmp);
			minValue = tmp < minValue? tmp : minValue;
		}
		
		System.out.println(minValue);
		
	}
	
	static int bestValue(int start) {
		int[] dist = new int[N];
		for(int i = 0; i < N; i++) {
			dist[i] = dig[i];
		}
		boolean[] visited = new boolean[N];
		visited[start] = true;
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			dist[i] = Math.min(dist[i], arrIdx[start][i]);
		}
		
		for(int i = 0; i < N-1; i++) {
			
			// 미방문 + 지금까지의 최소비용 찾기
			int min = Integer.MAX_VALUE;
			int idx = -1;
			for(int j = 0; j < N; j++) {
				if(!visited[j] && dist[j] < min) {
					min = dist[j];
					idx = j;
				}
			}
			
			visited[idx] = true;
			// 인접한 간선들 중 최소비용으로 업데이트 할 애가 있다면 업데이트 하기
			for(int j = 0; j < N; j++) {
				if(!visited[j] && arrIdx[idx][j] != 0 && dist[j] > arrIdx[idx][j]) {
					dist[j] = arrIdx[idx][j];
				}
			}
		}
		int ans = 0;
//		System.out.println("====최종 결과====");
		for(int i = 0; i < N; i++) {
			ans += dist[i];
//			System.out.println("dist[" + i + "] : " + dist[i]);
		}
		return ans;
	}
}