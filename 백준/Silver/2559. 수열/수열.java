import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[Integer.parseInt(st.nextToken())];
		int sum;
		int maxSum = Integer.MIN_VALUE;
		
		int days = Integer.parseInt(st.nextToken());
		
		// 배열 생성
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 배열 돌면서 합 구하기
		for(int i = 0; i <= arr.length - days; i++) {
			sum = 0;
			for(int j = i, day = 0; day < days; day++) {
				sum += arr[j++];
			}
			// 최대값 구하기
			if(sum > maxSum) maxSum = sum;
		}
		
		System.out.println(maxSum);
	}
}