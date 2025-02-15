import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int num = 1;
		int[] arr = new int[10];
		
		for(int i = 0; i < 3; i++) {
			num *= Integer.parseInt(br.readLine());
		}
		
		while(num > 0) {
			arr[num % 10]++;
			num /= 10;
		}
		
		for(int i = 0; i < 10; i++) {
			sb.append(arr[i]).append('\n');
		}
		
		System.out.println(sb);
	}
}