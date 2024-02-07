import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	// 입력값 받아오기 및 etc..
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int[] temp = new int[4];
    	int[][] arr = new int[102][102];
    	int count = 0;
    	
    	for(int i = 0; i < 4; i++) {
    		// 주어지는 사각형 1개씩 배열에 담기
			StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < 4; j++) {
            	temp[j] = Integer.parseInt(st.nextToken());
    		}
    		// 전체 배열에서 사각형이 차지하는 부분 1로 칠하기
        	for(int j = temp[0]; j < temp[2]; j++) {
        		for(int k = temp[1]; k < temp[3]; k++) {
        			arr[j][k] = 1;
        		}
        	}
    	}
    	
    	// 전체 배열에서 1이 차지하는 부분 카운팅
    	for(int r = 0; r < arr.length; r++) {
    		for(int c = 0; c < arr.length; c++) {
    			if(arr[r][c] == 1) count++;
    		}
    	}
    	
    	System.out.println(count);
    }
}