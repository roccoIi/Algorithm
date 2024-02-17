import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] diceIndex = {5, 3, 4, 1, 2, 0}; // 반대편 주사위 숫자 인덱스
    static int[] tempDiceNumber = new int[6]; // 주사위 각 주사위를 복사할 임시 배열
    static int[][] dice; // 실제 주사위들 입력받을 배열
    static int sum, diceNum, otherNumber, otherIdx;

    /*
    *  <전체 진행방식>
    *  1. 모든 주사위를 2차원배열에 입력받고
    *  2. 밑바닥이 1로 시작할때부터 6으로 시작할때까지의 모든 경우의 수를 구해서
    *  3. 그 중 누적된 합이 가장 큰 수를 구한다.
    *
    *  <재귀함수 진행방식>
    *  1. 첫번째 주사위의 밑바닥 숫자의 인덱스를 확인하고 그 인덱스의 반대편 인덱스를 diceIndex 배열을 통해 확인한다.
    *       => 0번째 숫자는 5번째 숫자와 평행, 1번째 숫자는 3번째 숫자와 평행, 2번째 숫자는 4번째 숫자와 평행
    *  2. 밑바닥과 윗변의 숫자를 0으로 만들고 남은 숫자 중 가장 큰 숫자를 구한다.
    *       => 이 때, 실제 주사위를 0으로 만들어버리면 진행이 안되니 해당 주사위를 복제해서 진행한다.
    *  3. 가장 큰 숫자를 누적해서 더하고 재귀로 들어간다. (모든 주사위를 다 탐색했다면 탈출)
    */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        diceNum = Integer.parseInt(br.readLine());
        dice = new int[diceNum+1][6]; // 아래 재귀함수에서 동일한 파라미터 사용을 위해 +1
        int realMax = Integer.MIN_VALUE;

        for (int r = 1; r <= diceNum; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 6; c++) {
                dice[r][c] = Integer.parseInt(st.nextToken()); // 주사위 입력
            }
        }

        for (int i = 1; i <= 6; i++) {
            sum = 0;
            int candidate = rolling(1, i, dice[1]); // 주사위 밑바닥이 1일때 ~ 6일때 숫자들의 합 구해서
            if(candidate > realMax) realMax = candidate;  // 최대값 도출
        }

       System.out.println(realMax); // 출력
    }

    static int rolling(int start, int targetNumber, int[] realDice) {
        System.arraycopy(realDice, 0, tempDiceNumber, 0, 6); // 주사위 하나씩 임시배열에 복사
        int maxNum = Integer.MIN_VALUE; // 위,아래 값 제외하고 가장 큰 값 저장할 변수

        for (int i = 0; i < 6; i++) {
            if(tempDiceNumber[i] == targetNumber) { // 목표하는 주사위 숫자를 찾으면
                tempDiceNumber[i] = 0; // 해당 숫자 0으로 만들고
                otherIdx = diceIndex[i]; // 그 숫자의 인덱스를 찾아서
                otherNumber = tempDiceNumber[otherIdx]; // 반대편 숫자 인덱스를 구해오고
                tempDiceNumber[otherIdx] = 0; // 반대편 숫자도 0으로 만든다.
            }
        }

        for (int j : tempDiceNumber) {
            if (j > maxNum) maxNum = j; // 남은 숫자중 최댓값 구하기
        }
        sum += maxNum; // 누적

        if(start +1 == diceNum+1) return 0; // 인덱스가 범위를 벗어나면 종료조건이 되기에 여기서 종료

        rolling(start+1, otherNumber, dice[start+1]); // 종료조건 아니면 그 다음 주사위 탐색

        return sum; // 종료됐을때 누적 합계 출력
    }
}