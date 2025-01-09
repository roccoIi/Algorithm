import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[M];
		int[] dp = new int[10001]; //짜장면의 수 최대 범위는 10000이다.
		Arrays.fill(dp, INF);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[arr[i]] = 1;
		}
		
		// 처음 주어진 웍을 이용해서 양손을 사용해서 만들 수 있는 그릇 수 체크
		for(int i = 0; i < M; i++) {
			for(int j = i + 1; j < M; j++) {
				int sum = arr[i] + arr[j];
				if(sum < dp.length && dp[sum] == INF) dp[sum] = 1;
			}
		}
		
		// 0그릇은 0회다.
		dp[0] = 0;
		
		// 1그릇은 처음 주어진 웍 크기에 1이 없으면 의미가 없다. (있으면 초기화, 없으면 INF)
		// 2그릇은 1그릇+1그릇 이므로 2부터 시작한다.
		// 예를들어 8그릇 일 경우 (1그릇 + 7그릇), (2그릇 + 6그릇), (3그릇 + 5그릇), ... 으로 탐색해나간다.
		for(int i = 2; i <= N; i++) {
			for(int j = 1; j <= i/2; j++) {
				dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
			}
		}
		
		if(dp[N] == INF) {
			System.out.println("-1");
		} else {
			System.out.println(dp[N]);
		}
		
	}

}