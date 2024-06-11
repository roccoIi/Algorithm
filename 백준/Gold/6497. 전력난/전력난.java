import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * - 길의 길이만큼 비용이 발생하고 구해야하는 것은 절약할 수 있는 최대 금액이다.
 * - 즉 모든 마을이 연결되면서 + 그 길이가 최소인 조건을 구해야 최대로 절약할 수 있다.
 *  => 최소신장트리(크루스칼, 프림) vs 최단거리 (다익스트라)
 *  - 이 문제에서 시작점은 중요하지 않다. 모두 연결되어있으면서 그 비용이 최소인것만 구하면 되기 때문에 최소신장트리이다.
 *  - 여기선 우선순위 큐와 인접리스트로 풀어본다.
 */

public class Main {

    static class Node implements Comparable<Node>{
        int start, end, value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            // V: 노드의 수, E: 간선의 수
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if(V == 0 && E == 0) break;
            int totalValue = 0;
            boolean[] check = new boolean[V];

            // 인접리스트 준비
            List<Node>[] list = new ArrayList[V];

            // 인접리스트 초기화
            for (int i = 0; i < V; i++) {
                list[i] = new ArrayList<>();
            }

            // 예제 입력
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                // 양방향 그래프
                list[start].add(new Node(start, end, value));
                list[end].add(new Node(end, start, value));
                totalValue += value;
            }

            // 우선순위 큐에 첫번째 노드와 연결된 모든 리스트를 넣는다. (0부터 시작, 뭐부터 시작해도 결과는 같음)
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (int i = 0; i < list[0].size(); i++) {
                pq.add(list[0].get(i));
            }
            check[0] = true;

            int count = 1;
            int ans = 0;

            while (count < V) {
                Node node = pq.poll();
                if(check[node.end]) continue; // 방문했다면 넘어가고 그 다음으로 길이가 짧은 마을을 가져온다.

                check[node.end] = true;
                ans += node.value;
                count++;

                // 해당 리스트 돌면서 미방문지역만 큐에 넣는다.
                for (int i = 0; i < list[node.end].size(); i++) {
                    if(check[list[node.end].get(i).end]) continue;
                    pq.add(list[node.end].get(i));
                }
            } // while문 종료
            sb.append(totalValue - ans).append("\n");
        }
        System.out.println(sb);
    }// main함수 종료
}