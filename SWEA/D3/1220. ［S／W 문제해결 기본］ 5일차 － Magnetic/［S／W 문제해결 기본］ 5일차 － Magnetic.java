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
                        if (r != n-1) { // 경계선 끝에 있으면 아무런 영향을 주지 않으니 제외
                            int temR = r;
                            while (temR != n-1) { // 위와 같은 이유로 제외
                                if (arr[temR + 1][c] == 0) { // 다음 행이 0이라면 아무런 영향이 없으므로 계속 탐색
                                    temR++;
                                } else if (arr[temR + 1][c] == 1) { // 다음행이 1이라면 중복방지를 위해 중단
                                    break;
                                } else { // 다음행이 2라면 서로 충돌하게 되므로 카운팅+1 후 중단
                                    count++;
                                    break;
                                }
                            }
                        }
                    } else if (arr[r][c] == 2) { //현재 좌표 (r,c)가 2일 경우
                        if (r != 0) {
                            int temR = r;
                            while (temR != 0) { // 경계선 끝에 있으면 아무런 영향을 주지 않으니 제외
                                if (arr[temR - 1][c] == 0) { // 이전 행이 0이라면 아무런 영향이 없으므로 계속 탐색
                                    temR--;
                                } else if (arr[temR - 1][c] == 2) { // 이전행이 2라면 중복방지를 위해 중단
                                    break;
                                } else { // 이전행이 1이라면 서로 충돌하게 되므로 카운팅+1 후 중단
                                    count++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", T, count/2); // 위에서 내려올때 한번 아래서 올라갈때 한번 총 2번 카운트되므로 /2를 한다.
        }
    }
}