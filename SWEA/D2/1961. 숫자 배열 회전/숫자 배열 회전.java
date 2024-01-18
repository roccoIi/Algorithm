import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		int T = sc.nextInt();
		for(int i = 1; i <= T; i++) {
			int a = sc.nextInt();
			int[][] arr = new int[a][a];
			
			// 제시되는 배열 만들기
			for(int r = 0; r<a;r++) {
				for (int c = 0; c<a;c++) {
					arr[r][c] = sc.nextInt();
				}
			}
			
			System.out.printf("#%d\n", i);
			
			for(int r = 0; r < a; r++) {
				for(int c = 0; c < a; c++) {
					System.out.print(arr[a-c-1][r]);
				}
				System.out.print(" ");
				for(int c = 0; c<a; c++) {
					System.out.print(arr[a-r-1][a-c-1]);
				}
				System.out.print(" ");
				for(int c = 0; c < a; c++) {
					System.out.print(arr[c][a-r-1]);
				}
				System.out.println();
			}
		}
	}
}