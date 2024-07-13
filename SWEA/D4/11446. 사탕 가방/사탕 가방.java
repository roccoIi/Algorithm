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
            int N = Integer.parseInt(st.nextToken()); // 캔디 종류
            long M = Long.parseLong(st.nextToken()); //가방에는 M+a개가 있어야 한다.
            long left = 1L; // 가방 1개부터
            long right = 0L; // 가방 max개 까지. (가장 많은 캔디 수)
            long idx = 0;

            long[] candies = new long[N]; // 내가 가진 캔디들의 수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                candies[i] = Long.parseLong(st.nextToken());
                right = Math.max(right, candies[i]);
            }

            while (left <= right) {
                long mid = left + (right - left) / 2; // 가방 mid개로 캔디들을 균등하게 나눠서 만들어볼 예정
                long candy = 0L;

                for (int i = 0; i < N; i++) {
                    candy += candies[i] / mid; // mid개의 가방에 넣을꺼니깐 각 캔디들을 mid개로 나눈 몫을 각 가방에 넣는다.
                }

                if (candy >= M) { // 가방안에 들어간 캔디들이 M개보다 많다면 통과!
                    idx = mid;
                    left = mid + 1; // 범위를 더 좁혀가면서 찾는다.
                } else {
                    right = mid - 1;
                }
            }
            sb.append(idx).append("\n");
        }
        System.out.println(sb);
    } //main End
}