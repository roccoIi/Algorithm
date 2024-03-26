import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		long cnt=1;
		int count=0;
		for(int i=1;i<=n;i++) {
			cnt*=i;
			while(cnt%10==0) {
				count++;
				cnt/=10;
			}
			cnt%=100000000;
		}
		
		System.out.println(count);
	}
}