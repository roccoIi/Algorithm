import java.io.*;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int cnt;

        for (int i = 1; i <= num; i++) {
            String str = Integer.toString(i);
            if (str.contains("3") || str.contains("6") || str.contains("9")) {
                cnt = 0;
                for (int j = 0; j < str.length(); j++) {
                    if(str.charAt(j) == '3' || str.charAt(j) == '6' || str.charAt(j) == '9') cnt++;
                }
                for (int j = 0; j < cnt; j++) {
                    sb.append("-");
                }
            } else{
                sb.append(str);
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }
}