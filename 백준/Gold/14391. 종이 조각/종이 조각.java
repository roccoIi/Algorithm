import java.io.*;
import java.util.*;

public class Main {
	static int R, C, maxNum, arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	arr = new int[R][C];
    	maxNum = -1;

    	
    	for(int r = 0; r < R; r++) {
    		String[] str = br.readLine().split("");
    		for(int c = 0; c < C; c++) {
    			arr[r][c] = Integer.parseInt(str[c]);
    		}
    	}
    	
    	for(int i = 0; i < (1<<R*C); i++) {
    		int totalNum = 0;

    		// 가로 계산    		
    		for(int r = 0; r < R; r++) {
    			int tmp = 0;
    			for(int c = 0; c < C; c++) {
    				int bit = r * C + c;
    				if((i & (1 << bit)) == 0) {
    					tmp = tmp * 10 + arr[r][c];
    				} else {
    					totalNum += tmp;
    					tmp = 0;
    				}
    			}
    			totalNum += tmp;
    		}
    		
    		
    		// 세로 계산
    		for(int c = 0; c < C; c++) {
    			int tmp = 0;
    			for(int r = 0; r < R; r++) {
    				int bit = r * C + c;
    				if((i & (1 << bit)) != 0){
    					tmp = tmp * 10 + arr[r][c];
    				} else {
    					totalNum += tmp;
    					tmp = 0;
    				}
    			}
    			totalNum += tmp;
    		}
    		maxNum = maxNum < totalNum ? totalNum : maxNum;
    	}
    	System.out.println(maxNum);
    }
}