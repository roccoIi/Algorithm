import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /*
    *  <풀이방식>
    *   1. 숫자 두개를 파라미터로 받는 메소드를 생성한다. ( '앞 앞 수', '앞 수')
    *   2. 파라미터로 쓰였던 '앞 수'를 첫번째 인자로, '앞 앞 수'와 '앞 수'의 차를 두번째 인자로 넣어서 재귀시킨다.
    *   3. 재귀로 들어가면서 파라미터로 들어가는 숫자들을 list에 넣고 카운트도 하나씩 늘려나간다.
    *   4. 재귀가 끝났을때 카운트 길이가 가장 길다면 최댓값을 갱신하고 list를 배열로 변환하여 저장해놓는다.
    *   5. 그렇게 주어지는 testCase의 숫자만큼 반복한다. (ex. 100이 주어지면 100번 반복)
    */

    static int cnt;
    static List<Integer> list;
    static Integer[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int number = Integer.parseInt(br.readLine());
        int maxNum = Integer.MIN_VALUE;


        for (int i = 1; i <= number; i++) {
            list = new ArrayList<>(); // 리스트에 첫번째 숫자와 두번째 숫자를 넣어놓고 시작, 반복마다 list 초기화
            list.add(number);
            list.add(i);
            cnt = 2; // 2개 넣고 시작했으니깐 cnt = 2
            minus(number, i); // 재귀
            if(cnt > maxNum) { // 최대값 갱신일때 배열도 새로 생성하여 갱신
                maxNum = cnt;
                arr = list.toArray(new Integer[list.size()]);
            }
        }
        
        //출력
        sb.append(maxNum).append("\n");
        for (Integer i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
    
    static void minus(int start, int target){
        int a = start - target; // 두 수의 차이가 다음 파라미터가 된다.
        if(a < 0) return; // 그게 음수면 종료
        
        list.add(a); // 정상적으로 진행된다면 파라미터들을 전부 수집하고 카운트 증가
        cnt++;
        minus(target, a);
    }
}