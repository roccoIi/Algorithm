import java.util.*;
import java.io.*;

public class Main {
	static int N, M, H;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
        	String[] str = br.readLine().split(" ");
        	list[i] = new ArrayList<>();
        	for(int j = 0; j < str.length; j++) {
        		list[i].add(Integer.parseInt(str[j]));
        	}
        }
        
        /* dfs로는 O(10^50)가 된다.... 다른 방법은??
         * dp가 있지 않을까 그럼?? -> 뭘 메모이제이션 할 수 있을까?
         * 2, 3, 5로 만들 수 있는  (최대 5까지)
         * [1] 2 3 5
         * [2] 3 5
         * [3] 1 2 3
         * 
         * 1. 내가 가진 블록으로 해당 숫자를 만들 수 있다. 
         * 2. 내 블록 없이도 내 이전의 숫자만으로 목표를 만들 수 있다.
         * 3. 내 블록과 이전의 숫자의 합으로 목표를 만들 수 있다.
         * */ 	
        
        int[][] dp = new int[N+1][H+1];

        for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= H; c++) {
				for(int num : list[r]) {
					if(c == num) dp[r][c]++; // (1)
					if(c > num) dp[r][c] += dp[r-1][c-num]; // (3)
				}
				dp[r][c] += dp[r-1][c]; //(3)
				dp[r][c] %= 10007;
			}
        }
        
        System.out.println(dp[N][H]);  
	}
}