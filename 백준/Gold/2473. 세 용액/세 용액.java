import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long minGap, arr[], answer[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		arr = new long[N];
		answer = new long[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		// 오름차순 정렬
		Arrays.sort(arr);
		
		// 차잇값 초기화 후 계산
		minGap = Long.MAX_VALUE;
		for(int i = 0; i < N-2; i++) {
			twoPointer(i);
		}
		
		// 정답배열 정렬
		Arrays.sort(answer);
		
		// 입력 후 출력
		for(int i = 0; i < 3; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);	
	}
	
	static void twoPointer(int idx) {
		int left = idx + 1;
		int right = N - 1;
		
		while(left < right) {
			long sum = arr[left] + arr[idx] + arr[right];
				
			if(Math.abs(sum) < minGap) {
				minGap = Math.abs(sum);
				answer[0] = arr[left];
				answer[1] = arr[idx];
				answer[2] = arr[right];
			}
			
			if(sum < 0) left++;
			else if (sum > 0) right--;
			else return;
		}
	}
}