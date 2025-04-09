import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int maxNum = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        if(start == end) {
        	System.out.println(start);
        	return;
        }
        
        Arrays.sort(arr);
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(isEven(start) ? start + 1 : start);
        list.add(isEven(end) ? end - 1 : end);
        
        for(int i = 1; i < N; i++) {
        	int mid = (arr[i-1] + arr[i]) / 2;
        	if(isEven(mid)) {
        		list.add(mid-1);
        		list.add(mid+1);
        	} else list.add(mid);
        }
        
        
        int answer = 0;
        for(int num : list) {
        	if(num < start || num > end) continue;
        	
        	int min = Integer.MAX_VALUE;
        	for(int i = 0; i < N; i++) {
        		min = Math.min(min, Math.abs(arr[i] - num));
        	}
        	
        	if(min > maxNum) {
        		maxNum = min;
        		answer = num;
        	}
        }
        
        System.out.println(answer);
    }
    
    static boolean isEven(int num) {
    	return (num & 1) == 0;
    }
    
}