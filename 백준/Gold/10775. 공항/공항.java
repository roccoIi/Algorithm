import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    static int G, P, p[];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine().trim());
        P = Integer.parseInt(br.readLine().trim());
        int count = 0;
        p = new int[G+1];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < P; i++) {
            q.offer(Integer.parseInt(br.readLine().trim()));
        }

        // p배열 초기값 할당
        for (int i = 1; i <= G; i++) {
            p[i] = i;
        }

        while (!q.isEmpty()) {
            int num = q.poll();
            // 0은 없는 게이트인데 0을 가리키는건 더이상 채울 수 있는 공간이 없다는 뜻
            // 해당 게이트를 채워져있다면 -1게이트에 채울 예정이기때문에 1번 게이트가 채워지면 0번을 가리키게 된다.
            if(findSet(num) == 0) break;

            // break 안당했다면 해당 게이트가 1칸 적은(-1) 게이트를 가리키게 한다.
            unionSet(findSet(num), findSet(num)-1);

            // 다른 게이트를 가리킨다는 것은 해당 게이트에 격납했다는 뜻 => count +1
            count++;
        }

        System.out.println(count);

    }

    // 부모노드 찾기
    static int findSet(int x){
        if(x == p[x]) return x;
        else return p[x] = findSet(p[x]);
    }

    // 두 집합 통합 연산
    static void unionSet(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);
        if(px != py) p[px] = py;
    }

}