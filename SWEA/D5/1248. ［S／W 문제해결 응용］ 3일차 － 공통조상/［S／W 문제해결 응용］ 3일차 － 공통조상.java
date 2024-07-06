import java.io.*;
import java.util.*;

/**
 * 문제 기록
 * - 정점의 개수 V(10 ≤ V ≤ 10000)와 간선의 개수 E, 공통 조상을 찾는 두 개의 정점 번호
 * - 두 번째 줄에는 E개 간선이 나열 (부모 자식 순서)
 */


public class Solution {
    static int V, E, node_1, node_2;
    static List<Integer> ancestorA, ancestorB;
    static Node[] node;
    static class Node{
        List<Integer> children;
        int parents;

        public Node() {
            this.children = new ArrayList<>();
            this.parents = 0;
        }
    }

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int T = 1; T <= testCase; T++) {
            sb.append("#").append(T).append(" ");
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            node_1 = Integer.parseInt(st.nextToken());
            node_2 = Integer.parseInt(st.nextToken());
            ancestorA = new ArrayList<>();
            ancestorB = new ArrayList<>();

            node = new Node[V+1];
            for (int i = 0; i <= V; i++) {
                node[i] = new Node();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                node[p].children.add(c);
                node[c].parents = p;
            }

            // node_1과 node_2의 모든 조상들 구하기
            findAncestor(node_1, ancestorA);
            findAncestor(node_2, ancestorB);

            // LCA 찾기
            int answer = 0;
            for (int i = 0; i < V; i++) {
                if(!ancestorA.get(i).equals(ancestorB.get(i))) break;
                answer = ancestorA.get(i);
            }

            sb.append(answer).append(" ").append(countChild(answer)).append("\n");
        }
        System.out.println(sb);
    } //main End

    // 자식 개수 세기
    static int countChild(int num) {
        int count = 1;
        for (int i = 0; i < node[num].children.size(); i++) {
            count += countChild(node[num].children.get(i));
        }
        return count;
    }

    static void findAncestor(int num, List<Integer> ancestor) {
        int parent = node[num].parents;
        if (parent != 0) {
            findAncestor(parent, ancestor);
        }
        ancestor.add(num);
    }
}