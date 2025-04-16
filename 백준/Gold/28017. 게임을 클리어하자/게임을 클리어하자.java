import java.io.*;
import java.util.*;

public class Main {
	static int N, M, dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        initDP();
        
        for(int r = 1; r <= N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		getMinNum(r, c, Integer.parseInt(st.nextToken()));
        	}
        }
        
        System.out.println(getAnswer());
    }
    
    static void initDP() {
    	dp = new int[N+1][M];
    	
    	for(int r = 1; r <= N; r++) {
    		Arrays.fill(dp[r], 987654321);
    	}
    }
    
    static void getMinNum(int r, int c, int value) {
    	for(int i = 0; i < M; i++) {
    		if(i == c) continue;
    		dp[r][c] = Math.min(dp[r-1][i] + value, dp[r][c]);
    	}
    }
    
    static int getAnswer() {
    	int answer = 987654321;
    	for(int c = 0; c < M; c++) {
    		answer = answer > dp[N][c] ? dp[N][c] : answer;
    	}
    	
    	return answer;
    }
}