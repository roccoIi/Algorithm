import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int target = Integer.parseInt(br.readLine());
		int[] dp = new int[target+1];
		
		// bottom-up 방
		// dp[0]은 주어진 조건에 없고, dp[1]은 이미 1이 완성되어있으므로 0이 답이다.
		// 2부터 고려하면 된다.
		for(int i = 2; i <= target; i++) {
			dp[i] = dp[i-1]+1; // 2던 3이던 뭐로든 안나눠떨어지면 -1
			// 6과 같이 2랑 3이랑 둘다로 나눠떨어질 수 있기때문에 if-else가 아닌 if+if로 처리한
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1); // 2로 나눠떨어지면 -1과 /2중에 최솟값
			if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1); // 3으로 나눠떨어지면 -1과 /3중에 최솟값
		}
		System.out.println(dp[target]);
	}
}