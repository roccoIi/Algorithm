import java.io.*;
import java.util.*;

public class Main {
	static int N, K, dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        dp = new int[N+1][K+1];
        
        for(int r = 1; r <= N; r++) {
        	st = new StringTokenizer(br.readLine());
        	int walkTime = Integer.parseInt(st.nextToken());
        	int walkMoney = Integer.parseInt(st.nextToken());
        	int bikeTime = Integer.parseInt(st.nextToken());
        	int bikeMoney = Integer.parseInt(st.nextToken());     	
        	
        	if(r == 1) {
				dp[r][walkTime] = walkMoney;
				dp[r][bikeTime] = Math.max(dp[r][bikeTime], bikeMoney);
    		} else {
	        	for (int t = 0; t <= K; t++) {
	        		if(dp[r-1][t]==0) continue;
	        		
	                // 도보 선택
	                if (t + walkTime <= K) {
	                    dp[r][t + walkTime] = Math.max(dp[r][t + walkTime], dp[r - 1][t] + walkMoney);
	                }
	                
	                // 자전거 선택
	                if (t + bikeTime <= K) {
	                    dp[r][t + bikeTime] = Math.max(dp[r][t + bikeTime], dp[r - 1][t] + bikeMoney);	
	                }
	        	} 
            }
        }
        
        int answer = 0;
        for (int t = 0; t <= K; t++) {
        	answer = Math.max(answer, dp[N][t]);
        }
        System.out.println(answer);
    }
}