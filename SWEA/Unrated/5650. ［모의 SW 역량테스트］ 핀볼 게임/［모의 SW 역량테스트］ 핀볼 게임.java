import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
* 1. PinBall 객체
*   1) 파라미터 2개: 초기에 값이 0인 위치의 좌표값을 저장한다. 큐에 넣어서 나중에 지도 map 탐색할때 하나씩 꺼내쓴다.
*   2) 파라미터 4개: 게임내에서 사용한다. 좌표값과 함께 진행방향과 현재 포인트를 가지고 있다.
* 2. play 메소드
*   - 현재 위치의 값별로 해야할 행동들을 넣어놨다.
*   - 경계값을 벗어나면 안되니 경계조건을 가장 먼저 걸어놨고 그 이후에 사각형, 삼각형, 웜홀, 블랙홀/되돌아왔을 때, 계속 진행할때별로 적혀있다.
* 3. triangle 메소드
*   - 삼각형에 부딪혔을때 바뀌는 방향을 반환하기위한 함수다. 이 함수 생각하는데 가장 오래 걸렸다.
*   - 들어오는 방향과 부딪히는 삼각형의 종류를 고려해 방향을 반환한다.
* 4. rectangle 메소드
*   - 사각형에 부딪혔을때 바뀌는 방향을 반환한다. 벽에 부딪혔을때와 동일하므로 벽에 부딪혔을때도 사용한다.
* 5. wormHole 메소드
*   - 웜홀에 들어갔을때 연결되어 나오는 좌표값을 넣은 pinball 객체를 반환한다.
* 6. check 메소드
*   - 경계값 조건
* ----------(런타임 에러)---------
* 1. 재귀가 너무 깊게 들어가면 안된다는 이야기를 듣고 재귀를 반복문으로 바꾸었다.
*/

public class Solution {
    static class PinBall{
        int r, c, d, point;

        public PinBall(int r, int c){
            this.r = r;
            this.c = c;
        }
        public PinBall(int r, int c, int d, int point) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.point = point;
        }
    }
    static int[][] dir = {{-1, 0, 1, 0},
                          {0, 1, 0, -1}}; // 0: 상, 1: 우, 2: 하, 3: 좌
    static int[][][] wormHoleArr = new int[11][2][2]; // 웜홀은 6번부터 10번까지
    static int N, maxNum;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");
            Queue<PinBall> q = new LinkedList<>();
            boolean[] flag = new boolean[11];
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            maxNum = Integer.MIN_VALUE;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());

                    // 웜홀 입력받기 (처음 입력받으면 0번 인덱스에, 두번째면 1번 인덱스에)
                    int num = map[r][c];
                    if(num == 6 || num == 7 || num == 8 || num == 9 || num == 10){
                        if(!flag[num]){
                            wormHoleArr[num][0][0] = r;
                            wormHoleArr[num][0][1] = c;
                            flag[num] = true;
                        } else{
                            wormHoleArr[num][1][0] = r;
                            wormHoleArr[num][1][1] = c;
                        }
                    } else if (num == 0) { // 0일때 좌표를 전부 큐에 넣어놓는다. (최대 100x100크기 맵, 10,000개 탐색하기 싫다.)
                        q.offer(new PinBall(r, c));
                    }
                }
            }

            // 큐에 들어가있는 0의 좌표를 하나씩 꺼내 게임을 시작한다.
            while(!q.isEmpty()){
                PinBall pinBall = q.poll();
                for(int d = 0; d < 4; d++){ // 사방으로 진행
                    play(new PinBall(pinBall.r, pinBall.c, d, 0), pinBall.r, pinBall.c);
                }
            }

            sb.append(maxNum).append("\n");
        }// testCase End
        System.out.println(sb);
    }//main End



    // 공 틩기기 (재귀 -> 반복문으로 변경)
    static void play(PinBall pinBall, int or, int oc){
        int r = pinBall.r;
        int c = pinBall.c;
        int direction = pinBall.d;
        int point = pinBall.point;
        int nr = r + dir[0][direction];
        int nc = c + dir[1][direction];


        while(true){
            //System.out.printf("(%d, %d)현재: (%d, %d) -> 진행방향: (%d, %d), 현재포인트: %d\n", or, oc, nr- dir[0][direction], nc -dir[1][direction], nr, nc, point);
            if(!check(nr, nc) || map[nr][nc] == 5) { // 사각형이나 벽에 부딪혔을 때 부딪히고 나서 내 원래 좌표값이면 종료
                if (nr - dir[0][direction] == or && nc - dir[1][direction] == oc) {
                    maxNum = Math.max(point + 1, maxNum);
                    break;
                }
                direction = rectangle(direction);
                point++;
            } else if(map[nr][nc] == 1 || map[nr][nc] == 2 ||
                    map[nr][nc] == 3 || map[nr][nc] == 4){ // 삼각형 만날때
                direction = triangle(map[nr][nc], direction);
                point++;
            } else if (map[nr][nc] == 6 || map[nr][nc] == 7 || map[nr][nc] == 8 ||
                    map[nr][nc] == 9 || map[nr][nc] == 10) { // 웜홀 만날 때
                PinBall newpinball = wormHole(new PinBall(nr, nc, direction, point), map[nr][nc]);
                nr = newpinball.r;
                nc = newpinball.c;
            } else if(map[nr][nc] == -1 || nr == or && nc == oc){ // 블랙홀 or 내 원래 좌표값으로 돌아올 때
                maxNum = Math.max(point, maxNum);
                break;
            }
            // 방향 바꿀애들 다 바꿨으면 해당 방향으로 진행
            nr += dir[0][direction];
            nc += dir[1][direction];
        }

    }

    // 삼각형 블록에 부딪혔을 때 위치 반환(num: 삼각형 번호, dir: 방향 번호)
    static int triangle(int num, int dir) {
        if (dir == (num + 1) % 4) {
            dir = (dir + 3) % 4;
        } else if (dir == (num + 2) % 4) {
            dir = (dir + 1) % 4;
        } else {
            dir = (dir + 2) % 4;
        }
        return dir;
    } //triangle End

    // 사각형 or 벽 부딛혔을때 반대방향 반환
    static int rectangle(int dir) {
        return (dir + 2) % 4;
    } //rectangle End

    // 웜홀에 도착했을때 해당 웜홀과 연결된 웜홀의 좌표를 가진 pinball 객체 반환
    static PinBall wormHole(PinBall pinBall, int num){
          if(wormHoleArr[num][0][0] == pinBall.r && wormHoleArr[num][0][1] == pinBall.c){
            return new PinBall(wormHoleArr[num][1][0], wormHoleArr[num][1][1], pinBall.d, pinBall.point);
        } else {
            return new PinBall(wormHoleArr[num][0][0], wormHoleArr[num][0][1], pinBall.d, pinBall.point);
        }
    } //wormHole End

    // 경계값조건
    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    } // check End
}