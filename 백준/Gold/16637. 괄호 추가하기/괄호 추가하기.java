import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
* [회고]
* 1. 사칙연산에 대한 조건을 어떻게 받을까 하다가 숫자 조건이 0~9인것을 보고 그냥 11, 22, 33을 임시로 정해서 각각을 연산자로 썼다.
* 2. 두가지 조건으로 나뉘었다.
*   2-1. 현재 숫자와 내 뒤의 숫자를 바로 연산할 경우
*   2-2. 내 뒤 숫자 2개를 우선 연산(괄호) 한 후 현재 숫자와 연산하는 경우
* 3. 각각의 조건을 재귀로 넣어서 현재 인덱스가 총 배열의 길이를 벗어날 때 최댓값 연산을 했다.
* 4. 연산은 각각의 연산자에 따라 계산되도록 메소드를 만들었다.
*
*
* */
public class Main {
    static int N, arr[], maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N];
        maxSum = Integer.MIN_VALUE;

        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            if(str.charAt(i) == '-'){
                arr[i] = 11;
            } else if (str.charAt(i) == '+') {
                arr[i] = 22;
            } else if (str.charAt(i) == '*') {
                arr[i] = 33;
            } else {
                arr[i] = str.charAt(i) - '0';
            }
        }

        dfs(arr[0], 0);

        System.out.println(maxSum);
    }

    static void dfs(int sum, int idx){
        if (idx >= N-1) {
            maxSum = Math.max(maxSum, sum);
            return ;
        }

        //1. 현재 숫자와 내 뒤 숫자를 괄호로 묶어서 연산 할 경우
        if(idx < N-4)
            dfs(calculate(sum, arr[idx+1], calculate(arr[idx+2], arr[idx+3], arr[idx+4])), idx+4);

        //2. 현재 숫자와 내 뒤 숫자를 바로 연산 할 경우
        if(idx < N-2)
            dfs(calculate(sum, arr[idx+1], arr[idx+2]), idx+2);
    }

    static int calculate(int num1, int operator, int num2) {
        if (operator == 33) {
            return num1 * num2;
        } else if (operator == 22) {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    }

}