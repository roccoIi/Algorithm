import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		double P = Double.parseDouble(st.nextToken());
		
		// 리스트-배열 생성
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 정점들간의 간선 정보를 입력받는다.(양방향)
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		
		// 이어진 간선이 없을 경우 해당 정점은 리프노드이다.
		int leafCnt = countLeafNode(list, N, 1);
		
		System.out.println(P / leafCnt);
		
	}
	
	// bfs로 돌면서 리프노드를 탐색한다.
	static int countLeafNode(ArrayList<Integer>[] list, int N, int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		q.add(start);
		
		int leafCnt = 0;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			boolean isLeaf = true; // 큐에 정점을 넣지 않았을경우에만 리프노드이다.
			
			for(int num : list[curr]) {
				if(visited[num]) continue;
				visited[num] = true;
				
				q.add(num);
				isLeaf = false;
			}
			
			if(isLeaf) leafCnt++;
		}
		return leafCnt;
	}
}