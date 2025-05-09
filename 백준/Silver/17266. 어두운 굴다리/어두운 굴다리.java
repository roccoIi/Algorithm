import java.util.*;
import java.io.*;

public class Main {
	static int N, M, arr[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(binarySearcy(0, N));
	}
	
	static int binarySearcy(int left, int right) {
		int answer = 0;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;
			
			if(canSee(mid)) {
				right = mid - 1;
				answer = mid;
			} else {
				left = mid + 1;
			}
		}
		return answer;
	}
	
	static boolean canSee(int height) {
		int curr = 0; //현재 가로등 불빛의 위치
		
		for(int i = 0; i < M; i++) {
			if(arr[i] - height > curr) return false;
			
			curr = arr[i] + height;
		}
		return curr >= N;
	}
}