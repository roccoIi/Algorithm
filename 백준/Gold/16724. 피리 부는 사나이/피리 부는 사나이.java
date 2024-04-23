import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [회고]
 * 1. 결국 화살표가 가리키는 방향의 끝이 safeZone이어야한다.
 * 2. 또는 순환하는 구조일때 그 길 중 한곳이 safeZone이여야한다.
 * 3. 이 두가지를 dfs로 길따라 가다보면 safeZone을 구할 수 있을 것이다.
 * ---(수정)--------
 * 4. dfs로 중간에 합류해도 결국 이전에 탐색했던 한 라인이라면 넘어가야했다.
 * 5. oneLine 배열을 추가해서 한 라인임을 체크하고 
 * 6. 해당 배열이 칠해져있지 않으면 거기가 마지막이다. safeZone 하나 올리고 재귀 나오면서 다 칠해준다.
 *
 * */

public class Main {
    static int R, C, safeZone, map[][];
    static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static boolean visit[][], oneLine[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visit = new boolean[R][C];
        oneLine = new boolean[R][C];

        // 4방탐색 배열 인덱스에 맞춰서 상하좌우 숫자를 지도에 넣는다.
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                char tmp = str.charAt(c);
                if(tmp == 'D') map[r][c] = 1;
                else if (tmp == 'L') map[r][c] = 2;
                else if (tmp == 'R') map[r][c] = 3;
            }
        }

        // 전체지도 돌면서 미방문 지역만 dfs 돈다.
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(!visit[r][c]) dfs(r, c);
            }
        }

        // safeZone 출력
        System.out.println(safeZone);

    }

    static void dfs(int r, int c) {
        // 현재위치가 곧 방향값이다.
        int nr = r + dir[0][map[r][c]];
        int nc = c + dir[1][map[r][c]];
        visit[r][c] = true;

        // 미방문 지역이라면 재귀
        if(!visit[nr][nc]) dfs(nr,nc);
        else {
            // 방문했던 곳인데 아직 라인체크 안되어있다면
            // 여기까지가 한 라인이라는 의미이다.
            if(!oneLine[nr][nc]) safeZone++;
        }

        // 재귀 하나씩 나오면서 현재 위치 방문체크한다. 한 라인이다.
        oneLine[r][c] = true;
    }

}