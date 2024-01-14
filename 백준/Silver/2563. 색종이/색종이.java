import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[][] arr = new int[100][100];
        int time = Integer.parseInt(br.readLine());
        int sum = 0;

        for (int i = 0; i < time; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            for (int j = width; j < width+10; j++) {
                for (int k = height; k < height + 10; k++) {
                    arr[j][k] = 1;
                }
            }
        }

        for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                sum += arr[j][k];
            }
        }

        br.close();

        System.out.println(sum);





    }


}