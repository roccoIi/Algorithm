import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 1; i <= a; i++) {
            for (int j = i; i * j <= a; j++) {
                count++;
            }
        }

        System.out.println(count);
    }
}