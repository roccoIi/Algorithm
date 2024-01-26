import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sugar = Integer.parseInt(br.readLine());
        int max5Kg = sugar / 5;
        int max5KgPb = 0;
        int max3KgPb = -1; // 끝까지 조건 만족 못해면 -1 그대로 출력
        for (int i = max5Kg; i >= 0; i--) { //5kg봉지가 가장 많을때부터 1씩 줄여나가면서
            if ((sugar - (i * 5)) % 3 == 0) { // 남은 설탕이 3kg로 딱 나눠떨어질때 break
                max5KgPb = i;
                max3KgPb = (sugar - i * 5) / 3;
                break;
            }
        }
        System.out.println(max5KgPb+max3KgPb);
    }
}