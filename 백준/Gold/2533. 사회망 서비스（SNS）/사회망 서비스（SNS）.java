import java.io.*;
import java.util.*;


public class Main {
	static List<Integer>[] list;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		
		int[][] arr = new int[N][2];
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		mainSolution(N, arr);
	}
	
	static void mainSolution(int N, int[][] arr) {
		
		// list 초기화
		list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<>();
		
		// dp 초기화
		dp = new int[N+1][2];
		
		for(int i = 0; i < N-1; i++) {
			int A = arr[i][0];
			int B = arr[i][1];
			list[A].add(B);
			list[B].add(A);
		}
		
		dfs(1, -1);	
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	static void dfs(int curr, int prev) {
		// 해당 문제에 비유하자면 curr은 리프노드인 5번이라고 생각해보자. 
		// 5번노드가 얼리어답터이기 위해선 자기 자신이 얼리어답터여야한다.
		// 즉, 얼리어답터가 되기 위한 최소 노드 수를 나타내는 dp배열에서 dp[5][1]은 1이여야한다.
		// 왜냐하면 자기 자신은 얼리어답터여야 하기 때문이다.
		// 반대로 얼리어답터가 아니라면 0이면 된다.
		dp[curr][0] = 0; // curr노드는 얼리어답터가 아니다.
		dp[curr][1] = 1; // curr노드는 얼리어답터다.
		
		for(int next : list[curr]) {
			if(next == prev) continue; // 무한루프 방지
			
			dfs(next, curr);
			
			dp[curr][0] += dp[next][1]; // 내가 얼리어답터가 아니라면 자식 노드는 얼리어답터여야 한다.
			dp[curr][1] += Math.min(dp[next][0], dp[next][1]); // 내가 얼리어답터라면 자식 노드는 얼리어답터여도 되고 아니여도 된다.
		}
	}
}