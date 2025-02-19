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
	static int N, M, parents[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>(
				(s1, s2) -> Integer.compare(s1.cost, s2.cost));
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			pq.add(new Node(A, B, C));
		}
		
		int answer = 0;
		int pick = 0;
		int maxValue = -1;
		for(int i = 0; i < M; i++) {
			Node curr = pq.poll();
			
			int x = findSet(curr.start);
			int y = findSet(curr.end);
			
			// 둘의 부모가 같다는 것은 사이클이라는 의미이다. 넘긴다.
			if(x == y) continue;
			
			unionSet(x, y);
			answer += curr.cost;
			maxValue = Math.max(maxValue, curr.cost);
			
			if(++pick == N) break;
		}
		System.out.println(answer - maxValue);
	}
	
	// x의 부모를 찾는 과정
	static int findSet(int x) {
		if(parents[x] == x) return x;
		else return parents[x] = findSet(parents[x]);
	}
	
	// y의 부모를 x로 만든다. (x에 y를 자식으로 넣겠다)
	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		parents[y] = x;
	}
}