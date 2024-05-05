import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
* [회고]
* 1. 하나하나 조건을 추가하다보니 코드가 굉장히 더러워진것 같다. 좀 더 간결하게 줄일 수 있을 것 같은데
* 2. [총 블록의 수가 많은 순 > 무지개 블록이 많은 순 > r값이 큰 순 > c 값이 큰 순] 이 조건을 구현하는데 하드코딩을 했다.
 *
* [메소드]
* 1. findGroup : 없앨 블록 그룹을 찾는다. bfs를 사용했고, 크기가 큰수서, 같을땐 무지개블록, r, c 의 순서대로 갱신 조건을 넣었다.
* 2. deleteGroup : 블록그룹을 찾았으면 이제 그 그룹을 없앤다. 없앤 블록은 99로 표시했다.
* 3. gravity: 중력의 영향을 받아 정렬되는 메서드다. deque를 사용했다.
* 4. turn90 : 반시계방향으로 90도 돌린다.
*
*
* */

public class Main {
    static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static int N, M;
    static boolean[][] visit;
    static class Node {
        int r, c, max, zero;

        public Node(){}
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void setRC(int r, int c, int max, int zero) {
            this.r = r;
            this.c = c;
            this.max = max;
            this.zero = zero;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Node[] targets = new Node[M+1];
        int[][] arr = new int[N][N];
        int totalScore = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());

            }
        }
        /*문제의 조건중
        * 크기가 가장 큰 블록 그룹을 찾는다.
        * 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹,
        * 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을,
        * 그 것도 여러개이면 열이 가장 큰 것을 찾는다
        * 이 조건을 만족시키기 위해 코드가 굉장히 길어졌다. 이걸 간결하게 줄일 수 없었을까
        * */
        while(true){
            int maxGroup = 0;
            int target = 0;
            int zero = 0;
            int r = 0;
            int c = 0;
            for (int i = 1; i <= M; i++) {
                targets[i] = findGroup(arr, i);
                if(targets[i].max > maxGroup) {
                    maxGroup = targets[i].max;
                    target = i;
                    zero = targets[i].zero;
                    r = targets[i].r;
                    c = targets[i].c;
                } else if (targets[i].max == maxGroup) {
                    if(targets[i].zero > zero){
                        target = i;
                        zero = targets[i].zero;
                        r = targets[i].r;
                        c = targets[i].c;
                    } else if (targets[i].zero == zero) {
                        if(targets[i].r > r) {
                            target = i;
                            r = targets[i].r;
                            c = targets[i].c;
                        } else if (targets[i].r == r) {
                            if(targets[i].c > c) {
                                target = i;
                                c = targets[i].c;
                            }
                        }
                    }
                }
            }

            // 그룹이 2개 미만이라면 while 문을 종료하고 그렇지 않다면 주어진 게임을 진행한다.
            if(maxGroup < 2) break;

            // 점수 획득
            int num = deleteGroup(arr, targets[target], target);
            totalScore += (num * num);

            // 중력 영향받고 90도 돌리고 영향받고
            gravity(arr);
            arr = turn90(arr);
            gravity(arr);
        }

        System.out.println(totalScore);

    }

    static Node findGroup(int[][] arr, int target) {
        boolean[][] visit = new boolean[N][N];
        Node targetNode = new Node();
        int maxCount = 0;
        int maxZero = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (arr[r][c] == target && !visit[r][c]) {
                    Queue<Node> q = new LinkedList<>();
                    q.offer(new Node(r, c));
                    visit[r][c] = true;
                    int count = 1;
                    int zeroCount = 0;

                    while (!q.isEmpty()) {
                        Node node = q.poll();

                        for (int d = 0; d < 4; d++) {
                            int nr = node.r + dir[0][d];
                            int nc = node.c + dir[1][d];
                            if(!check(nr, nc) || visit[nr][nc]) continue;

                            // target 숫자와 같거나 무지개블록이거나
                            if(arr[nr][nc] == target || arr[nr][nc] == 0) {
                                visit[nr][nc] = true;
                                q.offer(new Node(nr, nc));
                                count++;
                                if(arr[nr][nc] == 0) zeroCount++;
                            }
                        }//    bfs/while/for End
                    }//    bfs/while End

                    // 총 블록의 수가 많은 순 > 무지개 블록이 많은 순 > r값이 큰 순 > c 값이 큰 순
                    if(count > maxCount){
                        maxCount = count;
                        maxZero = zeroCount;
                        targetNode.setRC(r, c, maxCount, zeroCount);
                    } else if (count == maxCount) {
                        if(zeroCount >= maxZero){
                            maxZero = zeroCount;
                            targetNode.setRC(r, c, maxCount, zeroCount);
                        }
                    }
                }// bfs End
            }
        }// for End
        return targetNode;
    }

    static int deleteGroup(int[][] arr, Node target, int targetNumber) {
        Queue<Node> q = new LinkedList<>();
        int count = 0;
        q.offer(target);

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = node.c + dir[1][d];
                if(!check(nr, nc)) continue;

                if (arr[nr][nc] == targetNumber || arr[nr][nc] == 0) {
                    arr[nr][nc] = 99;
                    q.offer(new Node(nr, nc));
                    count++;
                }
            }
        }
        return count;
    }

    //deque에 99와 -1이 아닌 숫자를 담고
    // -1을 만났을때 그 위에 숫자들을 차례로 덮어씌운다.
    // 남은건 99로 덮어씌운다. (99 = 비어있다는 의미)
    static void gravity(int[][] arr) {
        for (int c = 0; c < N; c++) {
            Deque<Integer> q = new LinkedList<>();
            int count = 0;
            for (int r = 0; r <= N; r++) {
                if(!check(r, c) || arr[r][c] == -1){
                    int tempR = r;

                    while (count != 0) {
                        tempR--;
                        if(q.isEmpty()) arr[tempR][c] = 99;
                        else arr[tempR][c] = q.removeLast();
                        count--;
                    }
                } else if(arr[r][c] != 99){
                    q.offer(arr[r][c]);
                    count++;
                } else if (arr[r][c] == 99) {
                    count++;
                }
            }
        }
    }

    static int[][] turn90(int[][] arr) {
        int[][] tempArr = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                tempArr[r][c] = arr[c][N-1-r];
            }
        }
        return tempArr;
    }

    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }// check End
}