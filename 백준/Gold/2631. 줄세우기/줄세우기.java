import java.util.*;
import java.io.*;

public class Main {
	static int N, len;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		
        N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
//        int maxNum = -1;
//        int[] dp = dpLIS(arr);
//        for(int i = 0; i < N; i++) {
//        	maxNum = maxNum > dp[i] ? maxNum : dp[i];
//        }
//        System.out.println(N - maxNum);
        
        int[] dp = binaryLIS(arr);
        System.out.println(N - len-1);
	}
	
	// dp를 이용한 LIS(시간복잡도: O(N^2)
	static int[] dpLIS(int[] arr) {
		int[] dp = new int[N];
		
		for(int i = 0; i < N; i++) {
			dp[0] = 1;
			for(int j = 0; j < i; j++) {
				if(arr[i] >= arr[j]) continue;
				
				dp[i] = Math.max(dp[i], dp[j]+1);
			}
		}
		
		return dp;
	}
	
	// 이분탐색을 이용한 LIS(시간복잡도: O(NlogN))
	static int[] binaryLIS(int[] arr) {
		int[] dp = new int[N];
		dp[0] = arr[0];
		
		for(int i = 1; i < N; i++) {
			if(arr[i] > dp[len]) {
				dp[++len] = arr[i];
			} else {
				int idx = binarySearch(dp, arr[i], 0, len);
				dp[idx] = arr[i];
			}		
		}	
		
		return dp;
	}
	
	static int binarySearch(int[] dp, int num, int left, int right) {
		int answer = 0;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;
			
			if(dp[mid] <= num) {
				left = mid + 1;
			} else {
				answer = mid;
				right = mid - 1;
			}
		}
		
		return answer;
	}
}