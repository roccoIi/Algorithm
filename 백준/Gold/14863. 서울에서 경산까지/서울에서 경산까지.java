import java.io.*;
import java.util.*;

public class Main {
	static int N, K, dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        dp = new int[N][K+1];
        Queue<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2; i++) {
        	int time = Integer.parseInt(st.nextToken());
        	int money = Integer.parseInt(st.nextToken());
        	q.add(time);
        	dp[0][time] = money;
        }
        
        int minTime = 0;
        for(int r = 1; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	int walkTime = Integer.parseInt(st.nextToken());
        	int walkMoney = Integer.parseInt(st.nextToken());
        	int bikeTime = Integer.parseInt(st.nextToken());
        	int bikeMoney = Integer.parseInt(st.nextToken());     	
        	minTime += Math.min(walkTime, bikeTime);
        	
        	int qSize = q.size();
        	for(int c = 0; c < qSize; c++) {       	
        		int lastTime = q.poll();
        		
        		if(lastTime + walkTime <= K) {
        			int newTime = lastTime + walkTime;
        			dp[r][newTime] = Math.max(dp[r][newTime], dp[r-1][lastTime] + walkMoney);
        			q.add(newTime);
        		}
        		
        		if(lastTime + bikeTime <= K) {
        			int newTime = lastTime + bikeTime;
        			dp[r][newTime] = Math.max(dp[r][newTime], dp[r-1][lastTime] + bikeMoney);
        			q.add(newTime);
        		}
        	}	
        }
        
        int answer = 0;
        for(int i = minTime; i <= K; i++) {
        	answer = Math.max(answer, dp[N-1][i]);
        }
        System.out.println(answer);
    }
}