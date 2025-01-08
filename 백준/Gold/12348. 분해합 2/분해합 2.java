import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String number = br.readLine();
		long N = Long.parseLong(number);
		
		int boundary = number.length() * 9;
		
		for(long i = N - boundary; i < N; i++) {	
			long num = i;
			long sum = i;
			
			while(num > 0) {
				sum += (num % 10);
				num /= 10;
			}
			
			if(sum == N) {
				System.out.println(i);
				return;
			}
		}
		System.out.println("0");
	}
}