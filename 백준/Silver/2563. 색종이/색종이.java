import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 기본이 되는 도화지 그리기 (100 x 100 사이즈)
        int[][] arr = new int[100][100];
        int time = Integer.parseInt(br.readLine());
        int sum = 0;

        // 주어지는 색종이의 수만큼 반복
        for (int i = 0; i < time; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
        // 주어지는 위치에 붙이는 색종이를 0 -> 1 로 표현한다. (중복되어도 1 -> 1 이기 때문에 영향을 받지 않는다.)
            for (int j = width; j < width+10; j++) {
                for (int k = height; k < height + 10; k++) {
                    arr[j][k] = 1;
                }
            }
        }
        // 도화지(배열)에 칠해진 1의 개수(색종이)를 다 더한다.
        for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                sum += arr[j][k];
            }
        }

        br.close();

        System.out.println(sum);
    }
}