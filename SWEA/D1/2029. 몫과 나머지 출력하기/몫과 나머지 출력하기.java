import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int time = sc.nextInt();
		
		for(int i = 1; i<=time; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			System.out.println("#" + i + " " + a/b + " " + a%b);
		}
	}
}