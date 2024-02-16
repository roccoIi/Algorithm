import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	
    public static void main(String[] args) throws IOException, FileNotFoundException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
    	int testCase = Integer.parseInt(br.readLine());
    	
    	for(int t = 1; t <= testCase; t++) {
    		st = new StringTokenizer(br.readLine());
    		int N = Integer.parseInt(st.nextToken());
    		int M = Integer.parseInt(st.nextToken());
    		int[][] flag = new int[N][3];
    		int count = 0;
    		int stair = 0;
    		int minNum = Integer.MAX_VALUE;
    		  		
  		
    		// color 개수 표 제작
    		for(int r = 0; r < N; r++) {
    			String str = br.readLine();
    			for(int c = 0; c < M; c++) {
    				if(str.charAt(c) == 'W') {
    					flag[r][0]++;
    				} else if(str.charAt(c) == 'B') {
    					flag[r][1]++;
    				} else {
    					flag[r][2]++;
    				}
    			}
    		}
    		

    		for(int i = 0; i < N-2; i++) {
    			for(int o = N-2-i; o >=1; o--) {
    				count = 0;
    				stair = 0; // 칠한위치 바로 다음부터 다음 색 칠하기
    				// 하얀색 칠하기
    				for(int w = 0; w < i+1; w++) {
    					count += M - flag[w][0];
    					stair++;
    				}
    				
    				// 파란색 칠하기
    				int checkPoint = stair;
					for(int b = stair; b < checkPoint + o; b++) {
						count += M - flag[b][1];
						stair++;
					
					// 빨간색 칠하기
					}
					for(int r = stair; r < N; r++) {
						count += M - flag[r][2];
					}
    					
					if(count < minNum) minNum = count;
    			}
    		}
    		
    		System.out.printf("#%d %d\n", t, minNum);

    	}
        
    }

}