import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    *  <풀이방식>
    *   1. 그림으로 그려서 가로기준으로 상황이 펼처질때, 세로 기준으로 상황이 펼처질때를 || 또는 && 로 나눈다.
    *   2. 그 다음 사각형의 위치가 서로 바뀌어서 펼처질때를 || 로 나눈다.
    *   3. 가장 경우의 수가 적은 d 부터 d -> c -> b -> a 순서대로 if문을 전개한다.
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A_x = Integer.parseInt(st.nextToken());
            int A_y = Integer.parseInt(st.nextToken());
            int B_x = Integer.parseInt(st.nextToken());
            int B_y = Integer.parseInt(st.nextToken());
            int C_x = Integer.parseInt(st.nextToken());
            int C_y = Integer.parseInt(st.nextToken());
            int D_x = Integer.parseInt(st.nextToken());
            int D_y = Integer.parseInt(st.nextToken());

            if ((A_x > D_x || C_x > B_x) || (A_y > D_y || C_y > B_y)) {
                System.out.println('d');
            } else if ((A_x == D_x || C_x == B_x) && (A_y == D_y || C_y == B_y)) {
                System.out.println('c');
            } else if ((A_x == D_x || C_x == B_x) || (A_y == D_y || C_y == B_y)) {
                System.out.println('b');
            } else {
                System.out.println('a');
            }
        }
    }
}