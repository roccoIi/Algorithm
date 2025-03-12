import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    	
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
        	int N = Integer.parseInt(br.readLine());
        	
        	int[] arr = new int[N];
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	int price = Integer.parseInt(br.readLine());
        	
        	int[] dp = new int[price+1];
        	dp[0] = 1;
        	
        	for(int i = 0; i < N; i++) {
        		for(int j = arr[i]; j <= price; j++) {
        			dp[j] = dp[j] + dp[j - arr[i]];
        		}
        	}
        	
        	sb.append(dp[price]).append('\n');
        }
        System.out.println(sb);
    }
}