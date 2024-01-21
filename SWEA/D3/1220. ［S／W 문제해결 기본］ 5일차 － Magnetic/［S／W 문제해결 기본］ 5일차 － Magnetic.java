import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) { // TestCase 만큼 반복
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            int count = 0;

            // 제시되는 배열 입력
            for (int r = 0; r < n; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int c = 0; c < n; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 행 우선 탐색으로 돌아가면서 확인
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (arr[r][c] == 1) { //현재 좌표 (r,c)가 1일 경우
                        if (r != 99) { // 경계선 끝에 있으면 아무런 영향을 주지 않으니 제외
                            int a = upAndDown(r, c, 1, 99, 1, arr);
                            count += a;
                        }
                    } else if (arr[r][c] == 2) { //현재 좌표 (r,c)가 2일 경우
                        if (r != 0) {
                            int a = upAndDown(r, c, -1, 0, 2, arr);
                            count += a;
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", T, count / 2); // 위에서 내려올때 한번 아래서 올라갈때 한번 총 2번 카운트되므로 /2를 한다.
        }
    }

    static int upAndDown(int row, int column, int plus, int maxOrMin, int sameNum, int[][] arr) {
        while (row != maxOrMin) { // 배열의 경계면 확인
            if (arr[row + plus][column] == 0) {
                row = row + plus;
            } else if (arr[row + plus][column] == sameNum) { // 다음/이전 행이 같은 숫자라면 중복방지를 위해 중단
                return 0;
            } else { // 이전행이 1이라면 서로 충돌하게 되므로 카운팅+1 후 중단
                return 1;
            }
        }
        return 0;
    }
}