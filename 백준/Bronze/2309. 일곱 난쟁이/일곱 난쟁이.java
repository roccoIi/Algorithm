import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int sum = 0;

        for (int i = 0; i < 9; i++) { // 난쟁이 배열 생성
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i]; //총 키의 합
        }

        Arrays.sort(arr); // 난쟁이 오름차순 정렬
        out :for (int i = 0; i < 8; i++) { // 총 키에서 2명 뺐을때 100이 되는 경우 계산
            for (int j = i + 1; j < 9; j++) {
                if (sum - arr[i] - arr[j] == 100) { // 해당 스파이 2명 키 제거(1000)
                    arr[i] = 1000;
                    arr[j] = 1000;
                    break out;
                }
            }
        }
        for (int i : arr) { //양수만 출력
            if(i < 1000)
                System.out.println(i);
        }
    }
}