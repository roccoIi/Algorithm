import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken()); // 주어진 랜선의 개수
        int n = Integer.parseInt(st.nextToken()); // 목표로 하는 랜선의 개수
        long[] arr = new long[k]; // 랜선의 길이를 저장할 배열

        long max = 0; // 랜선의 최대 길이
        for (int i = 0; i < k; i++) {
            arr[i] = Long.parseLong(br.readLine()); // 랜선의 길이 입력
            max = Math.max(max, arr[i]); // 최대 길이 갱신
        }

        long left = 1;
        long right = max;

        while (left <= right) {
            long mid = (left + right) / 2; // 중간 길이 설정
            int count = countLines(arr, mid); // 중간 길이로 만들 수 있는 랜선의 개수 계산

            if (count >= n) {
                left = mid + 1; // 랜선의 길이를 늘려서 더 많은 랜선을 만들 수 있는지 확인
            } else {
                right = mid - 1; // 랜선의 길이를 줄여서 더 적은 랜선을 만들어야 하는지 확인
            }
        }

        System.out.println(right); // right 값을 출력
    }

    // 주어진 길이로 만들 수 있는 랜선의 개수를 계산하는 메서드
    private static int countLines(long[] arr, long length) {
        int count = 0;
        for (long l : arr) {
            count += l / length; // 랜선의 길이를 주어진 길이로 나누어 몇 개의 랜선을 만들 수 있는지 계산
        }
        return count;
    }
}