import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [정리 - target Virus 가 1일경우 가정]
 * 1. 전체 배열을 돌면서 1일 때 큐에 해당 좌표를 노드로 저장
 * 2. 위치를 이동하면서 경계를 벗어나지 않고 해당 위치가 0일때 1로 변경
 * 3. 그 외의 경우에느 아무런 행동을 하지 않음
 * 4. 2번과 3번 행동이 끝난 후에 다시 큐에 노드를 넣지 않고 그대로 진행
 * 5. 1~4 과정을 바이러스의 번호만큼 반복한다.
 * 6. 5번 과정을 주어진 초만큼 반복하면서 정답 좌표에 0이 아닌 값이 있을때 바로 종료하고 출력
 */

class Node{
    int r, c;

    public Node(int r, int c ) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static int N, K, arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        
//      ---------------------여기까지 입력값 받기----------------------------

    end:for (int time = 1; time <= S; time++) { // 주어진 초만큼 반복
            for (int i = 1; i <= K; i++) { // 바이러스 수만큼 돌면서 각 번호의 바이러스들을 모두 bfs돈다. 
                bfs(i);
                if(arr[X-1][Y-1] != 0) break end; // 하나 끝났을때 해당 위치가 0이 아니라면 즉시 종료 후 출력
            }
        }

        System.out.println(arr[X-1][Y-1]);

    }

    static void bfs(int target){
        Queue<Node> q = new LinkedList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(arr[r][c] == target) { // target 번호 바이러스 좌표를 전부 큐에 넣는다.
                    q.offer(new Node(r, c));
                }
            }
        }

        while (!q.isEmpty()){
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];

                // 이동한 좌표가 0이라면 target 번호로 변경하고 그게 아니라면 패스한다.
                // 다시 큐에 넣지는 않는다.
                if(check(nr, nc) && arr[nr][nc] == 0) arr[nr][nc] = target;
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}