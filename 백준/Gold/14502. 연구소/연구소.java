import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
* [회고]
* 1. 세개의 기둥이 놓이는 모든 경우의 수를 구하고, 기둥 3개 놓았을때 지도 복사해서 bfs 돌린다.
*     bfs 들어가기 전에 지도 복사해서 복사한 지도 손에 들려준다.
* 2. 큐에 2(바이러스) 개수 세서 넣어놓고 사방탐색으로 바이러스 퍼뜨린다.
* 3. 다 퍼뜨렸으면 0(안전지대) 개수 세서 최댓값 비교한다.
* 4. 모든 경우의 수를 구할때 더 적은 경우의 수를 구할 수 있을 것 같은데 아이디어가 생각나지 않았다.
*/
public class Main {
    static int[][] dir = {{0, -1, 0, 1}, {1, 0, -1, 0}};
    static int N, M, maxSafe;
    static int[][] map;
    static int[][][] visit;
    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력값
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        maxSafe = Integer.MIN_VALUE;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(maxSafe);
    }// main End

    // 1. 세개의 기둥이 놓이는 모든 경우의 수를 구하고, 기둥 3개 놓았을때 지도 복사해서 bfs
    static void dfs(int idx){
        if (idx >= 3) {
            int[][] newMap = copyMap(map);
            bfs(newMap);
            return;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] == 1 || map[r][c] == 2) continue;
                map[r][c] = 1;
                dfs(idx+1);
                map[r][c] = 0;
            }
        }
    } // dfs End

    // 2. 바이러스 위치 찾아서 큐에 넣고 사방탐색으로 바이러스를 넓혀간다.
    static void bfs(int[][] map){
        Queue<Node> q = new LinkedList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] == 2) q.offer(new Node(r, c));
            }
        }

        while(!q.isEmpty()){
            Node node = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];
                if(!check(nr, nc)) continue;
                if(map[nr][nc] == 0){
                    map[nr][nc] = 2;
                    q.offer(new Node(nr, nc));
                }
            }
        }
        maxSafe = Math.max(maxSafe, countSafe(map));
    }// bfs End

    // 3. 지도복사
    static int[][] copyMap(int[][] map){
        int[][] temp = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                temp[r][c] = map[r][c];
            }
        }
        return temp;
    }// copyMap End

    // 4. 안전지대 카운트
    static int countSafe(int[][] map){
        int cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] == 0) cnt++;
            }
        }
        return cnt;
    }// countSafe End

    // 5. 경계조건 확인
     static boolean check(int r, int c){
        return r >= 0 && c >= 0 && r < N && c < M;
    }// check End
}