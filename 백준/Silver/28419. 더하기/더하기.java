import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		long even = 0, odd = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N; i++) {
			if((i & 1) == 0) even += Integer.parseInt(st.nextToken());
			else odd += Integer.parseInt(st.nextToken());
		}
		
		if(N == 3 && odd > even) sb.append("-1");
		else sb.append(Math.abs(even - odd));
		
		System.out.println(sb);
	}
}