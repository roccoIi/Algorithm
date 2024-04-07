import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* [회고]
* 1. 문제에 주어진 조건대로만 작성하면 쉽게 해결되는 문제다
* 2. 단지 바라보는 방향의 뒷쪽으로 이동하는 것과 반시계방향으로 90도 회전시키는 것이 관건일 것 같다.
* 3. 바라보는 방향 뒷쪽으로 이동하는 것은 임시 방향변수를 생성 후 (dir+2)%4 로 해결
* 4. 반시계방향으로 90도 회전은 (dir+3)%4로 해결했다.
*/


public class Main {
    static int N, M, cnt;
    static int[][] map;
    static boolean[][] visit; // true는 쓰레기를 치운적이 있고 false는 쓰레기 치운적이 없다.
    static int[][] direction = {{-1, 0, 1, 0}, // 델타배열
                                {0, 1, 0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        cnt = 0;

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cleaning(r, c, dir);

        System.out.println(cnt);
    }

    static void cleaning(int r, int c, int dir) {
        while(map[r][c] == 0){
            // 빈칸에 방문하지 않았다면(쓰레기를 치우지 않았다면 쓰레기 치운다.)
            if(map[r][c] == 0 && !visit[r][c]){
                cnt++;
                visit[r][c] = true;
            }

            if(find(r, c)){ // 주변에 쓰레기가 하나라도 있을 때
                dir = (dir+3) % 4; // 반시계방향 90도 회전
                int nr = r + direction[0][dir];
                int nc = c + direction[1][dir];
                if(map[nr][nc] == 0 && !visit[nr][nc]){ // 해당 방향이 비어있고 쓰레기치운적 없으면 진행
                    r = nr;
                    c = nc;
                }
            } else{ // 주변에 아무런 쓰레기가 없을 때
                int back = (dir + 2) % 4; // 뒷쪽
                if(map[r + direction[0][back]][c + direction[1][back]] == 1) return; // 뒤가 벽이면 바로 종료
                r += direction[0][back]; // 그게 아니면 뒤로 이동
                c += direction[1][back];
            }
        }
    }

    static boolean find(int r, int c){
        // 사방탐색하여 한곳이라도 비어있는 칸에 쓰레기가 있으면 true 반환, 없으면 false;
        for (int d = 0; d < 4; d++) {
            int nr = r + direction[0][d];
            int nc = c + direction[1][d];
            if(map[nr][nc] == 0 && !visit[nr][nc]) return true;
        }
        return false;
    }
}