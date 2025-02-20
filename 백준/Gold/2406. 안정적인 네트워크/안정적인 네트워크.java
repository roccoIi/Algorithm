import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int start, end, value;
		
		Node(int start, int end, int value){
			this.start = start;
			this.end = end;
			this.value = value;
		}
	}
	static int N, M, parents[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = getInt(st.nextToken());
		M = getInt(st.nextToken());
		
		parents = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = getInt(st.nextToken());
			int B = getInt(st.nextToken());
			unionSet(A, B);
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>(
				(s1, s2) -> Integer.compare(s1.value, s2.value));
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int num = getInt(st.nextToken());
				
				if(r == 0 || r >= c) continue;
				
				pq.add(new Node(r+1, c+1, num));
			}
		}
		
		int cost = 0;
		List<Node> list = new ArrayList<>();
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			int x = findSet(curr.start);
			int y = findSet(curr.end);
			
			if(x == y) continue;
			
			unionSet(x, y);
			cost += curr.value;
			list.add(new Node(curr.start, curr.end, 0));
		}
		
		sb.append(cost).append(" ").append(list.size()).append('\n');
		for(Node node : list) {
			sb.append(node.start).append(" ").append(node.end).append('\n');
		}
		System.out.println(sb);
	}
	
	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		parents[y] = x;
	}
	
	static int findSet(int x) {
		if(parents[x] == x) return x;
		else return parents[x] = findSet(parents[x]);
	}
	
	static int getInt(String num) {
		return Integer.parseInt(num);
	}
}