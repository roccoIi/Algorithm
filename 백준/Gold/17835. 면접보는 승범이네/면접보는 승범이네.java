import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int end;
		long distance;
		
		Node(int end, long distance){
			this.end = end;
			this.distance = distance;
		}
	}
	static int N, M, K;
	static final long INF = Long.MAX_VALUE;
	static ArrayList<Node>[] map; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		// 0) 문제에서는 도시 -> 면접장에서 가장 짧은 거리를 구하는 것이다.
		//    하지만 풀이에서는 면접장을 기준으로 면접장 -> 도시의 가장 짧은 거리를 구할 예정이므로 
		//    예제로 주어지는 출발점과 도착점을 반대로 입력받는다.
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long distance = Long.parseLong(st.nextToken());
			map[end].add(new Node(start, distance));
		}
		
		
		// 1) 방문체크 배열 생성
		boolean[] visited = new boolean[N+1];
		
		// 2) 다익스트라 계산을 위해 거리가 짧은순으로 정렬되는 우선순위큐 생성
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2)
				-> Long.compare(s1.distance, s2.distance));
		
		// 3) 거리배열 생성 (초기값은 INF, 첫 시작점은 0으로 갱신)
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		
		// 4) 주어진 면접장들을 우선순위 큐에 넣고 초기화
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int city = Integer.parseInt(st.nextToken());
			pq.add(new Node(city, 0));
			dist[city] = 0;
		}
		
		// 5) 각 면접장에서 도시로 나아가는 다익스트라 진행
		distanceMeasure(pq, visited, dist);
			
		// 6) 전체 도시중에서 가장 거리가 먼 도시 선정 후 거리 출력
		long maxDist = -1;
		int answerCity = -1;
		for(int i = 1; i <= N; i++) {
			if(maxDist < dist[i]) {
				answerCity = i;
				maxDist = dist[i];
			}
		}
		
		sb.append(answerCity).append('\n').append(maxDist);
		System.out.println(sb);
	}
	
	static void distanceMeasure(PriorityQueue<Node> pq, boolean[] visited, long[] dist) {
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.end]) continue;
			visited[curr.end] = true;
			
			for(Node next : map[curr.end]) {
				if(dist[next.end] > dist[curr.end] + next.distance) {
					dist[next.end] = dist[curr.end]+ next.distance;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}
	}
}