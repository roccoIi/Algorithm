import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++){
            String result = ""; // 결과 출력할 문자열

            // 첫번째 카드를 배열로 만들기 위한 함수 셋트
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st1.nextToken());
            int[] arr = new int[N+1];  // 두 카드의 길이가 다를 때를 대비하여 while문을 끝내기 위해 한칸을 비워둔다(0으로)
            for(int i = 1; i < arr.length; i++){ // 아무리 크기가 작은 모양이더라도 0보단 크니 해당 모양의 승리로 체크하기 위하여
                arr[i] = Integer.parseInt(st1.nextToken());
            }

            // 두번째 카드를 배열로 만들기 위한 함수 셋트
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st2.nextToken());
            int[] arr2 = new int[M+1];
            for(int i = 1; i < arr2.length; i++){
                arr2[i] = Integer.parseInt(st2.nextToken());
            }

            Arrays.sort(arr); Arrays.sort(arr2); // 오름차순 정렬

            while (true) { // 뒤에서부터 탐색하면서 크기가 큰 쪽이 발생한다면 해당 카드의 승리로 확인하고 while 종료
                if(arr[N] > arr2[M]){
                    result = "A";
                    break;
                } else if(arr[N] < arr2[M]){
                    result = "B";
                    break;
                }

                if (--N < 0 || --M < 0) {
                    result = "D";
                    break; // 끝까지 승부를 내지 못하고 배열을 모두 탐색했다면 무승부로 종료
                }
            }
            // 결과 출력
            System.out.println(result);

        }
    }
}
