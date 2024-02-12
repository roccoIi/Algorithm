import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer, nr, nc;
    static int[] dr = {0, -1, 0, 1}; static int[] dc = {-1, 0, 1, 0};
    static int[][] arr, visit;
    public static void main(String[] args) throws IOException {
        // 자료 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        arr = new int[N+1][M+1]; // 주어진 배열
        visit = new int[N+1][M+1]; // 방문 배열

        // 주어진 배열 생성
        for (int r = 1; r <= N; r++) {
            String str = br.readLine();
            for (int c = 1; c <= M; c++) {
                arr[r][c] = str.charAt(c-1) - '0';
            }
        }
        //bfs 진행
        bfs(1, 1, 1);
    }

    public static void bfs(int startX, int startY, int depth) { // (1, 1, 1) 입력되어야 함
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY, depth}); // (x좌표, y좌표, 깊이)

        while (!q.isEmpty()) {
            int[] num = q.poll();
            visit[num[0]][num[1]] = 1; // 꺼낸 좌표 방문체크

            if(num[0] == N && num[1] == M) { // 꺼낸 좌표가 (N,M) 이라면 즉시 종료
                answer = num[2];
                break;
            }

            for (int d = 0; d < 4; d++) { // 사방탐색
                nr = num[0] + dr[d];
                nc = num[1] + dc[d];
                if (check(nr, nc)){
                    visit[nr][nc] = 1;
                    q.offer(new int[]{nr, nc, num[2]+1});
                }
            }
        }
        System.out.println(answer);
    }

    public static boolean check(int r, int c) { // 경계조건 및 지도에 길이 맞는가 + 방문한 적이 없는가
        return r > 0 && r <= N && c > 0 && c <= M && visit[r][c] == 0 && arr[r][c] == 1;
    }
}