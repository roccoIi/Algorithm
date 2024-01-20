import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        for (int T = 1; T <= k; T++) { // TestCase 만큼 반복
            int N = Integer.parseInt(br.readLine());
            int sum = Integer.MIN_VALUE;
            int max = -1;
            // 제시되는 배열 만들어 넣기
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    // 각 숫자별로 곱한 숫자를 아래 함수에 넣어서 확인
                    int a = arr[i] * arr[j];
                    if (increase(a)) {
                        sum = a;
                    }
                    if (sum > max) { //최댓값 구하기
                        max = sum;
                    }
                }
            }
            System.out.printf("#%d %d\n", T, max);
        }
    }

    public static boolean increase(int number) { // 해당 숫자가 [단조 증가하는 수]인지 아닌지 판단하는 함수
        int minNum = Integer.MAX_VALUE;
        int count = 0;
        int b = number;
        // 숫자의 자릿수 구하기
        while (number > 0) {
            number /= 10;
            count++;
        }
        // 숫자의 자리수만큼 돌면서 해당 숫자가 오름차순으로 되어있는가 확인
        // 오름차순(ex. 1234) 이면 true, 아니라면 false 반환
        for (int i = 0; i < count; i++) {
            if (b % 10 <= minNum) {
                minNum = b % 10;
                b /= 10;
            } else {
                return false;
            }
        }
        return true;
    }
}
