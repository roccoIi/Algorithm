import java.io.*;
import java.util.*;

public class Main {
	static int N, T, K, target, times[] ,degree[], totalCost[];
	static boolean[][] visited;
	static ArrayList<Integer>[] buildings;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 각 건물당 완성에 필요한 시간을 저장한다.
			times = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
			}
			
			// 건물 순서를 저장할 배열 초기화
			buildings = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				buildings[i] = new ArrayList<>();
			}
			
			// 건물 순서를 입력받고, 진입차수를 저장한다.
			degree = new int[N+1];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				buildings[start].add(end);
				degree[end]++;
			}
			
			// 목표 건물을 입력받는다.
			target = Integer.parseInt(br.readLine());
			
			// 각 건물을 짓는 데 필요한 누적 시간을 입력받는다.
			totalCost = new int[N+1];
			
			build();
			
			sb.append(totalCost[target]).append('\n');
		}
		System.out.println(sb);
	}
	
	static void build() {
		// 진입차수가 0인 건물번호를 찾아 큐에 넣는다.
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) {
				totalCost[i] = times[i];
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for(int i = 0; i < buildings[curr].size(); i++) {
				int next = buildings[curr].get(i);
				totalCost[next] = Math.max(totalCost[curr] + times[next], totalCost[next]);
				degree[next]--;
				
				if(degree[next] == 0) q.add(next);
			}
		}
	}
}