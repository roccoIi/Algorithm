import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int s, e, r; //s: Start, e: End, r: Road
		
		Node(int s, int e, int r){
			this.s = s;
			this.e = e;
			this.r = r;
		}
	}
	static int V, E;
	static List<Node>[] list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken()) + 1; // 정점 (건물의 수)
		E = Integer.parseInt(st.nextToken()); // 간선 (도로의 수)
		
		list = new List[V];
		for(int i = 0; i < V; i++) {
			list[i] = new ArrayList<>();
		}
		
	
		for(int i = 0; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			if(r == 1) {
				r = 0;
			} else {
				r = 1;
			}
			list[s].add(new Node(s, e, r));
			list[e].add(new Node(e, s, r));
		}
		
		
		int best = bestRoot();
		int worst = worstRoot();
		System.out.println((int)(Math.pow(worst, 2) - Math.pow(best, 2)));
	}
	
	// 최소신장트리 -> 가장 작은 값을 가지고 있으면서 방문하지 않은 값들을 순회하며 구해야한다.
	static int bestRoot() {
		boolean[] visited = new boolean[V];
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.r, s2.r));
		visited[0] = true;
		
		for(int i = 0; i < list[0].size(); i++) {
			pq.add(list[0].get(i));
		}
		
		int pick = 1;// 현재 뽑힌 정점의 수 (0은 뽑혔으니깐 1이다.)
		int ans = 0;// 정답길이를 누적해서 구할 예정이다.
		
		while(pick != V) {// 총 정점의 수와 같아지면 종료!
			Node node = pq.poll();
			if(visited[node.e]) continue; //만약 목표로 한 곳이 이미 방문한 이력이 있는 곳이라면? 패쓰!
			
			ans += node.r;
			visited[node.e] = true;
			pick++;
			
			pq.addAll(list[node.e]);
		}
		
		return ans;
	}
	
	// 최대신장트리 -> 가장 큰 값을 가지고 있으면서 방문하지 않은 값들을 순회하며 구해야한다. 
	static int worstRoot() {
		boolean[] visited = new boolean[V];
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s2.r, s1.r));
		visited[0] = true;
		
		for(int i = 0; i < list[0].size(); i++) {
			pq.add(list[0].get(i));
		}
		
		int pick = 1;
		int ans = 0;
		
		while(pick != V) {
			Node node = pq.poll();
			if(visited[node.e]) continue;
			
			ans += node.r;
			visited[node.e]= true;
			pick ++;
			
			pq.addAll(list[node.e]);
		}
		
		return ans;
	}
}