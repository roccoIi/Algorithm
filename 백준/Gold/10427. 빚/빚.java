import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[], sum[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	
        	N = Integer.parseInt(st.nextToken());
        	arr = new int[N+1];
        	for(int i = 1; i <= N; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	Arrays.sort(arr);
        	
        	sum = new int[N+1];
        	for(int i = 1; i <= N; i++) {
        		sum[i] = sum[i-1] + arr[i];
        	}
        	
        	int answer = 0;
        	for(int i = 2; i <= N; i++) {
        		answer += getSum(i);
        	}
        			
        	sb.append(answer).append('\n');
        }
        
        System.out.println(sb);
    }
    
    static int getSum(int idx) {
    	int answer = Integer.MAX_VALUE;
    	
    	// 가장 큰 빌린돈: arr[i]
    	// M번을 값아라: idx
    	for(int i = idx; i <= N; i++) {
    		answer = Math.min(answer, (arr[i] * idx) - (sum[i] - sum[i - idx]));
    	}
    	return answer;
    }
}