import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(br.readLine());

        for (int k = 1; k <= a; k++) { // TestCase 만큼 반복
            int cnt = 0; // 현재 기립박수 하고 있는 사람 수
            int needPeople = 0; // 전체 기립박수를 위해 필요한 사람의 수
            // 문자열로 주어지기 때문에 BufferedReader 로 값을 입력받고 배열 생성
            String ar = br.readLine();
            int[] arr = new int[ar.length()];
            for (int i = 0; i < ar.length(); i++) {
                arr[i] = ar.charAt(i)-'0';
            }
            // 필요한 사람의 수 계산
            for (int i = 0; i < ar.length(); i++) {
                if (cnt >= i) { // 배열의 번호가 기립박수를 위해 필요한 사람의 수
                    cnt += arr[i];
                } else {
                    needPeople += i - cnt;
                    cnt += i - cnt + arr[i]; // 필요한 사람의 수 + 그 때 기립박수를 하려던 사람의 수
                }
            }
            System.out.printf("#%d %d\n", k, needPeople);
        }
    }
}