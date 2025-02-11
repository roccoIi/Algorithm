import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		
		String[] str = br.readLine().split("");
		int[] dp = new int[str.length + 1];
		
		if(Integer.parseInt(str[0]) == 0) {
			System.out.println(0);
			return;
		}
		
		
		dp[0] = dp[1] = 1;
		
		for(int i = 2; i <= str.length; i++) {
			sb = new StringBuilder();
			
			int num = Integer.parseInt(sb.append(str[i-1]).toString());
			
			if(1 <= num && num <= 9) {
				dp[i] += dp[i-1] % 1000000;
			}
			
			sb.deleteCharAt(0);
			num = Integer.parseInt(sb.append(str[i-2]).append(str[i-1]).toString());

			if(10 <= num && num <= 26) {
				dp[i] += dp[i-2] % 1000000;
			}
			
		}
		System.out.println(dp[str.length] % 1000000);
	}
}