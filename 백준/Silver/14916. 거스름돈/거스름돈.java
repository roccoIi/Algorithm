import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int r=0;
		int ans=0;
		boolean flag= false;
		for(int i=n/5;i>=0;i--) {
			r=n-i*5;
			if(r%2==0) {
				
				ans=i+(r/2);
				flag=true;
				break;
			}
		}
		if(!flag) {
			System.out.println(-1);
			return;
		}
		System.out.println(ans);
		
	}
}