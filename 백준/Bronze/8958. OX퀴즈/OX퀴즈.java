import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int quest = Integer.parseInt(br.readLine());

        for (int i = 0; i < quest; i++) {
            int count = 1;
            int totalPoint = 0;
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'O') {
                    totalPoint += count;
                    count++;
                } else {
                    count = 1;
                }
            }
            System.out.println(totalPoint);
        }
    }
}