import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[100][100];
        int count = 0;

        // 배열에 제시되는 색종이 칠하기
        int paper = Integer.parseInt(br.readLine());
        for (int i = 0; i < paper; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()); // 가로로 얼만큼
            int w = Integer.parseInt(st.nextToken()); // 세로로 얼만큼

            for (int r = w; r < w + 10; r++) {
                for (int c = h; c < h + 10; c++) {
                    arr[r][c] = 1;
                }
            }
        }
        br.close();

        // 델타배열
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 색칠된 곳 탐색하기
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr.length; c++) {
                if (arr[r][c] == 1) { // 해당 위치가 1일때
                    for (int j = 0; j < 4; j++) { // 4방탐색을 진행
                        int nr = r + dr[j];
                        int nc = c + dc[j];
                        if (nr < 0 || nr > 99 || nc < 0 || nc > 99) { // 붐위를 넘어선다면 경계면 => 변의 갯수 +1
                            count++;
                        }
                        if (nr >= 0 && nr <= 99 && nc >= 0 && nc <= 99) { // 범위 안에 있다면
                            if(arr[nr][nc] == 0) // 이동한 위치가 0이라면 경계면 => 변의 갯수 +1
                                count++;
                        }

                    }
                }
            }
        }
        System.out.println(count);
    }
}