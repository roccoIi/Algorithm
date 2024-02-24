import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
*  1. 주어진 N x N 배열은 ArrayList로 구성한다. 그리고 그 안에 Node객체를 넣어서 스위치가 킬 수 있는 방의 좌표를 저장한다.
*  2. 리스트 하나의 칸에 여러개의 스위치가 들어갈 수 있으니 리스트 한 칸 안에는 또 리스트로서 구성을 한다.
*  3. 노드는 x좌표와 y좌표로 구성
*  4. bfs를 통해 불을 키고 이동하는 과정을 구성한다.
*  [bfs]
*  1. 첫 시작은 node(1,1)로 시작한다. (1,1)은 곧 list[1][1]을 의미하고 list[1][1]에는 node(1,2), node(1,3)이 들어있다.(예제참고)
*  2. (1,2)와 (1,3)의 불을 키고 move배열에 방문가능하다고 표시되어있다면 큐에 넣는다.(지금은 표시가 안되어있으니 패스)
*  3. (1,1) 을 사방탐색하여 경계조건만 만족한다면 move배열에 체크를 해둔다. (1,2)(2,1)이 큐에 들어간다.
*  3. (1,1) 을 사방탐색하여 경계조건을 만족하고, 불이 켜져있고, 방문한 적이 없는 방이라면 큐에 넣는다. (1,2)가 큐에 들어간다.
*  4. While문 (1,2)를 시작한다. -> ....
*  5. (1,3)을 시작할때 (1,2)와 (2,1)의 불을 키고 이때 2번 상황을 확인한다. move배열에 (2,1)이 있는 것을 확인. 큐에 넣는다.
*  6. 만약 move배열에 넣지 않았다면 (2,1)은 이후로도 큐에 들어가지 못해 탐색을 하지 못하고 while문이 끝났을 것이다.
*/
class Node{
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class Main {
    static ArrayList<Node>[][] list;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M, cnt;
    static boolean[][] visit, light, move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // NxN의 방
        M = Integer.parseInt(st.nextToken()); // 제시되는 testCase 수
        list = new ArrayList[N+1][N+1]; // 방마다 스위치 저장할 리스트
        visit = new boolean[N+1][N+1]; // 방문한 방인지 체크
        light = new boolean[N+1][N+1]; // 불 켜져있는지 체크
        move = new boolean[N+1][N+1]; // 내가 갈 수 있는 방인지 체크

        // list안에 list를 만들어서 node가 들어갈 수 있게 세팅
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                list[r][c] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[x][y].add(new Node(a,b)); // list[x][y] 위치에 node(a,b)를 넣어서 스위치 표기
        }
        light[1][1] = true; // (1,1) 번방 불 키고 카운트+1
        cnt++;

        bfs();
        System.out.println(cnt);
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(1,1)); // (1,1)번방으로 큐 시작

        while(!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;  // 1
            int y = node.y;  // 1
            visit[x][y] = true;

            // 불 스위치 켜기
            for(Node n : list[x][y]){ // => [n.x][n.y] => (1,2), (1,3)
                if(light[n.x][n.y]) {
                    continue; // 이미 불 켜져있으면 건너뛰고 아니면 불키고 카운트+1
                }
                light[n.x][n.y] = true;
                cnt++;
                if(move[n.x][n.y]) q.offer(new Node(n.x,n.y)); // 내가 갈 수 있는 방이라면 큐에 추가
            }                                                  // 내가 갈 수 있는 방 = 경계를 넘지 않고 아래 조건들을 통과한 방

            for (int d = 0; d < 4; d++) { // 사방탐색
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(check(nx,ny)) move[nx][ny] = true; // 내가 이동할 수 있는 위치(경계 통과)라면 move => true;
                if(check(nx,ny) && light[nx][ny] && !visit[nx][ny]){ // 경계 벗어나지 않고, 불 켜져있고, 방문한적 없으면 큐에 추가
                    q.offer(new Node(nx,ny));
                }
            }
        }
    }

    static boolean check(int x, int y) { // 경계조건
        return x > 0 && y > 0 && x <=N && y <=N;
    }
}