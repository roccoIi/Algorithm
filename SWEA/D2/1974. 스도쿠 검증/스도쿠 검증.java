import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int[][] arr = new int[9][9];

        //델타 배열
        int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int k = 1; k <= T; k++) {
            boolean flag= false;
            // 스도쿠 채우기
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    arr[r][c] = sc.nextInt();
                }
            }
            // 행 검사
            for (int r = 0; r < 9; r++) {
                int[] check = new int[9];
                for (int c = 0; c < 9; c++) {
                    check[arr[r][c]-1] ++;
                }
                for (int c = 0; c < 9; c++) {
                    if (check[c] == 0) {
                        flag = true;
                    }
                }
            }

            // 열 검사
            for (int c = 0; c < 9; c++) {
                int[] check = new int[9];
                for (int r = 0; r < 9; r++) {
                    check[arr[r][c]-1]++;
                }
                for (int r = 0; r < 9; r++) {
                    if (check[r] == 0) {
                        flag = true;
                    }
                }
            }

            // 3x3 검사
            for (int i = 1; i < 9; i +=3) {
                for (int j = 1; j < 9; j += 3) {
                    int[] check = new int[9];
                    check[arr[i][j]-1]++;
                    for (int d = 0; d < 8; d++) {
                        int ni = i + dr[d];
                        int nj = j + dc[d];
                        check[arr[ni][nj]-1]++;
                        // check배열에서 -1을 하는 이유
                        // 스도쿠 안에는 1~9까지 있으나 check배열은 배열번호가 0~8로 이뤄져있어 범위를 벗어난다.
                    }
                    for (int r = 0; r < 9; r++) {
                        if (check[r] == 0) {
                            flag = true;
                        }
                    }
                }
                
            }

            if (flag == false) {
                System.out.printf("#%d 1\n", k);
            } else {
                System.out.printf("#%d 0\n", k);
            }
        }


    }
}