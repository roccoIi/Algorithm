import java.io.*;
import java.util.*;

public class Main {
	static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

    	int[] arr = new int[N+2];
    	arr[0] = 0;
    	arr[1] = L;
    	for(int i = 2; i < N+2; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    
    	Arrays.sort(arr);
    	
    	int left = 1;
    	int right = L;
    	
    	while(left <= right) {
    		int mid = left + (right - left) / 2;
    		int count = 0;
    		
    		for(int i = N+1; i > 0; i--) {
    			count += (arr[i] - arr[i-1] - 1) / mid;
    		}
    		
    		if(count > M) {
    			left = mid + 1;
    		} else {
    			answer = mid;
    			right = mid - 1;
    		}
    	}
    	
    	System.out.println(answer);
    }
    
   
}