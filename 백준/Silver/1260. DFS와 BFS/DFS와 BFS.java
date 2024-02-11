import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] check; // 한번 체크한 숫자인지 아닌지 확인하기 위한 boolean 배열
    static int node;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        node = Integer.parseInt(st.nextToken());
        int line = Integer.parseInt(st.nextToken());
        int rootNode = Integer.parseInt(st.nextToken());
        check = new boolean[node+1];

        arr = new int[node+1][node+1];
        for (int i = 0; i < line; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = arr[b][a] = 1; // 인접 행렬 생성 (노드들간의 간선이 존재한다는 것을 의미)
        }

        dfs(rootNode);
        sb.append("\n");
        check = new boolean[node+1]; // dfs -> bfs 넘어가면서 check배열 초기화.
        bfs(rootNode);

        System.out.println(sb);
    }

    // dfs(깊이 우선 탐색) 구현
    public static void dfs(int rootNode) {
        check[rootNode] = true;
        sb.append(rootNode).append(" ");

        for (int i = 1; i <= node; i++) { // 모든 노드의 수만큼 반복 진행
            if(arr[rootNode][i] == 1 && !check[i]) // 그 숫자가 다른 숫자들과 간선이 있는지 && 그 숫자를 체크했던 적이 있는지 확인
                dfs(i);  // 모든 조건을 만족한다면 해당 숫자로 재귀 시작
        }
    }

    // bfs(넓이 우선 탐색) 구현
    public static void bfs(int rootNode){
        Queue<Integer> queue = new LinkedList<>(); // bfs를 위한 큐 생성 (FIFO)
        queue.add(rootNode);
        check[rootNode] = true;

        while (!queue.isEmpty()) { // 큐가 다 빌때까지 진행
            int number = queue.poll(); // 가장 먼저 들어간 숫자 꺼내서
            sb.append(number).append(" ");
            for (int i = 1; i <= node; i++) { // 모든 노드의 수만큼 반복 진행
                if (arr[number][i] == 1 && !check[i]) { // 그 숫자가 다른 숫자들과 간선이 있는지 && 그 숫자를 체크했던 적이 있는지 확인
                    queue.add(i);  // 모든 조건을 만족하며 큐에 해당 숫자 입력
                    check[i] = true;
                }
            }
        }
    }
}