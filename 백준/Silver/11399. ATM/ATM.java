import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());
        int[] arr = new int[people];
        int sum = 0;
        int total = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < people; i++) { // 입력받은 사람들 배열 생성
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr); // 오름차순 정렬

        for (int i = 0; i < people; i++) { // 배열 읽으면서 시간 누적
            sum += arr[i];
            total += sum;
        }
        System.out.println(total);
    }
}