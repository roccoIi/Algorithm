import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
* [회고]
* 1. 한 세대가 지날때마다 이전 방향에서 -90도 (방향으로는 +1)된 방향으로 누적 진행된다.
*   ex) 0 -> (0+1) -> (1+1), (0+1) -> (1+2), (2+1), (1+2), (0+1) -> ...
* 2. 리스트에 방향들을 누적하고 역순으로 진행하면 된다.
* 3. 한 세대의 드래곤커브가 한번에 진행되기 때문에 중간에 범위를 벗어난다면 그 세대 전체가 진행되지 말아야한다.
* 4. 이를 위해 한 세대의 이동좌표를 모두 큐에 담아놓고 한 세대가 범위내에서 진행된다면 큐에 담긴 좌표를 모두 꺼내 표시한다.
* 5. 최대로 나아가는 범위를 알 수 없기때문에 최대 범위인 100x100을 전체 배열의 크기로 잡았다.
*/
public class Main {
    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[][] dir = {{0, -1, 0, 1}, {1, 0, -1, 0}};
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력값
        N = Integer.parseInt(br.readLine().trim());
        map = new int[101][101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            map[y][x] = 1;
            curve(y, x, d, g);
        }

        // 전체 순회하면서 4개의 꼭짓점이 모두 1로 표시되어있다면 카운트+1
        int count = 0;
        for (int r = 0; r <= 100; r++) {
            for (int c = 0; c <= 100; c++) {
                if(map[r][c] == 1){
                    // 나머지 세 좌표중 하나라도 경계에서 벗어나면 패쓰, 아니라면 모두 1인지 확인하고 카운트
                    if(!check(r, c+1) || !check(r+1, c) || !check(r+1, c+1)) continue;
                    if(map[r][c+1] == 1 && map[r+1][c] == 1 && map[r+1][c+1] == 1) count++;
                }
            }
        }
        System.out.println(count);
    }// main End

    public static void curve(int r, int c, int d, int g){
        List<Integer> list = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        // 첫 진행방향을 표시하고 리스트에 담는다. (세대는 1부터 시작, 0세대는 방금 표시했다.)
        int nr = r + dir[0][d];
        int nc = c + dir[1][d];
        map[nr][nc] = 1;
        list.add(d);
        int nowGen = 1;

        // nowGen 으로 현재 세대를 카운트하고, 주어진 세대를 넘어서면 while 문 종료
        while(nowGen <= g){
            int size = list.size();
            for (int i = size-1; i >= 0; i--) {
                int newDir = (list.get(i) + 1) % 4;
                nr += dir[0][newDir];
                nc += dir[1][newDir];
                if (!check(nr, nc)) return; // 만약 한 세대안에서 범위를 벗어난다면 표시하지도 않고 바로 종료
                q.offer(new Node(nr, nc));
                list.add(newDir);
            }

            // 정상적으로 여기까지 도착했다면 큐에 담긴 좌표를 모두 꺼내 칠한다.
            while(!q.isEmpty()){
                Node node = q.poll();
                map[node.r][node.c] = 1;
            }
            nowGen++;
        }
    }// curve End

    public static boolean check(int r, int c){
        return r >= 0 && c >= 0 && r <= 100 && c <= 100;
    }// check End
}