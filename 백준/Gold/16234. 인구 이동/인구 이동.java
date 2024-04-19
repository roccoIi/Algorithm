import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * [회고]
 * 1. bfs을 통해 연합을 찾고, 연합을 찾음과 동시에 인구를 나눠갖는다.
 * 2. 처음엔 배열 한번 싹 돌면서 연합들 방문배열에 true 표시하려고 했지만 2개 이상이 될 경우 그 위치를 일일이 저장해야했다.
 * 3. 그래서 아예 bfs안에서 연합을 찾고, 찾음과 동시에 그 연합들이 수를 나눠 갖는 방법을 택했다.
 * 4. 단 한쪽이 먼저 나눠가짐으로써 수가 줄어들면 다른 한쪽 연합이 탐색할때 재탐색 될 수도 있으니 visit배열을 싸이클단위로 관리했다.
 * --------(수정)----------
 * 5. divide 배열은 평균숫자 나눠줄때 사용하는 방문배열인데, 이걸 큐 들어갈때마다 초기화 시켜서 오류가 났다.
 * 6. 큐 들어갈때마다가 아니라 한 사이클 돌때마다 초기화시켜줘야했다.
 */

public class Main {
    static int N, L, R, Day, map[][];
    static int[][] dir = {{1, -1, 0, 0}, {0, 0, 1, -1}};
    static boolean[][] visit, divide;
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

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            int num = 0;
            visit = new boolean[N][N];
            divide = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(!visit[r][c]) num += bfs(new Node(r, c));
                }
            }


            if (num == 0) break;
            Day++;
        }

        System.out.println(Day);

    }// main End

    static int bfs(Node tmp){
        // ---------------- 연합을 찾는다. ----------------
        Queue<Node> q = new LinkedList<>();
        boolean flag = false;
        visit[tmp.r][tmp.c] = true;
        q.offer(tmp);
        int count = 1;
        int sum = map[tmp.r][tmp.c];

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];
                if(!check(nr, nc) || !check2(map[node.r][node.c], map[nr][nc])) continue;
                if(!visit[nr][nc]){
                    flag = true;
                    q.offer(new Node(nr, nc));
                    visit[nr][nc] = true;
                    count++;
                    sum += map[nr][nc];
                }
            }
        }



        // bfs에 들어왔을때와 같은 값이라면 아무런 연합을 만들지 못했다.
        if(!flag) {
            visit[tmp.r][tmp.c] = false;
            return 0;
        }


        // ---------------- 연합끼리 숫자를 나눠갖는다. ----------------
        q.offer(tmp);
        int average = sum / count;
        map[tmp.r][tmp.c] = average;
        divide[tmp.r][tmp.c] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];
                if(!check(nr, nc)) continue;

                if(visit[nr][nc] && !divide[nr][nc]) {
                    divide[nr][nc] = true;
                    map[nr][nc] = average;
                    q.offer(new Node(nr, nc));
                }
            }
        }
        return 1;
    }// bfs End

    // 경계값 조건
    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }// check End

    // 범위 조건
    static boolean check2(int num1, int num2) {
        int num = Math.abs(num1 - num2);
        return  L <= num && num <= R;
    }// check2 End
}