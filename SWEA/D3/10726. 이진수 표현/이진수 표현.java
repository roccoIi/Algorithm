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
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int target = (1<<N)-1;


            if((M & target) == target) sb.append("ON").append("\n");
            else sb.append("OFF").append("\n");
        }

        System.out.println(sb);
    }
}