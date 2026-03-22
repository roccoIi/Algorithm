import java.io.*;
import java.util.*;


public class Main {
	static int[] cnt;
	static List<Integer>[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		
		int[][] arr = new int[N-1][2];
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] query = new int[Q];
		for(int i = 0; i < Q; i++) {
			query[i] = Integer.parseInt(br.readLine());
		}
		
		
		mainSolution(N, R, arr, query);
	}
	
	static void mainSolution(int N, int R, int[][] arr, int[] query) {
		tree = new ArrayList[N+1];
		cnt = new int[N+1];
		
		// tree 초기화
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		// tree 간선정보 입력
		for(int i = 1; i < N; i++) {
			int A = arr[i-1][0];
			int B = arr[i-1][1];
			
			tree[A].add(B);
			tree[B].add(A);
		}
		
		dfs(R, -1);
		
		StringBuilder sb = new StringBuilder();
		for(int num : query) {
			sb.append(cnt[num]).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int curr, int prev) {
		// 현재 서브트리도 정점으로 자기 자신을 갖는다.
		cnt[curr] = 1;
		
		for(int next : tree[curr]) {
			// 방금 지나온 정점은 재방문하지 않는다.(무한루프 방지)
			if(next == prev) continue;
			dfs(next, curr);
			cnt[curr] += cnt[next];
		}
	}
}