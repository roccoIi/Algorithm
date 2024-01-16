import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int a = sc.nextInt();
		
		
		
		for(int i = 1; i <= a; i++) {
			int[] anw = new int[5];
			int num = sc.nextInt();
			
			while (num != 1) {
				if(num % 2 == 0) {
					num /= 2;
					anw[0]++;
				} else if (num % 3 == 0) {
					num /= 3;
					anw[1]++;
				} else if (num % 5 == 0) {
					num /= 5;
					anw[2]++;
				} else if (num % 7 == 0) {
					num /= 7;
					anw[3]++;
				} else if (num % 11 == 0) {
					num /= 11;
					anw[4]++;
				}
			}
			
			System.out.print("#" + i + " ");
			for(int j:anw) {
				System.out.print(j + " ");
			}
			System.out.println();
			
		}
	}
}