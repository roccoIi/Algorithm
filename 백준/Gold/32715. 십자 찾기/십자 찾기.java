import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, map[][], dp_row[][], dp_column[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        
        map = new int[N][M];
        dp_row = new int[N][M];
        dp_column = new int[N][M];
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		map[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 슬라이딩 윈도우 (가로)
        for(int r = K; r < N - K; r++) {
        	
        	// 첫번째 시작값 설정
        	for(int c = 0; c <= (K*2); c++) {
        		dp_row[r][K] += map[r][c];
        	}
        	
        	// 이후 슬라이딩 윈도우 시작
        	for(int c = K+1; c < M - K; c++) {
        		dp_row[r][c] = dp_row[r][c-1] - map[r][c-K-1] + map[r][c+K];
        	}
        }
        
      
        // 슬라이딩 윈도우 (세로)
        for(int c = K; c < M - K; c++) {
        	
        	// 첫번째 시작값 설정
        	for(int r = 0; r <= (K*2); r++) {
        		dp_column[K][c] += map[r][c];
        	}
        	
        	// 이후 슬라이딩 윈도우 시작
        	for(int r = K+1; r < N - K; r++) {
        		dp_column[r][c] = dp_column[r-1][c] - map[r-K-1][c] + map[r+K][c];
        	}
        }
        
        // 십자가에서 중앙값은 2번 더했기 때문에 십자가라면 dp의 숫자가 4K+2 여야한다.
        int answer = 0;
        for(int r = 0; r < N; r++) {
        	for(int c = 0; c < M; c++) {
        		if(dp_column[r][c] + dp_row[r][c] == (4*K + 2)) answer++;
        	}
        }
        
        System.out.println(answer);
    }
}