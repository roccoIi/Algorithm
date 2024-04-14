import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][M+1];
        int[][] dp = new int[N+1][M+1];

        // 주어진 조건 입력받기
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // (r, c)의 값 = 현재 위치값 + ( (r-1, c), (r, c-1), (r-1, c-1) 중 최댓값)
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                dp[r][c] = arr[r][c] + Math.max(dp[r-1][c], Math.max(dp[r][c-1], dp[r-1][c-1]));
            }
        }
        
        System.out.println(dp[N][M]);
    }
}