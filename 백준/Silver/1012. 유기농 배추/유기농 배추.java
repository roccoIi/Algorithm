import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int C, R, K;
    static int[][] map;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static class Node{
        int r, c;

        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[R][C];
            int cnt = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[b][a] = 1;
            }

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if(map[r][c] == 1){
                        bfs(r, c);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }// testCase 종료
    }// main 종료

    static void bfs(int a, int b){
        Queue<Node> q = new LinkedList<>();
        map[a][b] = 0;
        q.offer(new Node(a, b));

        while(!q.isEmpty()){
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];

                if(check(nr, nc) && map[nr][nc] == 1){
                    map[nr][nc] = 0;
                    q.offer(new Node(nr, nc));
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}