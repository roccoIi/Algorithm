import java.io.*;
import java.util.*;

/**
 * 문제 기록
 * - 첫 번째 줄에 테스트 케이스의 수 T
 * - 각 테스트 케이스의 첫 번째 줄에 하나의 정수 N(1 ≤ N ≤ 300) (표의 크기가 N*N)
 * - N개의 줄의 i번째 줄에는 길이가 N인 문자열
 * - 이 중 j번째 문자는 표에서 i번째 행 j번째 열에 있는 칸이 지뢰가 있는 칸인지 아닌지를 나타낸다.
 * - ‘*’이면 지뢰O, ‘.’이면 지뢰X
 */


public class Solution {
    static int arr[][], N, count;
    static int[][] dir = {{-1, -1, -1, 0, 0, 1, 1, 1}, {-1, 0, 1, -1, 1, -1, 0, 1}};
    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int T = 1; T <= testCase; T++) {
            sb.append("#").append(T).append(" ");
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            count = 0;

            // 입력값 받기
            for (int r = 0; r < N; r++) {
                String str = br.readLine();
                for (int c = 0; c < N; c++) {
                    // 지뢰가 없다면 -1, 지뢰가 있다면 -2
                    if(str.charAt(c) == '.') arr[r][c] = -1;
                    else arr[r][c] = -2;
                }
            }

            // 0인 칸들 모두 클릭해서 한번에 열어버리기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(arr[r][c] != -1) continue; //지뢰라면 패쓰
                    if(isZero(r, c)){
                        click(r, c);
                        count++;
                    }
                }
            }

            // 열리지 않는 남은 칸들 열기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(arr[r][c] == -1) count++;
                }
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    } //main End

    private static void click(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(r, c));
        arr[r][c] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            arr[node.r][node.c] = 0;
            for (int d = 0; d < 8; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || arr[nr][nc] != -1) continue;
                if(isZero(nr, nc)) q.add(new Node(nr, nc));
                arr[nr][nc] = 0;
            }
        }
    }

    private static boolean isZero(int r, int c) {
        for (int d = 0; d < 8; d++) {
            int nr = r + dir[0][d];
            int nc = c + dir[1][d];
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if(arr[nr][nc] == -2) return false;
        }
        return true;
    }

}