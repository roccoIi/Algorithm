import java.io.*;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int[] arrA = new int[Integer.parseInt(st.nextToken())];
            int[] arrB = new int[Integer.parseInt(st.nextToken())];
            int maxNum = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < arrA.length; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < arrB.length; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }
            if (arrA.length < arrB.length) {
                for (int i = 0; i <= arrB.length - arrA.length; i++) {
                    int sum = 0;
                    for (int j = 0; j < arrA.length; j++) {
                        sum += arrB[i+j] * arrA[j];
                    }
                    maxNum = Math.max(sum, maxNum);
                }
            } else {
                for (int i = 0; i <= arrA.length - arrB.length; i++) {
                    int sum = 0;
                    for (int j = 0; j < arrB.length; j++) {
                        sum += arrA[i+j] * arrB[j];
                    }
                    maxNum = Math.max(sum, maxNum);
                }
            }
            sb.append("#").append(t).append(" ").append(maxNum);
            System.out.println(sb);
        }
    }
}