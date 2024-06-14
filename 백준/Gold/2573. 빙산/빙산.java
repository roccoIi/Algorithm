import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static int R, C, totalIce, arr[][];
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

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        int year = 0;

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                if (arr[r][c] != 0) totalIce++;
            }
        }
        System.out.println(passYear(year));
    }//Main End

    /**
     * 한 해에 반복되는 일들, return 값은 최종 결과다.
     * 1. for 문을 돌면서 빙하를 찾는다. => 이때 빙하가 발견되지 못한다면 분리되지 못한 채 다 녹은상태(0 출력)
     * 2. 찾았다면 해당 빙하와 연결된 빙하의 수를 구한다. => totalIce(총 빙하 수)와 값이 같지 않다면 분리된 상태(해당 년도 출력)
     * 3. 하나의 덩어리라면 빙하를 녹인다.
     * 4. 1년이 지난다.
     */
    static int passYear(int year){
        while (true) {
            Node node = findIce();
            if (node == null) return 0;

            if (countIce(node) != totalIce) return year;
            else reduceIce(node);

            year++;
        }
    }//passYear End

    /**
     * - for 문을 돌면서 빙하의 좌표를 찾고 반환한다.
     * 만약 찾지 못했다면 null 반환
     */
    static Node findIce(){
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(arr[r][c] != 0) return new Node(r, c);
            }
        }
        return null;
    }//findIce End

    /**
     * - 연결된 덩어리에 속한 빙하의 수가 몇개인지 구한다.
     * 이때 구해지 빙하의 수가 전체 빙하수와 다르다면 덩어리가 분리된 상태
     */
    static int countIce(Node n){
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[n.r][n.c] = true;
        q.add(n);
        int count = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];

                if(check(nr, nc) && arr[nr][nc] > 0 && !visited[nr][nc]){
                    q.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                    count++;
                }
            }
        }
        return count;
    }//countIce End

    /**
     * - 빙하를 녹인다.
     * 사방탐색을 통해 주변에 0이 있다면 빙하를 녹인다.
     */
    static void reduceIce(Node n){
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        boolean[][] reduceVisited = new boolean[R][C];
        visited[n.r][n.c] = true;
        q.add(n);

        while (!q.isEmpty()){
            Node node = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];

                if(check(nr, nc) && !reduceVisited[nr][nc] && arr[node.r][node.c] > 0 && arr[nr][nc] == 0){
                    reduceVisited[node.r][node.c] = true;
                    arr[node.r][node.c]--;
                    if(arr[node.r][node.c] == 0) totalIce--;
                }

                if(check(nr, nc) && arr[nr][nc] > 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }
            }
        }
    }//reduceIce End

    /**
     * 경계값 분석
     */
    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }//check End
}