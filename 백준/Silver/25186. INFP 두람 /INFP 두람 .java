import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int maxNum = -1;
		long totalNum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			maxNum = maxNum < num ? num : maxNum;
			totalNum += num;
		}
		
		if(N == 1 && maxNum == 1) {
			System.out.println("Happy");
		} else if(totalNum / 2 < maxNum){
			System.out.println("Unhappy");
		} else {
			System.out.println("Happy");
		}
	}
}