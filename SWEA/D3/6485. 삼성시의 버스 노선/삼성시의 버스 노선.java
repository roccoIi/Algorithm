import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());

        for (int T = 1; T <= cases; T++) {
            int line = Integer.parseInt(br.readLine());
            int[] arr = new int[5000];
            for (int i = 0; i < line; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken());
                for (int j = start; j < end; j++) {
                    arr[j]++;
                }
            }
            int busStopCount = Integer.parseInt(br.readLine());
            int[] busArr = new int[busStopCount];
            for (int i = 0; i < busStopCount; i++) {
                int busStop = Integer.parseInt(br.readLine());
                busArr[i] = arr[busStop-1];
            }

            System.out.printf("#%d", T);
            for (int i = 0; i < busStopCount; i++) {
                System.out.print(" " + busArr[i]);
            }
            System.out.println();
        }
    }
}