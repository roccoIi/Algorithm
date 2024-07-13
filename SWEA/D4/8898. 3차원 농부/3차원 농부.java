import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int T = 1; T <= testCase; T++) {
            sb.append("#").append(T).append(" ");

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 소는 몇마리?
            int M = Integer.parseInt(st.nextToken()); // 말은 몇마리?
            int minDistance = Integer.MAX_VALUE; // 가장 짧은 거리
            int count = 0; // 가장 짧은 거리는 총 몇번 등장?

            // 소와 말의 x값 차이 구하기 (어차피 계산할땐 |x1 - x2| 로 고정이기 때문에 미리 계산)
            st = new StringTokenizer(br.readLine());
            int distance = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));

            // 소 위치 입력받기
            int[] cows = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cows[i] = Integer.parseInt(st.nextToken());
            }

            // 이분탐색은 정렬되어있다는 조건에서 가능하다.
            Arrays.sort(cows);

            // 말들 위치 입력받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int horse = Integer.parseInt(st.nextToken());
                int hIndex = binarySearch(cows, horse); // 줄서있는 소 사이에서 말의 위치는?

                // 오른쪽 소와의 거리 차이
                if (hIndex < cows.length) { // 말의 위치가 줄서있는 소 맨 오른쪽이 아닐때만 진행
                    int dis = cows[hIndex] - horse;
                    if (dis < minDistance) {
                        minDistance = dis;
                        count = 1;
                    } else if (dis == minDistance) {
                        count++;
                    }
                }

                // 왼쪽 소와의 거리 차이
                if (hIndex - 1 >= 0) {// hIndex가 음수가 아닐때만 진행한다. (맨 왼쪽이 아닐라면 음수여선 안됨)
                    int dis = horse - cows[hIndex - 1];
                    if (dis < minDistance){
                        minDistance = dis;
                        count = 1;
                    } else if (dis == minDistance) {
                        count++;
                    }
                }
            }
            sb.append(minDistance + distance).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    } //main End

    static int binarySearch(int[] arr, int num) {
        int idx = arr.length;
        int left = 0;
        int right = arr.length-1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (arr[mid] >= num) {
                idx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return idx;
    }
}