import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {
    static class Object{
        int r;
        int c;

        public Object(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int R, C, range, nr, nc, maxCount;
    static int[][] map, copy;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());

        map = new int[R][C]; // 원래 지도
        copy = new int[R][C]; // 궁수의 배치가 바뀔때마다 초기화 해줄 지도
        maxCount = Integer.MIN_VALUE;

        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++) {
                int num = Integer.parseInt(st.nextToken());
                map[r][c] = num;
                copy[r][c] = num;
            }
        }

        // 총 3명의 조합 구하기
        for(int i = 0; i < C-2; i++) {
            for(int j = i+1; j < C-1; j++) {
                for(int k = j+1; k < C; k++) { // 9. 이걸 매 조합마다 반복한다.
                    // 조합이 완성되면 큐에 궁수를 넣고 게임 진행한다.
                    Queue<Object> q = new LinkedList<>();
                    q.offer(new Object(R,i));
                    q.offer(new Object(R,j));
                    q.offer(new Object(R,k));
                    play(q);
                    resetMap(); // 해당 배치의 게임이 끝나면 지도 리셋
                }
            }
        }

        System.out.println(maxCount);

    }


    /*
    * [진행 방식]
    *  - 매 라운드마다 궁수들을 한칸씩 위로 옮기면서 진행한다. 라운드는 총 R라운드 진행한다.
    *  [각 라운드]
    *   1. 큐에 들고온 궁수 한명 꺼내서 지도 전체를 훑는다. 
    *   2. 몬스터 만날때마다 궁수의 위치와 몬스터 위치 거리 비교해서 조건에 일치할때 몬스터 위치 킵해놓는다.(nr, nc)
    *   3. 거리가 일치할때는 더 왼쪽에 있는지 확인한다음에 몬스터 위치 갱신할지 말지 정한다.
    *   4. 몬스터 찾았다면 flag를 true로 돌려준다.
    *   5. 꺼낸 궁수 한명의 r좌표를 1 줄여주고 다시 큐에 넣는다.
    *   6. 깃발 돌아갔다면 만들어둔 몬스터 큐에 넣어놓는다. (한번에 활 쏴야하기때문에 위치만 킵해놓는 용도)
    *   7. 궁수 3명 다 진행했다면 킵해놓은 몬스터 큐의 몬스터 좌표들 꺼내면서 처리한다.(해당 위치 1이라면 카운트+1)
    *   8. 이렇게 R라운드를 반복해서 나온 count와 maxCount를 비교해서 최대값을 저장한다.
    * 
    * --------------
    *   9. 이걸 매 조합마다 반복한다.
    *
    * */
    static void play(Queue<Object> q){
        int count = 0;
        for(int i = 0; i < R; i++){
            Queue<Object> monster = new LinkedList<>();
            for(int p = 0; p < 3; p++){
                int minDistance = Integer.MAX_VALUE;
                Object archer = q.poll();
                int r = archer.r;
                int c = archer.c;
                nr = nc = 0;
                boolean flag = false;
                for(int y = 0; y < r; y++){ // 1. 큐에 들고온 궁수 한명 꺼내서 지도 전체를 훑는다. 
                    for (int x = 0; x < C; x++) {
                        if(map[y][x] == 1){ // 2. 몬스터 만날때마다 궁수의 위치와 몬스터 위치 거리 비교해서 조건에 일치할때 몬스터 위치 킵해놓는다.(nr, nc)
                            int monsterD = attackDistance(r, c, y, x);
                            if(monsterD <= range){
                                if(minDistance > monsterD){
                                    minDistance = monsterD;
                                    nr = y;
                                    nc = x;
                                    flag = true;
                                } else if(minDistance == monsterD){ //  3. 거리가 일치할때는 더 왼쪽에 있는지 확인한다음에 몬스터 위치 갱신할지 말지 정한다.
                                    if(x < nc){
                                        nr = y;
                                        nc = x;
                                        flag = true; // 4. 몬스터 찾았다면 flag를 true로 돌려준다.
                                    }
                                }
                            }
                        }
                    }
                }
                q.offer(new Object(r-1, c)); // 5. 꺼낸 궁수 한명의 r좌표를 1 줄여주고 다시 큐에 넣는다.
                if(flag) monster.offer(new Object(nr, nc)); // 6. 깃발 돌아갔다면 만들어둔 몬스터 큐에 넣어놓는다. (한번에 활 쏴야하기때문에 위치만 킵해놓는 용도)
            }

            while(!monster.isEmpty()){ // 7. 궁수 3명 다 진행했다면 킵해놓은 몬스터 큐의 몬스터 좌표들 꺼내면서 처리한다.(해당 위치 1이라면 카운트+1)
                Object m = monster.poll();
                int r = m.r;
                int c = m.c;

                if(map[r][c] == 1){
                    count++;
                    map[r][c] = 0;
                }
            }
        }
       
        maxCount = Math.max(count, maxCount); // 8. 이렇게 R라운드를 반복해서 나온 count와 maxCount를 비교해서 최대값을 저장한다.
    }

    // 거리 측정
    static int attackDistance(int r1, int c1, int r2, int c2){
        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }

    // 지도 리셋
    static void resetMap(){
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = copy[r][c];
            }
        }
    }
}