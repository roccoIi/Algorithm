import java.io.*;
import java.util.*;

public class Main {
	static long[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			arr = new long[101];
			Arrays.fill(arr, -1);
			
			arr[0] = 0L;
			arr[1] = 1L;
			arr[2] = 1L;
			
			sb.append(getLength(N)).append('\n');
		}
		
		System.out.println(sb);

	}
	
	static long getLength(int num) {
		if(arr[num] == -1) {
			arr[num] = getLength(num-2) + getLength(num-3);
		}
		
		return arr[num];
	}
}