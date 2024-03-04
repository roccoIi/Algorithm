import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 * [회고] 
	 * 	1. DP 공부 후 처음 풀어보는 문제인만큼 어떻게 진행되는지 감을 잡아야했다.
	 * 	2. 가장 햇갈렸던것은 초기조건(score[1], score[2])이 누구고 어떻게 설정해야할지
	 * 	3. 개념을 어덯게 잡아서 반복되는 조건을 설정해야할지 였다.
	 *  4. i번째 계단에서 결국 구해야 할것은 내 자신과 i-1번째를 포함하느냐 안하느냐 였다. (2연속 / 독립)
	 *  5. 즉 i번째 계단에 더해야하는 값이 "i-2번째 까지의 누적" or "i-1번째의 값과 i-3까지의 누적" 둘 중 하나였다.
	 *  
	 */
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1]; //  	
		int[] score = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		score[1] = arr[1];
		if(N>1) { // 계단이 1개라면 => 바로 종료되고 score[1] 출력
			score[2] = arr[2] + arr[1]; // 계단이 2개 뿐이여도 아래 반복문에 들어가지 못하고 score[2]출력			
			for(int i = 3; i <= N; i++) {
				score[i] = arr[i] + Math.max(score[i-3] + arr[i-1], score[i-2]); // 여기서 주목해야 할 것은 i번째와 i-1번째는 누적이 아니라는 것이다.
			}	 															     // 연속해서 2개까지만 담을 수 있기 때문에 i번째와 i-1을 연속해서 담을 것인지 (이 경우 i-3까지의 누적을 더해줘야 한다.)
		}																		 // 아니면 i-1번째를 담지 않고 i-2번까지의 누적을 담을 것인지 골라야 한다.	
		
																			 
		System.out.println(score[N]); // 마지막 계단은 밟아야한다는 조건

	}
}