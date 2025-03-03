import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	final int INF = 987654321;
        
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][10];
        
        for(int j = 1; j < 10; j++) {
        	dp[1][j] = 1;
        }
        
        for(int i = 2; i <= N; i++) {
        	for(int j = 1; j <= 9; j++) {
        		// 1과 9는 3개만 더한다.
        		if(j == 1 || j == 9) {
        			int start = j == 1 ? 1 : 7;
        			dp[i][j] = (dp[i-1][start] + dp[i-1][start + 1] + dp[i-1][start + 2]) % INF;
        		}
        		// 2와 8은 4개만 더한다.
        		else if(j == 2 || j == 8) {
        			int start = j == 2 ? 1 : 6;
        			dp[i][j] = (dp[i-1][start] + dp[i-1][start + 1] + dp[i-1][start + 2] + dp[i-1][start + 3]) % INF;
        		}
        		// 다머지는 5개 전부 더한다.
        		else dp[i][j] = (dp[i-1][j-2] + dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j+1] + dp[i-1][j+2]) % INF; 
        	}
        }
        
        long ans = 0;
        for(int j = 1; j <= 9; j++) {
        	ans = (ans + dp[N][j]) % INF;
        }
        
        System.out.println(ans);
    }
}