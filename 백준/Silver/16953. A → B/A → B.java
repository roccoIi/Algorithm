import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int count = 1;
		
		while(B >= A) {
			if((B & 1) == 0) {
				B = B >> 1;
			} else if (B % 10 == 1) {
				B /= 10;
			} else {
				System.out.println("-1");
				return;
			}
			
			count++;
			
			if(A == B) {
				System.out.println(count);
				return;
			}
		}
		System.out.println("-1");	
	}
}