import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine().trim());
		int[][] rbg = new int[N][N]; // 기존 문제 배열
		int[][] dp = new int[N][N];  // dp배열 
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				rbg[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp의 각 색깔별 초기값은 제시된 값과 동일하다.
		for(int i = 0; i < 3; i++) {
			dp[0][i] = rbg[0][i];
		}
		
		// 문제 조건: N번째 집의 색은 N-1집의 색과 달라야한다.
		for(int i = 1; i < N; i++) {
			dp[i][0] = rbg[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = rbg[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = rbg[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		int answer = Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]);
		
		System.out.println(answer);
	}
}