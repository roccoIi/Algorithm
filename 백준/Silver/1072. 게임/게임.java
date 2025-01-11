import java.io.*;
import java.util.*;

public class Main {
	static int X, Y;
	static int standard;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		standard = (int)((long) Y * 100 / X);
		
		int answer = -1;
		int left = 0;
		int right = X;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;

			if(isChange(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}	
		}
		
		System.out.println(answer);
	}
	
	static boolean isChange(int add) {
		if((int)(((long) Y + add) * 100 / (X + add)) <= standard) {
			return false;
		}
		return true;
	}	
}