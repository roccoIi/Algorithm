import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int idx, distance;
		
		Node(int idx, int distance){
			this.idx = idx;
			this.distance = distance;
		}
	}
	static int V, E, dist[];
	static ArrayList<Node>[] map;
	static final int INF = 987654321;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		Node curr = new Node(Integer.parseInt(br.readLine()), 0);
		dist[curr.idx] = 0;
	
		map = new ArrayList[V+1];
		for(int i = 1; i <= V; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			map[start].add(new Node(end, dist));
		}
		
		shortestPath(curr);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(dist[i] == INF) sb.append("INF").append('\n');
			else sb.append(dist[i]).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void shortestPath(Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.distance, s2.distance));
		pq.add(start);
		
		boolean[] visited = new boolean[V+1];
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.idx]) continue;
			visited[curr.idx] = true;
			
			for(Node next : map[curr.idx]) {
				if(dist[next.idx] > dist[curr.idx] + next.distance) {
					dist[next.idx] = dist[curr.idx] + next.distance;
					pq.add(new Node(next.idx, dist[next.idx]));
				}
			}
		}
	}
}