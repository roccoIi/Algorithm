import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, child[], p[], total[][], people[], dp[];
	static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        p = new int[N+1];
        child = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	child[i] = Integer.parseInt(st.nextToken());
        	p[i] = i;
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	if(x < y) unionSet(x, y);
        	else unionSet(y,x);
        }
        
        total = new int[2][N+1];
        boolean[] visited = new boolean[N+1];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
        	int parents = findSet(i);
        	total[0][parents] += child[i];
        	total[1][parents] ++;
        	if(!visited[parents]) {
        		visited[parents] = true;
        		list.add(parents);
        	}
        }
 
        dp = new int[K+1];
        int totalChild = 0;
        for(int num : list) {     	
        	totalChild += total[1][num];
        	for(int i = Math.min(totalChild, K); i >= total[1][num]; i--) {
             	dp[i] = Math.max(dp[i], dp[i - total[1][num]] + total[0][num]);
            }
        }

        System.out.println(dp[K-1]); 
    }
    
    static int findSet(int x) {
    	if(p[x] == x) return x;
    	else return p[x] = findSet(p[x]);
    }
    
    
    // y가 x밑으로 들어간다.
    static void unionSet(int x, int y) {
    	x = findSet(x);
    	y = findSet(y);
    	
    	p[y] = x;
    }  
}