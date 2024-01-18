import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++) {
            int a = Integer.parseInt(br.readLine());
            int[][] arr = new int[a][a];
            
            // 제시되는 배열 만들기
            for(int r = 0; r<a;r++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c<a;c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            System.out.printf("#%d\n", i);
            
            for(int r = 0; r < a; r++) {
                for(int c = 0; c < a; c++) {
                    System.out.print(arr[a-c-1][r]);
                }
                System.out.print(" ");
                for(int c = 0; c<a; c++) {
                    System.out.print(arr[a-r-1][a-c-1]);
                }
                System.out.print(" ");
                for(int c = 0; c < a; c++) {
                    System.out.print(arr[c][a-r-1]);
                }
                System.out.println();
            }
        }
	}
}