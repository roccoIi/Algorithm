import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* [회고]
* 1. 일단 문제 이해하는게 어려웠는데 그냥 배열로 나타냈을때 양옆으로 같은거, 위아래로 같은건 없애라는 거였다.
* 2. 시계방향으로 돌리는걸 추가/삭제로 구현하기 위해 리스트를 사용했다.
* 3. 만들어야하는 메소드가 많아서 오래걸렸지 실제 구현은 그렇게 오래 걸리지 않았다.
* -----------(수정)------------
* 4. 백준은 list.removeFirst() 이런함수가 안되네..?
* 5. list.get(i) == list.get(j)가 안된다는 이야기를 듣고 list.get(i).equals(list.get(j))로 바꿨다. java 버전문제일까?
* -----------(수정)------------
* 6. 문제를 잘못이해했다. 한 원판에서 이어진 숫자들 + 같은 위치에 있는 숫자들은 전부 이어진것이라 생각하고 삭제했어야했는데
*    쉽게말해서 가로+세로를 탐색해야하는데 가로따로 세로 따로 탐색해서 놓치는 부분이 생겼다.
*    bfs로 탐색하면서 이어져있다면 가로세로 상관없이 모두 탐색을 시작한다.
*/

public class Main {
    static int N, M, T; // N: 원판 개수, M: 원판 내 숫자 개수, T: 회전 수
    static List<Integer>[] list;
    static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
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
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // 리스트를 인자로 가지는 배열 선언 및 각 배열에 리스트 초기화
        list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 입력값
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                list[r].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 각각의 테스트케이스를 받아서 돌린다.
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int turnTime = Integer.parseInt(st.nextToken());

            // 주어진 조건에 맞춰서 원판을 회전시키고
            for (int t = 0; t < turnTime; t++) {
                rotation(target, direction, list);
            }

            // 지워지는 숫자가 없었을때는 평균값 계산해서 올려주고 내려준다.
            if(!delete(list)) {
                distribution(list, average(list));
            }
        }

        // 원판에 남아있는 숫자들 모두 합해서 출력한다.
        int total = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 0; c < M; c++) {
                total += list[r].get(c);
            }
        }

        System.out.println(total);
    }// main End

    // start: n의 배수인 원판만 돌려야하고 그 n이다.
    // dir: 회전방향, arr: 원판들의 배열
    static void rotation(int start, int dir, List<Integer>[] arr){
        if (dir == 0) {
            for (int r = start; r <= N; r += start) {
                int tmp = arr[r].remove(arr[r].size()-1);
                arr[r].add(0, tmp);
            }
        } else {
            for (int r = start; r <= N; r += start) {
                int tmp = arr[r].remove(0);
                arr[r].add(tmp);
            }
        }
    }// rotation End

    // 전체 배열을 돌면서 0이 아닐때만 bfs 로 탐색한다.
    // 한번이라도 bfs 를 통해 숫자를 변경했는지 안했는지 count로 확인하여 boolean형을 반환한다.
    static boolean delete(List<Integer>[] arr) {
        int count = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 0; c < M; c++) {
                if(arr[r].get(c).equals(0)) continue;
                count += bfs(r, c, arr[r].get(c), arr);
            }
        }
        return count != 0;
    }// delete End

    // 사방탐색을 돌면서 처음 bfs를 들어왔을때의 숫자와 동일한 숫자인지 확인하면서 탐색한다.
    // 해당 숫자와 동일할 경우 0으로 변경해놓으면서 진행한다.
    // 원판은 이어져있기 때문에 3번 숫자에 +1 해서 0번이 나오려면 나머지로 계산해야했다.
    // 역으로 0에 -1을 하면 3이가 나와야했기 때문에 시드 숫자에 나누는 값을 한번 더해 계산했다.
    static int bfs(int r, int c, int num, List<Integer>[] arr){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));
        int count = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dir[0][d];
                int nc = ((M+node.c) + dir[1][d]) % M;
                if(nr < 1 || nr > N) continue;

                if(arr[nr].get(nc).equals(num)) {
                    arr[nr].set(nc, 0);
                    q.offer(new Node(nr, nc));
                    count++;
                }
            }
        }
        return count;
    }// bfs End

    // 모든 원판 돌면서 평균값을 구한다.
    // return 값이 double 이 아닌 int 형이라면 나중에 대소 비교할때 오류가 발생한다.
    static double average(List<Integer>[] arr) {
        int sum = 0;
        int count = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 0; c < M; c++) {
                if(arr[r].get(c) != 0) count++;
                sum += arr[r].get(c);
            }
        }
        return (double)sum / (double)count;
    }// average End

    // 평균보다 낮은 값에는 +1, 높은 값에는 -1
    static void distribution(List<Integer>[] arr, double avg){
        for (int r = 1; r <= N; r++) {
            for (int c = 0; c < M; c++) {
                int num = arr[r].get(c);
                if(num == 0) continue;

                if(num < avg){
                    arr[r].set(c, num+1);
                } else if (num > avg) {
                    arr[r].set(c, num-1);
                }
            }
        }
    }// distribution End
}