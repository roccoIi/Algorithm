import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int divideNum = 1000000003;
		
		int[][] dp = new int[K+1][N+1];
		
		for(int i = 2; i <= N; i++) {
			dp[1][i] = i;
		}
		
		for(int r = 2; r <= K; r++) {
			for(int c = r * 2; c <= N; c++) {
				dp[r][c] = (dp[r-1][c-2] + dp[r][c-1]) % divideNum;
			}
		}
		
		System.out.println(dp[K][N]);
	}
}