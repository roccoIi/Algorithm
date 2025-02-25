import java.io.*;
import java.util.*;

public class Main {
	static int T, W;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[T+1][W+1];
		
		for(int r = 1; r <= T; r++) {
			int curr = Integer.parseInt(br.readLine());
			
			for(int c = 0; c <= W; c++) {
				if(c == 0) {
					dp[r][c] = curr == 1 ? dp[r-1][c] + 1 : dp[r-1][c];
					continue;
				}
				
				int add = curr == 1 ? 1 : 0;
				int sub = 1 - add;
				
				
				if((c & 1) == 0) {
					dp[r][c] = max(dp[r-1][c] + add, dp[r-1][c-1] + sub);
				} else {
					dp[r][c] = max(dp[r-1][c] + sub, dp[r-1][c-1] + add);
				}
				
			}
		}
		System.out.println(dp[T][W]);
	}
	
	static int max(int x, int y) {
		return x < y ? y : x;
	}
}