import java.io.*;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] arr = new int[N][M];
            int[] dr = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
            int[] dc = new int[]{1, 0, -1, 0, -1, 1, 1, -1};
            int maxNum = Integer.MIN_VALUE;
            int standard;
            int answer = 0;
            int nr, nc;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    int count = 0;
                    standard = arr[r][c];
                    for (int d = 0; d < 8; d++) {
                        nr = r + dr[d];
                        nc = c + dc[d];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] < standard) {
                            count++;
                        }
                    }
                    if(count >= 4) answer++;
                }
            }
            sb.append("#").append(t).append(" ").append(answer);
            System.out.println(sb);
        }
    }


}