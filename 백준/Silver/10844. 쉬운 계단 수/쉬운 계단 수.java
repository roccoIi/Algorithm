import java.util.*;
import java.io.*;

public class Main {
	static final int INF = 1000000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][10];
		for(int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j <= 9; j++) {
				int left = j-1 < 0 ? 0 : dp[i-1][j-1];
				int right = j+1 > 9 ? 0 : dp[i-1][j+1];
				dp[i][j] = (left + right) % INF;
			}
		}
		
		int answer = 0;
		for(int j = 0; j <= 9; j++) {
			answer = (answer + dp[N][j]) % INF;
		}
		
		System.out.println(answer);
	}
}