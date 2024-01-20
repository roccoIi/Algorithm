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
                    int a = arr[i] * arr[j];
                    if (increase(a)) {
                        sum = a;
                    }
                    if (sum > max) {
                        max = sum;
                    }
                }
            }
            System.out.printf("#%d %d\n", T, max);
        }
    }

    public static boolean increase(int number) {
        int minNum = Integer.MAX_VALUE;
        int count = 0;
        int b = number;
        while (number > 0) {
            number /= 10;
            count++;
        }
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