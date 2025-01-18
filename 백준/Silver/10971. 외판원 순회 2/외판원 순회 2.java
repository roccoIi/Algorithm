import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int end, distance;
		
		Node(int end, int distance){
			this.end = end;
			this.distance = distance;
		}
	}
	static int N, answer, totalCnt;
	static boolean[] visited;
	static ArrayList<Node>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		list = new ArrayList[N+1];
		answer = 987654321;
		
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			list[r] = new ArrayList<>();
			for(int c = 1; c <= N; c++) {			
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) list[r].add(new Node(c, num));
			}
		}
		
		for(int i = 1; i <= N; i++) {
			findRoute(i, i, 0, 0);
		}
		
		System.out.println(answer);
		
		
	}
	
	static void findRoute(int start, int curr, int cnt, int dist) {
		if(curr == start && cnt == N) {
			answer = answer > dist ? dist : answer;
			return;
		}
		
		for(Node now : list[curr]) {
			int next = now.end;
			int distance = now.distance;
			
			if(visited[next]) continue;
			
			if(next == start && cnt != N - 1) continue;

			visited[next] = true;
			findRoute(start, next, cnt+1, dist + now.distance);
			visited[next] = false;
		}
	}
}