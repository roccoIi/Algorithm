import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* [회고]
* 1. 문제를 읽으면서 필요하다고 생각했던 함수는 3가지다. 1) 경계조건, 2) 배열 회전시키기, 3) 회전연산의 모든 조합
* 2. 배열 전체가 경계조건이 아닌 특정 범위가 경계조건이여서 경계조건이 까다로웠다.
* 3. 매번 지도를 복사해야한다. 최대 50x50이라면 복사만 하다 시간 다갈지도 모르겠다.
* 3-1. 문제 그대로 지도를 복사해서 직접 돌리는 것보다 더 효율적인 방법이 있을것 같으니 그걸 알아봐야겠다.
* -------------------------------------------------------------------------------------------------
* 4. 아 설마 초기 배열은 최솟값 계산에서 제외인가..? 지금까지 포함했었는데
*/

public class Main {
    static int N, M, K, minValue;
    static boolean[] visit;
    static int[] seq;
    static int[][]  turnArr;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        minValue = Integer.MAX_VALUE;

        // 문제 배열 입력
        int[][] arr = new int[N+1][M+1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 배열 입력
        turnArr = new int[K][3];
        for (int r = 0; r < K; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++) {
                turnArr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        seq = new int[K];
        visit = new boolean[K];
        combination(0, arr);

        System.out.println(minValue);

    }

    static void turn(int k, int[][] map){
        int cr = turnArr[k][0];
        int cc = turnArr[k][1];
        int s = turnArr[k][2];

    // 총 s라는 범위만큼 테두리만 회전을 진행한다. 한칸씩 줄여가면서 테두리만 회전
     here:for (int i = s; i > 0; i--) {
            int nr = cr - i;
            int nc = cc - i;
            int start = map[nr][nc]; // 시작지점 숫자 저장

         // 델타배열로 사방탐색을 하는데, 여기서 주의할점은 nr값과 nc값이다.
         // nr값과 nc값을 진행시킨채로 경계조건 탐색을 하니 경계조건을 벗어난값이 계속 돌아왔다.
         // 만약 경계조건 통과를 못하고 다음 방향으로 진행되야한다면 델타배열이 적용되기 전 좌표를 가져와야했다.
         // 그러므로 d가 0 이상일때는 델타배열 이전 좌표로 돌려놓고 새로운 델타배열을 적용시켜 확인한다.
            for (int d = 0; d < 4; d++) {
                if (d > 0) {
                    nr -= dr[d - 1];
                    nc -= dc[d - 1];
                }
                nr += dr[d];
                nc += dc[d];
                while (check(cr, cc, nr, nc, i)) {
                    map[nr - dr[d]][nc - dc[d]] = map[nr][nc];
                    nr += dr[d];
                    nc += dc[d];
                    if (nr == cr - i && nc == cc - i && d == 3) { // 탐색지점이 원래지점으로 돌아왔을때는 저장해놨던 시작지점 값을 적용시켜야한다.
                        map[nr - dr[d]][nc - dc[d]] = start;
                        continue here;
                    }
                }
            }
     }
    }

    // 회전배열의 모든조합을 구한 후 모두 돌려본다.
    static void combination(int idx, int[][] arr){
        if(idx == K){
            // 지도복사
            int[][] tmp = new int[N+1][M+1];
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= M; c++) {
                    tmp[r][c] = arr[r][c];
                }
            }

            // 조합따라 계산
            for (int i = 0; i < K; i++) {
                turn(seq[i], tmp);
            }
            cal(tmp);
            return;
        }
        // 재귀를 통한 조합 생성
        for (int i = 0; i < K; i++) {
            if(visit[i]) continue;
            seq[idx] = i;
            visit[i] = true;
            combination(idx + 1, arr);
            visit[i] = false;
        }
    }
    // 계산 함수
    static void cal(int[][] tmp){
        for (int r = 1; r <= N; r++) {
            int sum = 0;
            for (int c = 1; c <= M; c++) {
                sum += tmp[r][c];
            }
            minValue = Math.min(minValue, sum);
        }
    }
    // 경계조건
    static boolean check(int cr, int cc, int nr, int nc, int ns){
        return nr <= (cr + ns) && nc <= (cc + ns) && nr >= (cr - ns) && nc >= (cc - ns);
    }
}