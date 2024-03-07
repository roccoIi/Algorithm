import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * [회고]
 *  1. Bfs 로 탐색하면 사방으로 퍼져가면서 가장 짧은 길을 체크할 수 있다.
 *  2. 주어진 배열 전체를 탐색하면서 가장 길이가 길때를 maxLength 에 저장한다.
 *  3. 좌표와 길이를 한번에 관리하기 위해 Node 생성 후 사용
 */
class Node{
    int r;
    int c;
    int length;
    Node(int r, int c, int length){
        this.r = r;
        this.c = c;
        this.length = length;
    }
}

public class Main {

    static int[] dr = new int[]{-1, 0, 1, 0}; // 사방탐색
    static int[] dc = new int[]{0, 1, 0, -1};
    static boolean[][] visit; // 방문체크
    static char[][] arr; // 주어진 배열
    static int R, C, maxLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maxLength = Integer.MIN_VALUE; // 가장 길이가 긴 길

        arr = new char[R][C];

        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                arr[r][c] = str.charAt(c);
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(arr[r][c] == 'L') bfs(r, c); // 물(W)이 아닌 땅(L)일때만 탐색
            }
        }
        // 출력
        System.out.println(maxLength);
    }

    static void bfs(int a, int b) {

        visit = new boolean[R][C]; // 방문배열 초기화
        Queue<Node> q = new LinkedList<>(); // 큐 생성
        q.offer(new Node(a, b, 0)); // 초기값 설정
        visit[a][b] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            int r = node.r;
            int c = node.c;
            int length = node.length;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(check(nr,nc) && arr[nr][nc] == 'L'){ // 경계조건 만족 + 미방문 + 땅(L) 일때 큐에 추가
                    q.offer(new Node(nr, nc, length + 1)); // 길이 +1해서 추가
                    visit[nr][nc] = true; // 방문확인
                }
            }
            maxLength = Math.max(length,maxLength); // 최댓값 출력
        }
    }

    // 경계조건을 만족하고 방문하지 않았을때 true 반환
    static boolean check(int nr, int nc){
        return nr >= 0 && nc >= 0 && nr < R && nc < C && !visit[nr][nc];
    }
}