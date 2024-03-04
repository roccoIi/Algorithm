import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class tomato{
    int r; // row
    int c; // column
    int day; // 해당 토마토가 익는데 걸린 시간

    tomato(int r, int c, int day){
        this.r = r;
        this.c = c;
        this.day = day;
    }
}

public class Main {
    /*
     * [회고]
     *  1. 처음엔 단순이 사방탐색하며 확장해 나가면 될 것이라고 생각했다.
     *  2. 그러나 십자가 모양에서 길어진 십자가가 아닌 눈꽃모양마냥 퍼지는 것을 보고서 더이상 단순한 사방팔방으로는 감당이 안된다는 것을 확인
     *  3. 사방으로 퍼져나가면서 체크하면 가장 빠르게 퍼져나가는 것이라고 생각했고 bfs 를 적용했다.
     *  4. 중간에 끊겨 더이상 익지 못하는 토마토를 확인하기 위해 처음 배열을 입력하면서 총 토마토의 개수를 저장했고
     *  5. 토마토가 익을때마다 +1을 하면서 익은 토마토와 전체 토마토의 개수가 불일치 할경우 -1을 반환
     *  6. 그 외에는 익을때 들어간 기간(day)를 반환한다.
     */

    static int C, R, one, days;
    static int[][] arr;
    static Queue<tomato> q = new LinkedList<>(); // 배열 입력하면서 익은 토마토를 바로 큐에 넣어야했기 때문에 static
    static int[] dr = new int[]{-1, 0, 1, 0}; // 델타배열
    static int[] dc = new int[]{0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        one = 0; // 익은 토마토 개수
        int tomato = 0; // 총 토마토 개수

        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++) {
                int num = Integer.parseInt(st.nextToken()); // 배열 입력받으면서
                arr[r][c] = num;
                // one이 tomato와 같아질때 모든 토마토가 익었다. (익은 토마토 파악하고)
                if(num == 1) { // 익은 토마토는 바로 큐에 넣어버린다. (2개 이상 있을 수 있기 때문)
                    q.offer(new tomato(r, c, 0));
                }
                if(num != -1) tomato++; // -1은 토마토가 없다는 뜻이기에 -1만 아니면 전부 토마토다
            }
        }

        bfs();

        if(one != tomato) { // 익은 토마토의 수와 총 토마토의 수가 다르면 -1 반환
            System.out.println(-1);
        } else { // 그게 아니면 전체가 익은데 걸린 시간 반환
            System.out.println(days);
        }

    }

    static void bfs() {

        while(!q.isEmpty()) {
            tomato to = q.poll();
            int r = to.r;
            int c = to.c;
            int day = to.day;
            one++; // 지금 꺼낸건 익었기때문에 one++

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(check(nr,nc)) { // 경계조건 만족한다면
                    arr[nr][nc] = 1; // 익혀버리고
                    q.offer(new tomato(nr, nc, day+1)); // 큐에 추가
                }
            }
            days = day; // 가장 마지막에 익은 토마토의 일자 반환
        }
    }

    static boolean check(int r, int c) { // 경계조건을 벗어나지 않으면서 안익은 토마토일때 True
        return r >=0 && c >=0 && r < R && c < C && arr[r][c] == 0;
    }
}