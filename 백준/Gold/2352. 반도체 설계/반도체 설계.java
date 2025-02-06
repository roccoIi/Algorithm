import java.io.*;
import java.util.*;

public class Main {
	static int arr[], lis[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); 
		lis = new int[N];
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lis[0] = arr[0];
		int idx = 0;
		for(int i = 1; i < N; i++) {	
			if(arr[i] > lis[idx]) {
				lis[++idx] = arr[i];
			} else {
				int newIdx = binarySearch(0, idx, arr[i]);
				lis[newIdx] = arr[i];
			}
		}
		
		System.out.println(idx + 1);
	}
	
	static int binarySearch(int left, int right, int target) {
		int mid = 0;
		int answer = 0;
		
		while(left <= right) {
			mid = left + (right - left) / 2;

			if(lis[mid] >= target) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return answer;
	}
}