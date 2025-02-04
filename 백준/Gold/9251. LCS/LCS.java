import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str_1 = br.readLine();
		String str_2 = br.readLine();
		
		int[][] dp = new int[str_2.length()+1][str_1.length()+1];
		
		for(int c = 1; c <= str_1.length(); c++) {
			for(int r = 1; r <= str_2.length(); r++) {
				if(str_1.charAt(c-1) == str_2.charAt(r-1)) {
					dp[r][c] = dp[r-1][c-1] + 1;
				} else {
					dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
				}
			}
		}
		
		System.out.println(dp[str_2.length()][str_1.length()]);
    }
}