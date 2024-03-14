import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * [회고]
 *  1. 가장 처음엔 bfs로 풀면 되겠다고 생각했지만 서로 연결되어있어야 하다는 조건에 막혔다.
 *  2. 두번째로 그럼 dfs로 풀면 될까 싶었지만 역시나 구역이 서로 연결되어있어야 한다는 조건에서 막혔다.
 *  3. 조합으로 구하면 될까 싶었지만 역시나 서로 연결되어있어야 한다는 조건을 어떻게 해결해야할지가 관건이였고
 *  4. 결국 서로 연결되어있음을 증명하지 못하면 풀수 없다고 생각해 서로 연결부터 생각했다.
 *  5. 구질구질하게 연결을 증명했다. 배열안에 4개가 있다면 4개 모두 체크하면서 각각의 요소가 다르 요소 중  최소 하나와 연결되어있는지 확인했다.
 *  6. 만들어야하는 2개의 배열 (A 구역, B 구역)은 한쪽 배열을 먼저 만들고 남은애들을 나머지 배열에 넣었다.
 * --------------------------------------------------------------------------------------------------------
 *  7. 연결을 증명하는 과정을 좀 더 추가했다.
 *  8. 각 요소가 최소 하나씩과는 연결되어있는지 + 서로 연결되어있는 간선의 수가 최소 n-1개인지
 * --------------------------------------------------------------------------------------------------------
 *  9. bfs로 처음 진행하면 첫번째 구역은 모두 연결된것이 증명될텐데...라고 생각하다가 깨달았다.
 *  10. 그럼 연결확인을 bfs로 하면 되잖아?
 */

public class Main {
    static boolean flag;
    static int N, minNum;
    static int[] population;
    static boolean[] visit;
    static boolean[][] indexArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        minNum = Integer.MAX_VALUE; // 최소값
        population = new int[N+1]; // 인구수 배열
        visit = new boolean[N+1]; // 방문배열
        indexArr = new boolean[N+1][N+1]; // 인접배열
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            for (int j = 0; j < index; j++) {
                indexArr[i][Integer.parseInt(st.nextToken())] = true; // 서로 연결이 있는 인덱스끼리 true로 전환
            }
        }

        for (int i = 1; i <= N / 2; i++) { // 절반 이상부터는 이전 조합 반복이므로 절반만 진행해도 충분
            int[] arr = new int[i]; // A구역이 1개, B구역이 N-1개 일때부터 AB구역이 N/2개씩 나눠가질때까지 반복한다.
            int[] arr2 = new int[N-i];
            search(0, 1, i, arr, arr2);
        }

        if(flag) System.out.println(minNum); // 최소값이 있으면 출력, 없으면 -1
        else System.out.println(-1);


    }
    static void search(int cnt, int idx, int num, int[] temp1, int[] temp2){
        if(cnt == num){  // 배열을 다 채우면 기저조건
            int length = 0;
            for (int i = 1; i <= N; i++) { // temp1 배열 채우고 남은 숫자로 temp2 채우기
                if(!visit[i]) temp2[length++] = i;
            }

            if(linkCheck(temp1) && linkCheck(temp2)){ // 둘다 모두 연결되어있는 구역이라면 카운트 진행
                if(!flag) flag = true; // 깃발이 돌아갔다는 것은 어떻게든 두개의 선거구로 나뉘었다는 뜻
                int sumTemp1 = 0;
                int sumTemp2 = 0;
                for (int i = 0; i < temp1.length; i++) {
                    sumTemp1 += population[temp1[i]];
                }
                for (int i = 0; i < temp2.length; i++) {
                    sumTemp2 += population[temp2[i]];
                }

                minNum = Math.min(minNum, Math.abs(sumTemp1 - sumTemp2)); // 인구수 차이 최솟값 구하기
            }
            return;
        }

        // 조합 알고리즘
        for (int i = idx; i <= N; i++) {
            temp1[cnt] = i;
            visit[i] = true;
            search(cnt+1, i+1, num, temp1, temp2);
            visit[i] = false;
        }
    }
    // 연결확인 (bfs진행)
    static boolean linkCheck(int[] arr) {
        boolean[] check = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(arr[0]);
        check[arr[0]] = true;

        while(!q.isEmpty()){
            int num = q.poll();
            for (int i = 1; i <= N; i++) {
                if(indexArr[i][num] && !check[i] && arrayCheck(i, arr)) {
                    q.offer(i);
                    check[i] = true;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if(!check[arr[i]]) return false;
        }
        return true;
    }
    static boolean arrayCheck(int num, int[] arr){ // num이 배열의 요소인가 체크
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == num) return true;
        }
        return false;
    }
}