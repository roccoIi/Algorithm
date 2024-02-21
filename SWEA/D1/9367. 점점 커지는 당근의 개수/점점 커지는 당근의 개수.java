import java.io.*;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            StringBuilder sb = new StringBuilder();
            int[] arr = new int[Integer.parseInt(br.readLine())];
            st = new StringTokenizer(br.readLine());
            int sum = 1 ;
            int maxNum = Integer.MIN_VALUE;


            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > arr[i - 1]) {
                    sum++;
                } else{
                    maxNum = Math.max(maxNum, sum);
                    sum = 1;
                }
            }
            maxNum = Math.max(maxNum,sum);

            sb.append("#").append(t).append(" ").append(maxNum);
            System.out.println(sb);
        }
    }
}