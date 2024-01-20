import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        for (int T = 1; T <= k; T++) { // TestCase 만큼 반복
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][n];
            int sum = 0; // q와 정확히 일치할때마다 +1
            int cnt = 0; // 연속되는 1의 값 누적시킬 공간

            // 제시된 배열 만들기
            for (int r = 0; r < n; r++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for (int c = 0; c < n; c++) {
                    arr[r][c] = Integer.parseInt(st2.nextToken());
                }
            }

            // 행 우선 탐색
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (arr[r][c] == 0) { // 0을 만났을때 이전까지 1의 갯수를 카운팅
                        if (cnt == q) { // 그것이 제시된 q와 같았을 경우 총 개수를 +1 후 카운팅 초기화
                            sum++;
                            cnt = 0;
                        } else { // 아니라면 카운팅만 초기화
                            cnt = 0;
                        }
                    } else if (c == n-1 && arr[r][c] == 1) { // 배열의 끝에 도달했고 지금 숫자가 1일 경우
                        cnt++;
                        if (cnt == q) {
                            sum++;
                            cnt = 0;
                        } else {
                            cnt = 0;
                        }
                    } else { // 그냥 중간에 1을 지날 경우에는 카운팅만 +1
                        cnt++;
                    }
                }
                cnt = 0;
            }
            // 열 우선 탐색
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (arr[c][r] == 0) {
                        if (cnt == q) {
                            sum++;
                            cnt = 0;
                        } else {
                            cnt = 0;
                        }
                    } else if (c == n-1 && arr[c][r] == 1) {
                        cnt++;
                        if (cnt == q) {
                            sum++;
                            cnt = 0;
                        } else {
                            cnt = 0;
                        }
                    } else {
                        cnt++;
                    }
                }
                cnt = 0;
            }
            System.out.printf("#%d %d\n", T, sum);
        }
    }
}