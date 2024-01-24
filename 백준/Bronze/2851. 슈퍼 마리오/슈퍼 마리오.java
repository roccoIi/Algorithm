import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        int differ = Integer.MAX_VALUE;
        int minDiffer = Integer.MAX_VALUE;
        int maxNum = 0;
        int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < 10; i++) {
            sum += arr[i];
            differ = Math.abs(100 - sum); // 차이 절댓값

            if (differ <= minDiffer) { //최솟값 찾기
                minDiffer = differ;
                maxNum = sum; // 차이 절댓값이 같아도 가장 마지막으로 업데이트 한 수는 그 전보다 큰 수이다.
            }
        }
        System.out.println(maxNum);
    }
}