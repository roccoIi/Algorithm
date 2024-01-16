import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int a = sc.nextInt();
		
		for(int i = 1; i <= a; i++) {
			int sum = 0;
			int num = sc.nextInt();
			for(int j = 1; j <= num; j++) {
				if(j % 2 == 0) {
					sum -= j;
				} else {
					sum += j;
				}
			}
			System.out.println("#" + i + " " + sum);
		}
	}
}