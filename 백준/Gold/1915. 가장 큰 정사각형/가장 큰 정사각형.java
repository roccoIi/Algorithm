import java.io.*;
import java.util.*;

public class Main {
	static int R, C, map[][], dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		dp = new int[R][C];
		int maxNum = 0;
		for(int r = 0; r < R; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(str[c]);
				if(map[r][c] == 1) {
					if(r == 0) dp[r][c] = 1;
					if(c == 0) dp[r][c] = 1;
					maxNum = dp[r][c] > maxNum ? dp[r][c] : maxNum;
				}
			}
		}
		
		
		for(int r = 1; r < R; r++) {
			for(int c = 1; c < C; c++) {
				if(map[r][c] == 0) continue;
				
				if(dp[r-1][c-1] > 0 && dp[r-1][c] > 0 && dp[r][c-1] > 0) {
					dp[r][c] = Math.min(Math.min(dp[r-1][c-1], dp[r-1][c]), dp[r][c-1]) + 1;
				} else {
					dp[r][c] = 1;
				}
				
				maxNum = dp[r][c] > maxNum ? dp[r][c] : maxNum;
			}
		}
		
		System.out.println(maxNum * maxNum);
	}
}