import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());

        for (int T = 1; T <= cases; T++) {
            int stick = 0;
            int oneCount = 0;
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    oneCount ++;
                } else {
                    if (str.charAt(i - 1) == '(') {
                        oneCount--;
                        stick += oneCount;
                    } else {
                        stick++;
                        oneCount--;
                    }
                }
            }
            System.out.printf("#%d %d\n", T, stick);
        }
    }
}