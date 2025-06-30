import java.util.*;
import java.io.*;

public class Main {	
	static int N, M, X, maxDist;
	static ArrayList<Node>[] dist, reverseDist;
	static class Node{
		int idx, distance;
		
		Node(int idx, int distance){
			this.idx = idx;
			this.distance = distance;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dist = new ArrayList[N+1];
		reverseDist = new ArrayList[N+1];
		for(int i = 1; i <= N; i++){
			dist[i] = new ArrayList<>();
			reverseDist[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			dist[start].add(new Node(end, time));
			reverseDist[end].add(new Node(start, time));
		}
		
		int[] forward = dijkstra(dist);
		int[] reverse = dijkstra(reverseDist);
		
		for(int i = 1; i <= N; i++) {
			maxDist = Math.max(maxDist, forward[i] + reverse[i]);
		}
		
		System.out.println(maxDist);
	}
	
	private static int[] dijkstra(ArrayList<Node>[] list) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, 987654321);
		dist[X] = 0;
		
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.distance, s2.distance));
		pq.add(new Node(X, 0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.idx]) continue;
			visited[curr.idx] = true;
			
			for(Node next : list[curr.idx]) {
				if(dist[next.idx] > dist[curr.idx] + next.distance) {
					dist[next.idx] = dist[curr.idx] + next.distance;
					pq.add(new Node(next.idx, dist[next.idx]));
				}
			}
		}
		
		return dist;
	}
}