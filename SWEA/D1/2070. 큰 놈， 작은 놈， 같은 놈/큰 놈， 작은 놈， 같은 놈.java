import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int a = sc.nextInt();
		for(int i = 1; i <= a; i++) {
			int b = sc.nextInt();
			int c = sc.nextInt();
			if(b > c) {
				System.out.println("#" + i + " >");
			} else if (b == c) {
				System.out.println("#" + i + " =");
			} else {
				System.out.println("#" + i + " <");
			}
		}
	}
}