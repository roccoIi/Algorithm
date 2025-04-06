import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int left = 0;
        int right = N-1;
        int minNum = Integer.MAX_VALUE;
        while(left < right) {
        	int sum = arr[left] + arr[right];
        	
        	if(minNum > Math.abs(sum)) {
        		sb = new StringBuilder();
        		sb.append(arr[left]).append(" ").append(arr[right]);
        		minNum = Math.abs(sum);
        	}
        	
        	if(sum < 0) left++;
        	else if(sum > 0) right--;
        	else break;
        }
        
        System.out.println(sb);
    }
}