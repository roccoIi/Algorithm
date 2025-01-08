import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 1) 엘사의 눈사람을 먼저 정한다. (2중 for문)
	 * 2) 안나의 눈사람은 투포인터를 이용하여 구하게 된다.
	 * 	 2-1) 안나가 선택한 눈덩이가 이미 엘사의 눈덩이라면 패쓰
	 *   2-2) 엘사와 안나의 눈사람 차이의 절댓값을 갱신한다.
	 *   2-3) 엘사의 눈사람이 더 크다면 안나의 눈사람 크기를 더 키우기 위해 left++
	 *   2-4) 안나의 눈사람이 더 크다면 안나의 눈사람 크기를 더 줄이기 위해 right++
	 *   2-5) 만약 두 눈사람의 크기가 같다면 즉시 종료
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int minGap = Integer.MAX_VALUE;
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				int elsa = arr[i] + arr[j];
				
				int left = 0;
				int right = N - 1;
				
				while(left < right) {
					if(left == i || left == j) {
						left++;
						continue;
					}
					
					if(right == i || right == j) {
						right--;
						continue;
					}
					
					int anna = arr[left] + arr[right];
					minGap = minGap > Math.abs(elsa - anna) ? Math.abs(elsa - anna) : minGap;
					
					if(elsa > anna) {
						left++;
					} else if (elsa < anna) {
						right--;
					} else {
						System.out.println("0");
						return;
					}	
				}
			}
		}
		System.out.println(minGap);
	}
}