import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
* [회고]
*  1. 이전 bfs를 풀면서 계속 막혔던 부분이 두가지 경우의 수가 생겨버렸을 때였다.
*  2. 이 문제를 통해 두가지 경우의 수가 생겼을 때 3차원 방문배열을 만들어 진행할 수 있다는 것을 알게 되었다.
*  3. 크게 전략은 벽을 부술 기회가 있을때와 없을때로 나눠 생각했다.
* [bfs 설명]
*   각 경우의 수를 진행하면서 기회여부에 따라 해당 방문배열에 꼭 체크를 하고 진행한다. (무한루프 방지)
*  1. 벽을 부술 기회가 있는 경우(O)
*    1) 벽을 만났다!
*      (1) 벽을 부수고 진행한다. (큐에 넣는다, 기회감소)
*      (2) 벽을 부수지 않고 돌아간다. (갱신된 좌표가 아닌 기존 좌표를 큐에 넣는다, 기회유지, 거리유지)
*    2) 일반 길이다!
*      (1) 큐에 넣는다. (기회유지, 거리+1)
*  2. 벽을 부술 기회가 없는 경우(X)
*    1) 벽을 만났다!
*       (1) 돌아간다. (큐에 넣지 않고 진행)
*    2) 벽이 아닌 일반 길이다.
*       (2) 큐에 넣는다.(기회 유지, 거리+1)
* */
public class Main {

    //데이터 배열
    static int N, M, minDistance;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Node{
        int r;
        int c;
        int distance;
        boolean breakable;

        public Node(int r, int c, int distance, boolean breakable) {
            this.r = r;
            this.c = c;
            this.distance = distance;
            this.breakable = breakable;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minDistance = Integer.MAX_VALUE;

        map = new int[N][M];
        visit = new boolean[N][M][2];
        visit[0][0][0] = visit[0][0][1] = true;

        for(int r = 0; r < N; r++) {
            String str = br.readLine();
            for(int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        Node person = new Node(0, 0, 0, false);
        bfs(person);
        
        // minDistance가 최댓값이라면 최종 목적지에 다다르지 모했다는 의미다.
        if(minDistance == Integer.MAX_VALUE){
            System.out.println(-1);
        } else{
            System.out.println(minDistance+1); // 시작점도 거리에 포함되므로 +1
        }
    }

    static void bfs(Node person) {
        Queue<Node> q = new LinkedList<>();
        q.offer(person);

        //[breakable]
        // 1. False : 기회 있음(default)
        // 2. True : 기회 X
        // new Node(r값, c값, 거리, 기회여부)
        // visit[][][0]: 기회 없는 사람들, visit[][][1]: 기회 있는 사람들
        while(!q.isEmpty()) {
            Node node = q.poll();
            int r = node.r;
            int c = node.c;
            int distance = node.distance;
            boolean breakable = node.breakable;

            // 종점에 도착했을 때 최솟값 갱신
            if(r == N-1 && c == M-1){
                minDistance = Math.min(minDistance, distance);
            }

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(!check(nr, nc)) continue; // 경계조건 벗어나면 일단 아웃

                if(!breakable){ //
                    if(map[nr][nc] == 1 && !visit[nr][nc][1]){
                        q.offer(new Node(nr, nc, distance+1, true));
                        q.offer(new Node(r, c, distance, false));
                        visit[nr][nc][1] = true;
                    } else if (map[nr][nc] == 0 && !visit[nr][nc][1]) {
                        q.offer(new Node(nr, nc, distance+1, false));
                        visit[nr][nc][1] = true;
                    }
                } else {
                    if (map[nr][nc] == 0 && !visit[nr][nc][0]) {
                        q.offer(new Node(nr, nc, distance + 1, true));
                        visit[nr][nc][0] = true;
                    }
                }
            }
        }
    }

    // 경계조건 확인
    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

}