import java.io.*;
import java.util.*;

public class Main {
	/**
	 * dp배열은 두 가지로 구성했다.
	 * 0행 : 아주 큰 점프를 사용할 수 있는 기회가 남아있는 경우 == 아주 큰 점프 사용 X
	 * 1행 : 아주 큰 점프를 사용할 수 있는 기회가 없는 경우 == 아주 큰 점프 사용 O
	 * 
	 * 1. 0행은 아주 큰 점프를 사용하지 않았기 때문에 사용하지 않은 곳(0행)에서 값을 더해 갱신한다.
	 *    (i번째 돌이라는 가정)
	 *     1) i - 1 번째 돌에서 작은 점프를 한 경우 (사용x)
	 *     2) i - 2 번째 돌에서 큰 점프를 한 경우 (사용x)
	 * 2. 1행은 아주 큰 점프를 사용했기 때무에 사용한 곳에서 값을 더해 갱신한다.
	 * 	   1) i - 1 번째 돌에서 작은 점프를 한 경우 (사용 o)
	 *     2) i - 2 번째 돌에서 큰 점프를 한 경우 (사용 o)
	 *     3) i - 3 번째 돌에서 아주 큰 점프를 한 경우 (사용 x)
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int INF = 987654321;
		
		// N의 범위는 1 <= N <= 20 이다. 
		// N보다 작을 경우에는 아래 dp배열 초기화에서 오류가 발생하기 때문에 최소 길이는 4로 설정한다.
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[2][Math.max(N+1, 4)];
		int[][] dp = new int[2][Math.max(N+1, 4)];
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[0][i] = Integer.parseInt(st.nextToken());
			arr[1][i] = Integer.parseInt(st.nextToken());
		}
		
		int K = Integer.parseInt(br.readLine());
		
		// dp배열을 모두 0으로 둔다면 dp[1][2], dp[2][2]와 같이 값이 존재하지 앟는 경우에도 0으로 인식한다.
		// 해당 부분을 지나치기 위해 최대값으로 갱신해놓는다.
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		// DP배열 초기화
		dp[0][1] = 0;
		dp[1][1] = 0;
		dp[0][2] = arr[0][1];
		dp[0][3] = Math.min(dp[0][1] + arr[1][1], dp[0][2] + arr[0][2]);
		
		// 위 설명을 참고하여 기회를 사용했을 때와 사용하지 않았을 때를 구분하여 갱신한다.
		// 0행: 아주 큰 점프 사용X, 1행: 아주 큰 점프 사용O
		for(int i = 4; i <= N; i++) {
			dp[0][i] = Math.min(dp[0][i-1] + arr[0][i-1], dp[0][i-2] + arr[1][i-2]);
			dp[1][i] = Math.min(Math.min(dp[1][i-1] + arr[0][i-1], dp[1][i-2] + arr[1][i-2]), dp[0][i-3] + K);
		}
		
		// 마지막 돌에서 기회를 사용했을 때와 사용하지 않았을 때 중 더 작은 수를 출력한다.
		System.out.println(Math.min(dp[0][N], dp[1][N]));	
	}

}