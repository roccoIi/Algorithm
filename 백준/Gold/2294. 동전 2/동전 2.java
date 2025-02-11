import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int INF = 1 << 30;
		int[] dp = new int[K+1];
		int[] coins = new int[N];
		
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = coins[i]; j <= K; j++) {
				dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
			}
		}
		
		System.out.println(dp[K] == INF ? -1 : dp[K]);
	}
}