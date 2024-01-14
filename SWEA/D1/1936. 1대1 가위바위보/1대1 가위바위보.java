import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		

		int player1 = sc.nextInt();
        int player2 = sc.nextInt();

        if (player1 == 1) {
            if (player2 == 2) {
                System.out.println("B");
            } else {
                System.out.println("A");
            }
        } else if (player1 == 2) {
            if (player2 == 3) {
                System.out.println("B");
            } else {
                System.out.println("A");
            }
        } else {
            if (player2 == 1) {
                System.out.println("B");
            } else {
                System.out.println("A");
            }
        
        }
	}
}