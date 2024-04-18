import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * [회고]
 * 1. 여러가지 모양을 탐색해야 했지만 ㅗ 를 제외한 나머지 도형들은 모두 한 점에서 쭉 뻗어나간 모양인것을 확인하고 dfs로 탐색했다.
 * 2. ㅗ 모양은 먼저 십자가 모양을 구한 뒤, 한 방향씩 빼가면서 도형을 탐색했다.
 * -------------------------------------(수정)--------------------------------------------------
 *    2-1. 십자가모양으로 더할때 더한 갯수가 5개라면 => 사방을 돌아가면서 한방향씩 빼보고 최댓값 갱신한다.
 *    2-2. 더한 갯수가 4개라면 => 이미 ㅗ 모양으로만 더해졌기 때문에 그 자체로 최댓값을 갱신한다.
 *    2-3. 더한 갯수가 3개라면 => ㅗ 모양에 부합하지 못하므로 테트로미노가 들어가지 못한다 PASS
 * --------------------------------------------------------------------------------------------
 * 3. 모든 경우의 수 진행 후 최댓값 반환
 */

public class Main {
    static int N, M, maxNum, map[][];
    static int[][] dir = {{1, -1, 0, 0}, {0, 0, 1, -1}};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maxNum = Integer.MIN_VALUE;

        map = new int[N][M];
        visit = new boolean[N][M];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 배열 전체를 탐색하면서 모든 테트로미노의 값을 구하고 그 중 최댓값을 구한다.
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                dfs(r, c, 0, 0);
                cross(r, c);
            }
        }

        System.out.println(maxNum);
    }// main End

    // 4종류의 테트로미노 탐색 (ㅗ 모양 제외)
    static void dfs(int r, int c, int idx, int sum) {
        // 정사각형 4개 연달아서 모았다면 해당 테트로미노의 합과 최댓값을 구한다.
        if(idx >= 4) {
            maxNum = Math.max(maxNum, sum);
            return;
        }

        // 사방탐색 하면서 dfs로 테트로미노 탐색
        for(int d = 0; d < 4; d++) {
            int nr = r + dir[0][d];
            int nc = c + dir[1][d];
            if(!check(nr, nc) || visit[nr][nc]) continue;
            visit[nr][nc] = true;
            dfs(r + dir[0][d], c + dir[1][d], idx+1, sum + map[nr][nc]);
            visit[nr][nc] = false;
        }
    }// dfs End

    // ㅗ 모양의 테트로미노 탐색
    static void cross(int r, int c) {
        int sum = map[r][c];
        int count = 1;
        for(int d = 0; d < 4; d++) {
            int nr = r + dir[0][d];
            int nc = c + dir[1][d];
            if(!check(nr, nc)) continue;
            sum += map[nr][nc];
            count++;
        }

        // count가 3개 이하인건 모양미달이다.
        if(count == 5){
            for(int d = 0; d < 4; d++) {
                int nr = r + dir[0][d];
                int nc = c + dir[1][d];
                if(!check(nr, nc)) continue;
                int tmpSum = sum - map[nr][nc];
                maxNum = Math.max(tmpSum, maxNum);
            }
        } else if (count == 4){
            maxNum = Math.max(sum, maxNum);
        }
    }

    // 경계값 조건
    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }// check End
}