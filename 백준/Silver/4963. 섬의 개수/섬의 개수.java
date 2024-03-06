import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    /*
     * [회고]
     *  1. 가로 세로 대각선으로 이어져있으면 하나의 섬으로 본다고 했다. 즉 팔방으로 이어진 땅들을 모두 체크해서 몇개 묶음인지 찾으면 된다.
     *  2. BFS로 섬의 갈림길까지 모두 체크하면서 방문한 섬(1)은 visit배열에 true로 표시했다.
     *  3. 그렇게 한번의 bfs가 끝났을땐 이어진 섬을 모두 조사했으므로 섬의 개수(count) +1
     *  4. 주어진 이차원 배열을 끝까지 탐색했을때 섬의 개수를 출력하면 마무리된다.
     *  5. 섬의 좌표를 쉽게 다루기 위해 Node를 생성해 관리했다.
     *
     */

    static int[] dr = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    static boolean[][] visit;
    static int[][] arr;
    static int R,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            if(R == 0 && C == 0) break; // 지도의 가로세로 값으로 0, 0 이 주어지면 종료 후 최종 출력
            int count = 0; // 섬의 개수 카운팅

            arr = new int[R][C]; //지도
            visit = new boolean[R][C]; // 방문체크

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 해당 위치가 섬(1)일때 + 방문한적 없을때 bfs시작
            for (int r = 0; r < R; r++) {
                for (int c = 0; c <C; c++) {
                    if(arr[r][c] == 1 && !visit[r][c]) {
                        count += bfs(r, c);
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(int r, int c){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));

        while(!q.isEmpty()){
            Node node = q.poll();

            int y = node.r;
            int x = node.c;


            for (int d = 0; d < 8; d++) { // 팔방탐색
                int ny = y + dr[d];
                int nx = x + dc[d];

                if(check(ny,nx) && arr[ny][nx] == 1){ //경계조건 만족 + 해당 위치가 섬(1)일때 큐에 추가
                    q.offer(new Node(ny,nx));
                    visit[ny][nx] = true; // 섬을 찾았으면 큐에 넣으면서 방문체크
                }
            }
        }
        return 1;
    }

    static boolean check(int r, int c){ // 경계를 벗어나지 않으면서도 방문한 이력이 없을때 true;
        return r >= 0 && c >= 0 && r < R && c < C && !visit[r][c];
    }

}
class Node{
    int r;
    int c;

    Node(int r, int c){
        this.r = r;
        this.c = c;
    }
}