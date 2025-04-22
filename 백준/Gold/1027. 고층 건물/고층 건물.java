import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int maxCount = 0;
        for(int i = 1; i <= N; i++) {
        	int count = 0;
        	double minSlope = Integer.MIN_VALUE;
        	for(int j = i+1; j <= N; j++) {
        		double slope = getSlope(i, arr[i], j, arr[j]);
        		if(minSlope < slope) {
        			minSlope = slope;
        			count++;
        		}
        	}
        	
        	minSlope = Integer.MAX_VALUE;
        	for(int j = i-1; j >= 1; j--) {
        		double slope = getSlope(i, arr[i], j, arr[j]);
        		if(minSlope > slope) {
        			minSlope = slope;
        			count++;
        		}
        	}
        	
        	if(maxCount < count) maxCount = count;
        }
        
        System.out.println(maxCount);   	
    }
    
    // 기울기 구하기
    // 1: 좌측에 있는 좌표, 2: 우측에 있는 좌표
    static double getSlope(int x1, int y1, int x2, int y2) {
    	return (double)(y2 - y1) / (x2 - x1);
    }
}