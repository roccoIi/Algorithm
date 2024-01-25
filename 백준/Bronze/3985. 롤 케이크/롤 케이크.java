import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rollCake= Integer.parseInt(br.readLine());
        int[] arr = new int[rollCake];
        int start = 0;
        int end = 0;
        int people= Integer.parseInt(br.readLine()); // 방청객 수
        int maxNum = Integer.MIN_VALUE; // (실제) 최대로 가져간 케익 조각
        int maxNumThink = Integer.MIN_VALUE; // (예상) 최대로 가져갈 것 같았던 케익 조각
        int peopleNum = -1; //(실제) 방청객 번호
        int peopleNumThink = -1; // (예상) 방청객 번호

        for (int i = 1; i <= people; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            // 가장 많이 가져갈 것으로 예상되는 최댓값 찾고 그때의 방청객 번호 저장
            if(end - start > maxNumThink) {
                maxNumThink = end - start;
                peopleNumThink = i;
            }
            // 실제 가져가는 조각의 수 계산
            // 0으로 이뤄진 배열에서 가져가면 1로 변경하고 1이면 아무런 조치 없이 pass
            int count = 0;
            for (int j = start-1; j < end; j++) {
                if (arr[j] == 0) {
                    arr[j] = 1;
                    count++;
                }
            }
            // 가져간 갯수 최댓값 구하고 그때의 방청객 번호 저장
            if (count > maxNum) {
                maxNum = count;
                peopleNum = i;
            }
        }
        System.out.printf("%d\n%d", peopleNumThink, peopleNum);
    }
}