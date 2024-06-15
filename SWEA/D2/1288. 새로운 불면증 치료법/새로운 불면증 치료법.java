import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int T = 1; T <= testCase; T++) {
            sb.append("#").append(T).append(" ");
            int target = Integer.parseInt(br.readLine());

            int bit = 0;
            int cnt = 1;
            while (true) {
                String tmp = Integer.toString(target * cnt);
                for (int i = 0; i < tmp.length(); i++) {
                    bit |= 1<<(tmp.charAt(i) - '0');
                }

                if (bit == ((1<<10)-1)) break;
                cnt++;
            }
            sb.append(target*cnt).append("\n");
        }
        System.out.println(sb);
    }


}