import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());
        String seat = br.readLine();
        int lCount = 0;
        for (int i = 0; i < people; i++) {
            if (seat.charAt(i) - 'A' == 11) //좌석의 각 글자가 L일 경우
                lCount++;
        }
        if (lCount < 4) { // 커플 2명까지는 컵홀더가 부족하지 않다.
            System.out.println(people);
        } else { // 그 이상부터는 컵홀더 품귀현상 발생
            int anw = people - ((lCount / 2) - 1);
            System.out.println(anw);
        }
    }
}