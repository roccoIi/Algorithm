import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    /*
    * [풀이 과정]
    * 1. 처음엔 비트연산 관련 문제일 줄 알고 비트연산자를 어떻게 써야할지 백만년 고민하다가 결국 포기
    * 2. 단순하게 배열을 통해서 풀기로 했다.
    * 3. 0부터 9까지 2진수 계산하기는 귀찮아서 배열로 만들었다. (2진수로 다 계산해서 해당 값이면 0~9 대입해도 가능할 것 같다.)
    * 4. 주어진 암호문을 행우선탐색, 역순으로 탐색했다. 모든 코드가 1로 끝나니 1을 찾으면 그때부터 56자리 탐색한다.
    * 5. 앞에서부터 7자리씩 새로운 배열에 넣음과 동시에 그 배열이 10개의 코드 배열중 어떠한 것과 일치하는지 확인한다.
    *       => 0~9의 숫자가 index 0~9에 담겨있다.
    * 6. 해독한 암호를 password 배열에 넣는다.
    * 7. 올바른 암호코드인지 아닌지 확인 후 값을 출력한다.
    */

    static int[][] code = {{0, 0, 0, 1, 1, 0, 1}, // 0~9 까지의 코드
                    {0, 0, 1, 1, 0, 0, 1},
                    {0, 0, 1, 0, 0, 1, 1},
                    {0, 1, 1, 1, 1, 0, 1},
                    {0, 1, 0, 0, 0, 1, 1},
                    {0, 1, 1, 0, 0, 0, 1},
                    {0, 1, 0, 1, 1, 1, 1},
                    {0, 1, 1, 1, 0, 1, 1},
                    {0, 1, 1, 0, 1, 1, 1},
                    {0, 0, 0, 1, 0, 1, 1},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for(int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] arr = new int[R][C];

            // 주어진 input값을 배열에 넣는다.
            for(int r = 0; r < R; r++) {
                String str = br.readLine();
                for(int c = 0; c < C; c++) {
                    arr[r][c] = str.charAt(c) - '0';
                }
            }

            // 1을 찾으면 해당 코드를 str에 넣어 하나의 문장으로 뽑는다.
            String str = "";
            end: for(int r = 0; r < R; r++) {
                for(int c = C-1; c >= 0; c--) {
                    if(arr[r][c]  == 1) {
                        for(int i = c-55; i <= c; i++) {
                            str += arr[r][i];
                        }
                        break end;
                    }
                }
            }
            int[] number = new int[7]; // 암호코드 한 자리의 구성은 7자리
            int[] password = new int[8]; // 암호코드는 8자리

            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 7; i++) {
                    number[i] = str.charAt((7*j) + i) - '0'; //구성품을 number 배열에 넣고
                }
                for (int k = 0; k < 10; k++) { // 그 구성품들이 어떤 암호코드와 일치하는지 확인 후 암호코드 각 자리수에 넣는다.
                    if(findNumber(number, k)) {
                        password[j] = k;
                        break;
                    }
                }
            }
            
            int answer = 0;
            if(check(password) == 0){ // 올바른 암호코드라면 출력
                for (int i = 0; i < 8; i++) {
                    answer += password[i];
                }
            }

            System.out.printf("#%d %d\n", t, answer);
        }
    }
    
    // 각 암호코드가 어떤 숫자를 의미하는지 확인
    static boolean findNumber(int[] arr, int num){
        for (int i = 0; i < 7; i++) {
            if(arr[i] != code[num][i]) return false;
        }
        return true;
    }

    // 올바른 암호코드인지 아닌지 확인한다.
    static int check(int[] arr){
        return (((arr[0] + arr[2] + arr[4] + arr[6]) *3) + (arr[1] + arr[3] + arr[5] + arr[7])) % 10;
    }
}