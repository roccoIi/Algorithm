import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        for (int T = 1; T <= k; T++) { // TestCase 만큼 반복
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            for (int i = 1; i <= q; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st2.nextToken());
                int p = Integer.parseInt(st2.nextToken());
                for (int j = m-1; j < p; j++) {
                    arr[j] = i;
                }
            }
            System.out.printf("#%d", T);
            for (int i = 0; i < n; i++) {
                System.out.print(" "+arr[i]);
            }
            System.out.println();
        }
    }
}