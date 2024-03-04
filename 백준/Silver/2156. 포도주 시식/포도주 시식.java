import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 * [회고] 
	 * 	1. 2579번 계단오르기와 유사하게 3개 연속은 불가하다.
	 * 	2. 단 주의해야할 것은 마지막 와인을 반드시 마셔야 하는 것은 아니다.
	 * 	3. 마지막이 반드시 포함되어야 한다는 의미는 생각보다 컸다. 매 순간 최대값을 구할때 마지막 값이 포함되어있는 값 뿐만 아니라
	 * 	   포함되어있지 않은 경우의 수까지 최대값 연산에 포함시켜야 했다.
	 *  4. 즉, max값을 구해야 할 항목들은 "arr[n]+total[n-2] // arr[n] + arr[n-1] + total[n-3] // total[n-1]" 이렇게 3가지 이다.
	 *  
	 */
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] wine = new int[N+1];  	
		int[] total = new int[N+1];
		for(int i = 1; i <= N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		int maxNum = total[1] = wine[1]; // 1잔일 때 최대값은 첫번째 와인
		if(N>1) { // 와인이 1잔이라면 => 바로 종료되고 total[1] 출력
			maxNum = total[2] = wine[2] + total[1]; // 와인이 2잔 뿐이여도 아래 반복문에 들어가지 못하고 total[2]출력	
			for(int i = 3; i <= N; i++) {
				maxNum = total[i] = Math.max(Math.max(wine[i] + wine[i-1]+total[i-3], wine[i] + total[i-2]), total[i-1]);	  
			}																	  
		}																		 																	 
		System.out.println(maxNum); 
	}
}