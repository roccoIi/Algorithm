import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int start, end, cost;
		
		Node(int start, int end, int cost){
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Node>[] adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList[A].add(new Node(A, B, cost));
			adjList[B].add(new Node(B, A, cost));
		}
		
		// 1번 마을부터 시작한다. 해당 마을에서 갈 수 있는 마을들을 우선순위 큐에 넣는다.
		// 우선순위의 조건은 유지비가 낮은순서부터다 (유지비 기준 오름차순)
		PriorityQueue<Node> pq = new PriorityQueue<>(
				(s1, s2) -> Integer.compare(s1.cost, s2.cost));
		pq.addAll(adjList[1]);
		
		// 1번마을부터 시작하므로 1번마을은 방문체크한다.
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		
		int answer = 0;
		int pick = 1;
		int maxValue = -1;
		while(pick != N) {
			Node curr = pq.poll();
			
			if(visited[curr.end]) continue;
			visited[curr.end] = true;
			
			maxValue = maxValue < curr.cost ? curr.cost : maxValue;
			answer += curr.cost;
			pick++;
			
			pq.addAll(adjList[curr.end]);
		}
		
		System.out.println(answer - maxValue);
	}
}