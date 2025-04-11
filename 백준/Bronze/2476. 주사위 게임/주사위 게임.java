import java.io.*;
import java.util.*;

public class Main {
	static int N, maxMoney;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        maxMoney = Integer.MIN_VALUE;
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	int C = Integer.parseInt(st.nextToken());
        	
        	int money = 0;
        	
        	if(A == B && B == C) {
        		money = 10000 + (A * 1000);
        	} else if(A != B && B != C && A != C) {
        		int maxNum = Math.max(A, Math.max(B, C));
        		money = maxNum * 100;
        	} else {
        		if(A == B || A == C) money = 1000 + (A*100);
        		else money = 1000 + (B*100);
        	}
        	
        	maxMoney = Math.max(maxMoney, money);
        }
        
        System.out.println(maxMoney);
    }
    
}