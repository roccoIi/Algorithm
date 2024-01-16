import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		
		for(int i = 1; i <= a; i++) {
			String str = sc.next();
			int sum = 0;
			for(int j = 0; j < str.length()/2; j++) {
				if(str.charAt(j) == str.charAt(str.length()-j-1)) {
					continue;
				} else {
					sum++;
				}
			}
			
			if(sum == 0) {
				System.out.println("#" + i + " 1");	
			} else {
				System.out.println("#" + i + " 0");	
			}
		}
	}
}