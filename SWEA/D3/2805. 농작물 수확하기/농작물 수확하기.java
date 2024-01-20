import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        for (int T = 1; T <= k; T++) { // TestCase 만큼 반복
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            int sum = 0;
            // 제시되는 배열 입력
            for (int r = 0; r < n; r++) {
                String b = br.readLine();
                for (int c = 0; c < n; c++) {
                    arr[r][c] = b.charAt(c) - '0';
                }
            }


            for (int r = 0; r < n; r++) { // 7x7 배열 가정
                if (r <= n / 2) { // r = 0 -> c = 3 ~ 3, n/2 = 3
                                  // r = 1 -> c = 2 ~ 4, n/2 = 3
                                  // r = 2 -> c = 1 ~ 5, n/2 = 3
                                  // r = 3 -> c = 0 ~ 6, n/2 = 3
                    for (int c = (n/2)- r; c <= (n/2) + r ; c++) {
                        sum += arr[r][c];}
                } else { // r = 4 -> c = 1 ~ 5, n/2 = 3, n = 7
                         // r = 5 -> c = 2 ~ 4, n/2 = 3, n = 7
                         // r = 6 -> c = 3 ~ 3, n/2 = 3, n = 7
                    for (int c = r - (n / 2); c <= ((n/2) * 3) - r; c++) {
                        sum += arr[r][c];
                    }
                }
            }
            System.out.printf("#%d %d\n", T, sum);
        }
    }
}