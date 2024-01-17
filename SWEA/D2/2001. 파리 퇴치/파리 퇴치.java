import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int k = 1; k <= T; k++) {
            int max = Integer.MIN_VALUE;
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            int hand = sc.nextInt();

            // 주어진 배열 입력받기
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    arr[r][c] = sc.nextInt();
                }
            }
            
            // 행 우선으로 돌면서 차례로 검사
            // n-(hand-1) 은 파리채의 크기만큼 필요한 만큼만 순회하기 위해서
            // 예를들이 5x5배열에서 파리채의 크기가 2x2라면 (4,4) 일때 파리채가 (5,5)까지 커버하면서 더 나아갈 필요가 없어진다.
            for (int r = 0; r < n-(hand-1); r++) {
                for (int c = 0; c < n - (hand - 1); c++) {
                    int sum = 0;
                    // 시작점을 파리채의 크기를 기준으로 잡고 파리채 크기만큼 순회
                    // ex) 시작점이 (2,2) , 파리채 2x2 일 경우
                    // (2,2), (2,3), (3,2), (3,3) 순서대로 돌면서 값들을 sum에 누적한다.
                    for (int i = 0; i < hand; i++) {
                        for (int j = 0; j < hand; j++) {
                            sum += arr[r+i][c+j];
                        }
                    }
                    // 최댓값 찾기
                    if(sum > max)
                        max = sum;
                }
            }
            System.out.printf("#%d %d\n", k, max);
        }

    }
}