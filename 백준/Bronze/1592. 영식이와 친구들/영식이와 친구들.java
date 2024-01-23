import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalPerson = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int move = Integer.parseInt(st.nextToken());
        int totalMove = 0;
        int a = 0;
        
        // 총 인원 수 만큼 배열 생성
        int[] arr = new int[totalPerson];
        arr[0]++; //처음 공 받은 사람도 받은걸로 친다.

        
        here :while (true) {
            // 배열에 있는 사람 중 종료조건만큼 공을 받은사람이 존재하면 반복종료
            for (int i = 0; i < totalPerson; i++) {
                if (arr[i] == end) {
                    break here;
                }
            }
            // 홀수면 오른쪽(+)으로 짝수면 왼쪽(-)으로
            if (arr[a] % 2 == 1) {
                a += move;
            } else {
                a -= move;
            }
            
            // 양수면 a를 사람수로 나눈 나머지가 현재 위치
            // 총 인원수에서 a를 사람수로 나눈 나머지를 뺀 것이 현재 위치
            if (a < 0) {
                a = totalPerson + (a%totalPerson);
            } else {
                a = a % totalPerson;
            }
            // 움직일때마다 움직임+1, 움직인 위치 배열에 +1
            totalMove++;
            arr[a]++;
        }

        System.out.println(totalMove);


    }
}