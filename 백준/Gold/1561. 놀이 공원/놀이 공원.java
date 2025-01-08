import java.io.*;
import java.util.*;

public class Main {
	static int M, arr[];
	static long N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[M+1];
		for(int i = 1; i <= M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N > M) {
			long maxTime = binarySearch(N * 30, N - M);
			long studentGap = N - countStudent(maxTime - 1) - M;
			
			for(int i = 1; i <= M; i++) {
				if(maxTime % arr[i] == 0) studentGap--;
				
				if(studentGap == 0) {
					System.out.println(i);
					return;
				}
			}
		} else {
			System.out.println(N);
			return;
		}
	}
	
	static long binarySearch(long max, long target) {
		long left = 0;
		long right = max;
		long mid = left + (right - left) / 2;
		
		while(left <= right) {
//			System.out.println(mid);
			if(countStudent(mid) >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
			
			mid = left + (right - left) / 2;
		}
		
		return mid;
	}

	static long countStudent(long time) {
		long count = 0;
		for(int i = 1; i < arr.length; i++) {
			count +=  time / arr[i];
		}
		return count;
	}

}