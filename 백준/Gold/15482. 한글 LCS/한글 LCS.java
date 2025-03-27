import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str_1 = br.readLine();
        String str_2 = br.readLine();
        
        int[][] dp = new int[str_1.length()+1][str_2.length()+1];
        for(int r = 1; r <= str_1.length(); r++) {
        	for(int c = 1; c <= str_2.length(); c++) {
        		if(str_1.charAt(r-1) == str_2.charAt(c-1)) {
        			dp[r][c] = dp[r-1][c-1] + 1;
        		} else {
        			dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
        		}
        	}
        }
        
        System.out.println(dp[str_1.length()][str_2.length()]);
    }
}