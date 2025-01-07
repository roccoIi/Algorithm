import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int start, end, distance;
		
		Node(int start, int end, int distance){
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
	}
	static int N, M, W, dist[];
	static final int INF = 987654321;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		while(testCase-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			dist = new int[N+1];
			
			// 각 도로의 정보를 입력받는다. (양방향 통행)
			ArrayList<Node> nodes = new ArrayList<>();
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());
				
				nodes.add(new Node(start, end, distance));
				nodes.add(new Node(end, start, distance));
			}
			
			// 웜홀의 정보를 입력받는다. (단방향 통행, 음의 가중치)
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());
				
				nodes.add(new Node(start, end, (distance * -1)));
			}
			
			// 음수 사이클이 존재한다면 시간여행이 가능하다는 의미이다. (True 반환)
			// 없다면 시간여행이 불가능하다는 뜻이다. (False 반환)
			if(bellmanford(nodes)) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}
	
	static boolean bellmanford (ArrayList<Node> nodes) {
		// 시작 지점을 1번 노드로 설정
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		// N-1번 순회할 예정이다. 이때 마지막 반복에서도 값이 갱신되었을 경우 음수 사이클이 존재한다는 뜻이다.
		for(int i = 1; i <= N; i++) {
			
			// 전체 노드를 순회하면서 갱신한다.
			for(Node curr : nodes) {
				
				// 최단거리 갱신
				if(dist[curr.end] > dist[curr.start] + curr.distance) {
					dist[curr.end] = dist[curr.start]+ curr.distance;
					
					// 최단거리 갱신이 마지막 반복에서 이뤄졌을 경우 음수 사이클 존재 (True 반환)
					if(i == N) {
						return true;
					}
				}
			}
		}
		// 아무 문제없이 반복문을 순회했다면 음수 사이클이 없으므로 false 반환.
		return false;
	}
}