import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R, K, idx, dp[][], dp_zero[][];
	static boolean visited[][];
	static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // N: 방문할 수 있는 전체 도시 수, R: 내일로 티켓가격
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()) * 2;
        
        // 각 방문할 도시를 숫자로 나타낸다.
        // 이 때, 중복으로 제시될 가능성도 있으므로 Map으로 관리한다.
        String[] cities = br.readLine().split(" ");
        for(String city : cities) {
        	if(!map.containsKey(city)) map.put(city, idx++);
        }
        
        // 방문 예정인 도시 입력
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] list = new int[M];
        for(int i = 0; i < M; i++) {
        	list[i] = map.get(st.nextToken());
        }
        
        // dp: 내일로 티켓이 적용되지 않은 가격
        // dp_zero: 내일로 티켓 혜택이 적용된 가격
        dp = new int[idx][idx];
        dp_zero = new int[idx][idx];
        
        // 전체 배열은 -1로 초기화
        for(int i = 0; i < idx; i++) {
        	Arrays.fill(dp[i], -1);
        	Arrays.fill(dp_zero[i], -1);
        }
        
        // 주어진 티켓들을 입력받는다.
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	String type = st.nextToken();
        	int startCity = map.get(st.nextToken());
        	int endCity = map.get(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken()) * 2;
        	
        	// 아직 입력이 안된 가격일땐(-1) 첫 가격 그대로, 이후에는 최소가격으로 비교한다.
        	// 왕복은 가격이 동일하므로 양방향으로 입력한다.
        	dp[startCity][endCity] = dp[startCity][endCity] == -1 ? cost : Math.min(dp[startCity][endCity], cost);
        	dp[endCity][startCity] = dp[endCity][startCity] == -1 ? cost : Math.min(dp[endCity][startCity], cost);
        	
        	// 내일로 적용 티켓가격으로 변환
        	if(isFree(type)) cost = 0;
        	else if(isHalf(type)) cost /= 2;
        	
        	// 아직 입력이 안된 가격일땐(-1) 첫 가격 그대로, 이후에는 최소가격으로 비교한다.
        	// 왕복은 가격이 동일하므로 양방향으로 입력한다.
        	dp_zero[startCity][endCity] = dp_zero[startCity][endCity] == -1 ? cost : Math.min(dp_zero[startCity][endCity], cost);
        	dp_zero[endCity][startCity] = dp_zero[endCity][startCity] == -1 ? cost : Math.min(dp_zero[endCity][startCity], cost);   	
        }
     
        // 두 배열(내일로 적용, 내일로 미적용)에 대해 모든 정점간의 최단거리를 계산
        Floyd_Warshall(dp);
        Floyd_Warshall(dp_zero);       

        // 내일로 티켓이 있을때와 없을때 총 비용계산
        int non_ticket = 0;
        int ticket = 0;
        for(int i = 0; i < list.length-1; i++) {
        	non_ticket += dp[list[i]][list[i+1]];
        	ticket += dp_zero[list[i]][list[i+1]];
        }
        
        System.out.println(non_ticket > (ticket + R) ? "Yes" : "No");
    }
    
    static void Floyd_Warshall(int[][] dp){
    	for(int k = 0; k < idx; k++) {
    		for(int i = 0; i < idx; i++) {
    			for(int j = 0; j < idx; j++) {
    				// 길이 없는 구간은 넘어간다.
    				if(dp[i][k] == -1 || dp[k][j] == -1) continue;
    				
    				// 길이 없었어도 두 경로를 통해 갈 수 있다면 갱신한다. 길이 있었으면 최솟값 비교하여 갱신한다.
    				dp[i][j] = dp[i][j] == -1 ? dp[i][k] + dp[k][j] : Math.min(dp[i][j], dp[i][k] + dp[k][j]);
    			}
    		}
    	}
    }
    
    // 내일로 티켓 혜택 적용 가능여부 (무료)
    static boolean isFree(String type) {
    	return type.equals("Mugunghwa") || type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun");
    }
    
    // 내일로 티켓 혜택 적용 가능여부 (50%)
    static boolean isHalf(String type) {
    	return type.equals("S-Train") || type.equals("V-Train");
    }
}