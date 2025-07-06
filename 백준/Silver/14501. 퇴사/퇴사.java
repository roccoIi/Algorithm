import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] T = new int[N];
		int[] P = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		for(int i = 0; i < N; i++) {
			// 현재 날짜 + 상담기간이 전체 기간을 넘지 않을때만 진행
			if(i + T[i] <= N) {
				dp[i + T[i]] = Math.max(dp[i + T[i]], P[i] + dp[i]);
			}
			
			// 미래를 위해 오늘 쉰다면 다음날은 오늘 누적값을 그대로 가져갈것이다
			dp[i+1] = Math.max(dp[i], dp[i+1]);
		}
		
		System.out.println(dp[N]);
	}
}