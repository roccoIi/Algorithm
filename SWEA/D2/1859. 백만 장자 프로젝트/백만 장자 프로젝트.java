import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; StringBuilder sb;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= testCase; t++) {
			int[] arr = new int[Integer.parseInt(br.readLine())];
			st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();
			
			// 주어진 배열 입력
			for(int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 가격의 최댓값과 이익의 합계 변수 선언
			int maxNum = arr[arr.length-1];
			long sum = 0;
			
			// 뒤에서부터 배열을 돌며 최댓값보다 높으면 갱신, 낮으면 차액 합산
			for(int i = arr.length-2; i >=0; i--) {
				if(arr[i] > maxNum) {
					maxNum = arr[i];
				} else {
					sum += maxNum - arr[i];
				}
			}
			
			sb.append("#").append(t).append(" ").append(sum);
			System.out.println(sb);
			
		}
		
		
	}
}