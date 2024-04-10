import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* * * * *
* [회고]
* 1. permutation: 최대 5번 돌리므로 {0, 0, 0, 0, 0} ~ {3, 3, 3, 3, 3} 까지 4가지 방향의 모든 중복조합을 구한다.
* 2. byDirection: 0, 1, 2, 3에 따른 모든 방향별로 숫자들을 이동시킨다.
* 3. move: 실제로 숫자들을 이동시키고 합친다.
*           덱(deque)을 활용해 각 줄마다 0이 아닌 숫자를 덱에 넣어놓고
*           다시 그 줄을 돌면서 차례대로 내려놓는다. 이때 전에 내려놓은 숫자와 겹친다면 제곱해서 전에 위치에 넣어놓고 현재 위치에 하나 더 놓는다.
*           안겹친다면 현재 위치에 내려놓는다.
* 4. copyMap: permutation 함수에서 기저조건이 될때마다 지도를 복사해간다.
* 5. maximum: 최댓값을 구한다.
* 6. check : 지도의 필수품 경계값 확인이다.
* * * * */

public class Main {
    static int[][] dir = {{-1, 0, 1, 0},{0, -1, 0, 1}}; // 델타배열
    static int[][] map;
    static int[] sel;
    static int N, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sel = new int[5];
        max = Integer.MIN_VALUE;

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        permutation(0);

        System.out.println(max);
    }//main 종료

    // 재귀를 통해 전체 중복순열 구하기
    static void permutation(int idx) {
        if(idx >= 5) {
            int[][] copy = copyMap(map);
            for(int i = 0; i < 5; i++) {
                byDirection(sel[i], copy);
            }
            maximum(copy);
            return;
        }

        for(int i = 0; i < 4; i++) {
            sel[idx] = i;
            permutation(idx+1);
        }
    } // permutation 종료

    // 각 방향별로 모든 행(or 열) 탐색
    static void byDirection(int d, int[][] map) {
        if(d == 0) {
            for (int c = 0; c < N; c++)
                move(map, d, N - 1, c);
        } else if (d == 1) {
            for(int r = 0; r < N; r++)
                move(map, d, r, N-1);
        } else if (d == 2) {
            for (int c = 0; c < N; c++)
                move(map, d, 0, c);
        } else if (d == 3){
            for (int r = 0; r < N; r++)
                move(map, d, r, 0);
        }
    } // byDirection 종료

    static void move(int[][] map, int d, int r, int c) { // 이걸 N번 돌려야함
        Deque<Integer> tmpQue = new ArrayDeque<>();
        // 처음이 0이면 패스, 아니면 덱에 넣고 시작
        if(map[r][c] != 0){
            tmpQue.addLast(map[r][c]);
            map[r][c] = 0;
        }

        // 모든 숫자를 덱에 넣었고 해당 줄은 모두 0으로 바뀌었다.
        int nr = r + dir[0][d];
        int nc = c + dir[1][d];
        while(check(nr, nc)) {
            if(map[nr][nc] != 0) {
                tmpQue.addLast(map[nr][nc]);
                map[nr][nc] = 0;
            }
            nr += dir[0][d];
            nc += dir[1][d];
        }

        // 덱이 비어있다면 (해당 열 / 행 이 모두 0이라면 종료)
        if(tmpQue.isEmpty()) return;
        // 다시 맨 첨으로 돌아와서 숫자를 놓는다.
        // 숫자가 겹치면 제곱하고 아니라면 놓는다.
        // 제곱될때마다 최댓값을 구한다.
        map[r][c] = tmpQue.remove();
        nr = r + dir[0][d];
        nc = c + dir[1][d];
        while(!tmpQue.isEmpty()){
            if(tmpQue.peek() == map[nr - dir[0][d]][nc - dir[1][d]]){
                map[nr - dir[0][d]][nc - dir[1][d]] = tmpQue.remove() << 1;
                if(tmpQue.isEmpty()) return;
            }
            map[nr][nc] = tmpQue.remove();
            nr += dir[0][d];
            nc += dir[1][d];
        }
    } // move 종료

    // 지도복사
    static int[][] copyMap(int[][] arr){
        int[][] map = new int[N][N];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                map[r][c] = arr[r][c];
            }
        }
        return map;
    }// copyMap 종료

    // 최댓값 구하기
    static void maximum(int[][] map){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                max = Math.max(map[r][c], max);
            }
        }
    }

    // 경계값 확인
    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }// check 종료
}