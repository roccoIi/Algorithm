import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] arr = new int[2][2];
		
		for(int r = 0; r < 2; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[r][0] = Integer.parseInt(st.nextToken());
			arr[r][1] = Integer.parseInt(st.nextToken());
		}
		
		int numerator = (arr[0][0] * arr[1][1]) + (arr[0][1] * arr[1][0]);
		int denominator = arr[0][1] * arr[1][1];
		
		int gcd = euclidean(numerator, denominator);
		
		sb.append(numerator / gcd).append(" ").append(denominator / gcd);
		System.out.println(sb);
	}
	
	static int euclidean(int a, int b) {
		return b == 0 ? a : euclidean(b, a % b);
	}
}