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

            long N = Long.parseLong(br.readLine()); //주어진 양초의 수
            long left = 1L; // 1단부터
            long right = 10000000000L; // 10억단 까지 생각해보자.
            long idx = 0; // 몇단까지 올릴 수 있을까?

            while (left <= right) {
                long mid = left + (right - left) / 2;
                long value = mid * (mid + 1) / 2;

                if (N >= value) { // 내가 가진 양초의 양이 mid 단을 구성하기에 충분하다!!
                    idx = mid;
                    left = mid + 1; // 최솟값을 mid까지 올려서 다시 계산해보자
                } else {
                    right = mid - 1; // 최댓값을 mid 아래로 내려서 다시 계산해보자
                }
            }

            long value = idx * (idx + 1) / 2;
            if (N != value) { // idx단을 구성하는데 있어 필요한 양초의 개수가 N과 다르다면 삼각형이 아니다.
                idx = -1;
            }

            sb.append(idx).append("\n");

        }
        System.out.print(sb);
    } //main End
}