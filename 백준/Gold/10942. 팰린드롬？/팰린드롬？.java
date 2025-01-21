import java.io.*;
import java.util.*;

public class Main {
	static int N, M, arr[], dp[][];
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		arr = new int[N+1];
		
		// r: start, c: end
		dp = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(isPalindrome(start, end)).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static int isPalindrome(int start, int end) {
		int left = start;
		int right = end;
		
		while(start <= end) {
			if(visited[start][end]) return dp[start][end];
			if(arr[start] != arr[end]) {
				visited[start][end] = true;
				dp[start][end] = 0;
				return 0;
			}
			start++; end--;
		}
		
		while(left <= right) {
			if(visited[left][right]) return 1;
			visited[left][right] = true;
			dp[left][right] = 1;
			left++; right--;
		}
		return 1;
	}
}