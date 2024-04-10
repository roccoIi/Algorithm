import java.util.*;
import java.io.*;
/*
* [회고]
* 1. class Node: 군집들의 좌표값, 가지고있는 미생물의 수, 방향을 저장해놓는다.
* 2. move: 큐에 담긴 군집들을 하나씩 빼서 이동시킨 후 pq에 넣는다.
*           pq에 담긴 군집들은 미생물 수가 많은 순으로 빠져나와 해당 좌표에 담긴 미생물들을 모두 흡수하고 다시 큐에 들어간다.
*           pq에서 나왔을때 해당 좌표에 미생물 수가 없으면 큐에 들어가지 않고 사라진다.
* 3. direction: 해당 군집이 가진 방향을 기준으로 이동시킨다. 경계값에 도착했을땐 미생물을 반으로 줄이고 방향을 바꾼다.
* ----------------------------------------------------------------------------
* 1. map 이라는 이름의 이중 리스트 배열을 만들었고 해당 좌표에는 도착하는 군집들의 미생물 수만 저장해놓는다.
* 2. map 을 전체 탐색하지 않고 pq에서 얻게 된 좌표값을 받아와 해당 좌표를 바로 찾아간다.
*
* */
class Node implements Comparable<Node>{
    int r, c, direction, value;

    public Node(int r, int c, int direction, int value) {
        this.r = r;
        this.c = c;
        this.direction = direction;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return o.value - this.value ;
    }
}
class Solution {
    static int N;
    static List<Integer>[][] map;
    static int[][] dir = {{0, -1, 1, 0, 0}, {0, 0, 0, -1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            Queue<Node> q = new LinkedList<>();
            int total = 0;

            // 2차원 리스트 배열 초기화
            map= new ArrayList[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = new ArrayList<>();
                }
            }

            // 입력값 받기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                q.offer(new Node(r, c, direction, num));
            }

            // M시간 후...
            for (int i = 0; i < M; i++)
                move(q);

            // 큐에 남아있는 군집들의 미생물수를 모두 더한다.
            while(!q.isEmpty())
                total += q.poll().value;
            sb.append(total).append("\n");
        }
        System.out.println(sb);
    }// main End

    static void move(Queue<Node> q){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 큐에 넣어놓은 군집들을 하나씩 빼서 이동시킨 후 pq에 넣는다.
        while(!q.isEmpty()){
            Node newNode = direction(q.poll());
            pq.offer(newNode);
        }

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int sum = 0;
            int size = map[node.r][node.c].size();
            if(map[node.r][node.c].isEmpty()) continue; // 해당 좌표가 비었다는 것은 이미 미생물수 더 많은애가 흡수해갔다는 뜻, pass한다.
            for (int j = 0; j < size; j++) { // 미생물 모두 흡수한 후
                sum += map[node.r][node.c].remove(0);
            }
            q.offer(new Node(node.r, node.c, node.direction, sum)); // 흡수한 미생물과 자신의 방향을 유지한채 큐에 넣는다.
        }
    }// move End


    static Node direction(Node node){
        int r = node.r + dir[0][node.direction];
        int c = node.c + dir[1][node.direction];

        // 경계에 들어갔을 때 (미생물 수는 절반, 방향 반대)
        if(r == 0 || c == 0 || r == N-1 || c == N-1){
            node.value /= 2;
            if(node.direction % 2 == 1){
                node.direction ++;
            } else{
                node.direction --;
            }
        }

        // map에 미생물 수 모아놓고 노드 반환
        map[r][c].add(node.value);
        return new Node(r, c, node.direction, node.value);
    }
}