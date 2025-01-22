import java.io.*;
import java.util.*;

public class Main {
	static int N, M, arr[][], dp[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		dp = new int[N][M];
		
		//dp배열 초기화
		for(int[] miniDP : dp) {
			Arrays.fill(miniDP,	-1);
		}
		
		// 입력값 받기
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		findRoute(0, 0);
		System.out.println(dp[0][0]);

	}
	
	static int findRoute(int r, int c) {
		if(r == N-1 && c == M-1) return 1;
		
		if(dp[r][c] != -1) return dp[r][c];
		
		dp[r][c] = 0;
		for(int d = 0; d < 4; d++) {
			int nr = r + dir[0][d];
			int nc = c + dir[1][d];
			
			if(!checkBoundary(nr, nc)) continue;
			
			if(arr[r][c] > arr[nr][nc]) {
				dp[r][c] += findRoute(nr, nc);
			}
		}
		
		return dp[r][c];
	}
	
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}