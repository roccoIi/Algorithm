import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 각층별 대포알의 누적 갯수
		ArrayList<Integer> sum = new ArrayList<>();
		sum.add(1);
		sum.add(4);
		int idx = sum.size() - 1; // 
		while(sum.get(idx) <= N) {
			int newNum = sum.get(idx) + (sum.get(idx) - sum.get(idx - 1) + (idx + 2));
			sum.add(newNum);
			idx++;
		}
		
		// dp배열 초기화 (모두 1개인 대포알로 구성했을때로 가정)
		int[] dp = new int[N+1];
		for(int i = 0; i <= N; i++) {
			dp[i] = i;
		}
		
		for(int i = 1; i < sum.size(); i++) {
			int num = sum.get(i);
			
			// j - num < 0 이면 어차피 갱신 안된다.
			for(int j = num; j <= N; j++) {
				dp[j] = Math.min(dp[j], dp[j - num] + 1);
			}
		}
		
		System.out.println(dp[N]);
	}
}