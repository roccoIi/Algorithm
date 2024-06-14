import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [정리]
 * 1. 전체 배열을 입력받으면서 0이 아닌숫자(바이러스)가 들어왔을때 해당인덱스에 들어있는 큐에 좌표를 넣는다.
 * 2. qArr배열의 각 index에 들어있는 큐는 해당 바이러스의 최전선에 위치한 좌표이다.
 * 3. 해당 queue 사이즈만큼만 for 문을 돌면서 사방탐색 위치의 값이 0일때 해당 바이러스 번호로 변화시키고
 *    이동한 위치의 좌표를 큐에 넣는다.
 * 4. 그게 아니라면(0이 아닌 숫자) 해당 좌표는 날린다.
 * 5. 목표로 하는 위치의 값이 0이 아닐때 즉시 종료 후 출력한다.
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

        Queue<Node>[] qArr = new Queue[K+1];
        for (int i = 1; i <= K; i++) {
            qArr[i] = new LinkedList<>();
        }


        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                if(arr[r][c] != 0){
                    qArr[arr[r][c]].add(new Node(r, c));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

//      ---------------------여기까지 입력값 받기----------------------------

    end:for (int time = 1; time <= S; time++) { // 주어진 초만큼 반복
            for (int i = 1; i <= K; i++) { // 바이러스 수만큼 돌면서 각 번호의 바이러스들을 모두 bfs돈다.
                bfs(i, qArr[i]);
                if(arr[X-1][Y-1] != 0) break end; // 하나 끝났을때 해당 위치가 0이 아니라면 즉시 종료 후 출력
            }
        }

        System.out.println(arr[X-1][Y-1]);

    }

    static void bfs(int target, Queue<Node> q){
        // 현재 들어와있는 큐 사이즈만큼만 돌아볼 예정이다.
        int size = q.size();

        for(int i = 0; i < size; i++){
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];

                // 이동한 좌표가 0이라면 target 번호로 변경하고 그게 아니라면 패스한다.
                // 0을 target 번호로 변경했을때 해당 위치가 해당 바이러스 번호 최전선에 위치해 있으므로
                // 다시 큐에 넣는다.
                if(check(nr, nc) && arr[nr][nc] == 0) {
                    arr[nr][nc] = target;
                    q.add(new Node(nr, nc));
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}