import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int number;
    int relation;
    Node(){};
    Node(int number, int relation){
        this.number = number;
        this.relation = relation;
    }
}

public class Main {
    /*
    *  <풀이방식>
    *   1. bfs를 통해 주어진 촌수를 확인한다.
    *   2. 몇 촌인지 확인하기 위해선 내가 지나온 관계의 개수를 파악해야한다. (queue.poll 할 때마다 카운트를 올리면 모든 경우에서 +1이 된다.)
    *       => 이를 위해 노드 클래스를 생성하고 노드에 숫자와 지나온 관계의 개수를 넣어준다.
    *   3. 노드의 숫자를 확인하고 그 숫자가 내가 찾으려는 관계와 일치한다면 Node.relation 을 출력하고 즉시 메서드 종료
    *   4. 만일 중간에 종료되지 못하고 끝까지 진행한다면 직접적인 관계가 없는 사이이므로 -1 출력
    */
    static int[][] arr;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int peopleNumber = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int target1 = Integer.parseInt(st.nextToken()); // 찾으려는 사람 1
        int target2 = Integer.parseInt(st.nextToken()); // 찾으려는 사람 2
        int relation = Integer.parseInt(br.readLine()); // 관계 개수
        arr = new int[peopleNumber+1][peopleNumber+1]; // 사람숫자를 그대로 배열에 넣기 위해 배열을 한칸 크게 만든다(0번 인덱스는 깍두기)

        for (int i = 0; i < relation; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = arr[b][a] = 1; // 관계 표현
        }

        System.out.println(bfs(target1, target2));


    }

    static int bfs(int a, int b){
        Queue<Node> queue = new LinkedList<>(); // 노드를 인자로 받아준다.
        queue.offer(new Node(a, 0)); // 처음엔 아무런 관계를 지나오지 않았기 때문에 0

        while (!queue.isEmpty()){
            Node node = queue.poll();
            int num = node.number; // 노드의 숫자와
            int relation = node.relation; // 그 노드가 지나온 관계의 수
            if(num == b) return relation; // 숫자가 일치한다면 즉시 종료
            
            for (int i = 1; i < arr.length; i++) {
                if (arr[i][num] == 1) { // 숫자가 관계 있을때 관계 +1 하고 큐에 넣는다.
                    queue.offer(new Node(i,relation+1));
                    arr[i][num] = arr[num][i] = 0;
                }
            }
        }
        return -1; // 중간에 종료되지 못하고 여기까지 왔다면 -1 리턴
    }
}