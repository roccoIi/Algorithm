import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int targetNum = Integer.parseInt(st.nextToken());
        int[] cards = new int[T];
        int nowSum = Integer.MIN_VALUE; //현재 3개 숫자의 합
        int nowDiffer = Integer.MAX_VALUE; // 목표숫자 - 현재 3개 숫자의 합
        int anw = Integer.MAX_VALUE; // 차이가 가장 적을때의 숫자 합
        int mostLow = Integer.MAX_VALUE; // 최저 차이

        for (int i = 0; i < T; i++) { // 배열 넣기
            cards[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < T; i++) {
            for (int j = 0; j < T; j++) {
                if(i != j) { // 배열 중복 방지
                    for (int k = 0; k < T; k++) {
                        if(i != k && j != k) //배열 중복 방지
                            nowSum = cards[i] + cards[j] + cards[k];

                        if(targetNum - nowSum >=0) // targetNum을 넘지 않으면서 가장 가까운 카드
                            nowDiffer = targetNum - nowSum;

                        if(nowDiffer < mostLow) { // 최소값 찾기
                            mostLow = nowDiffer;
                            anw = nowSum; // 그때의 숫자의 합 정답
                        }
                    }
                }
            }
        }
        System.out.println(anw);
    }
}