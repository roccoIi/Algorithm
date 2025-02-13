import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 각층별 대포알의 누적 갯수
		int[] sum = new int[122];
		sum[0] = 1;
		sum[1] = 4;
		int maxIdx = 0;
		for(int i = 2; i < 122; i++) {
			sum[i] = sum[i-1] + (sum[i-1] - sum[i-2] + (i + 1));
			if(sum[i] >= N) {
				maxIdx = i;
				break;
			}
		}
		
		// dp배열 초기화 (모두 1개인 대포알로 구성했을때로 가정)
		int[] dp = new int[N+1];
		for(int i = 0; i <= N; i++) {
			dp[i] = i;
		}
		
		for(int i = 1; i <= maxIdx; i++) {
			for(int j = sum[i]; j <= N; j++) {
				dp[j] = Math.min(dp[j], dp[j - sum[i]] + 1);
			}
		}
		
		System.out.println(dp[N]);
	}
}