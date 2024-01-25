import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> rList = new ArrayList<>();
        List<Integer> cList = new ArrayList<>();
        int R = 0;
        int C = 0;
        int minNum = Integer.MAX_VALUE;
        int count = 0;

        String str = br.readLine();

        for (int i= 1; i <= str.length(); i++) {
            int a = str.length() / i;
            if (i <= a && i * a == str.length()) {
                rList.add(i);
                cList.add(a);
            }
        }
        for (int i = 0; i < rList.size(); i++) {
            if (cList.get(i) - rList.get(i) <= minNum) {
                minNum = cList.get(i) - rList.get(i);
                R = rList.get(i);
                C = cList.get(i);
            }
        }
        char[][] arr = new char[R][C];
        for (int r = 0; r < C; r++) {
            for (int c = 0; c < R; c++) {
                arr[c][r] = str.charAt(count++);
            }
        }
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(arr[r][c]);
            }
        }
    }
}