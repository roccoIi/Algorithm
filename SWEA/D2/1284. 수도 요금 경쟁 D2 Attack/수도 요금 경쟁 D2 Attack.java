import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		
		for(int i = 1; i <= a; i++) {
			int literPerPrice = sc.nextInt();
			int underPrice = sc.nextInt();
			int standard = sc.nextInt();
			int underLiterPerPrice = sc.nextInt();
			int waterAmount = sc.nextInt();
			
			int aCompany = literPerPrice * waterAmount;
			int bCompany = 0;
			if(waterAmount <= standard) {
				bCompany = underPrice;
			} else {
				bCompany = underPrice + ((waterAmount - standard)*underLiterPerPrice);
			}
			
			if(aCompany > bCompany) {
				System.out.println("#" + i + " " + bCompany);
			} else {
				System.out.println("#" + i + " " + aCompany);
			}
		}
	}
}