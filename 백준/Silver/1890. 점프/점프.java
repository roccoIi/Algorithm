import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		long[][] dp = new long[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				
				// 도착지점에선 이동하지 않는다. 
				if(r == N-1 && c == N-1) continue;
				
				// 점프했을 때 도달하는 새로운 좌표
				int nr = r + arr[r][c];
				int nc = c + arr[r][c];
				
				// 새로운 좌표가 범위내에 있을 경우에만 계산한다.
				if(check(nr, c)) dp[nr][c] += dp[r][c];
				if(check(r, nc)) dp[r][nc] += dp[r][c];		
			}
		}
		
		System.out.println(dp[N-1][N-1]);
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}