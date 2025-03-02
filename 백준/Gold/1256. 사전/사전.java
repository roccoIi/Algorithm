import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static long dp[][];
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new long[N+1][M+1];
        
        dp[0][0] = 0;
        for(int r = 1; r <= N; r++) {
        	dp[r][0] = 1;
        }
        
        for(int c = 1; c <= M; c++) {
        	dp[0][c] = 1;
        }
        
        for(int r = 1; r <= N; r++) {
        	for(int c = 1; c <= M; c++) {
        		dp[r][c] = dp[r-1][c] + dp[r][c-1];
        		
        		if(dp[r][c] > 1000000000) dp[r][c] = 1000000000;
        	}
        }
        

        if(dp[N][M] < K) {
        	System.out.println(-1);
        	return;
        }
        
        while(true) {
        	if(N == 0 && M == 0) break;
        	
        	if(N == 0) {
        		sb.append("z");
        		M--;
        		continue;
        	} else if(M == 0) {
        		sb.append("a");
        		N--;
        		continue;
        	}
        	
        	if(K <= dp[N-1][M]) {
        		sb.append("a");
        		N--;
        	} else {
        		K -= dp[N-1][M];
        		sb.append("z");
        		M--;
        	}
        }
        
        System.out.println(sb);
    } 
}