import java.io.*;
import java.util.*;

public class Main {
	static int N, M, app[][], dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int totalMemory = 0;
		app = new int[2][N+1];
		for(int r = 0; r < 2; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				app[r][c] = Integer.parseInt(st.nextToken());
				if(r == 1) totalMemory += app[r][c];
			}
		}
		
		// dp배열 초기화
		dp = new int[N+1][totalMemory+1];
		Arrays.fill(dp[0], 0);
		
		int lowCost = totalMemory;
		for(int r = 1; r < app[0].length; r++) {
			for(int c = 0; c <= totalMemory; c++) {
				if(c < app[1][r]) {
					dp[r][c] = dp[r-1][c];
				} else {
					dp[r][c] = Math.max(dp[r-1][c], dp[r-1][c - app[1][r]] + app[0][r]);
				}
				if(dp[r][c] >= M) lowCost = lowCost > c ? c : lowCost;
			}
		}
		
		System.out.println(lowCost);
    }
}