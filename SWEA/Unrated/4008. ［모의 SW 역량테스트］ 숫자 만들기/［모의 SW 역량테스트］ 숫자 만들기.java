import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	// 필요한 배열 및 변수 생성
	static int N, symbol[], numbers[], answer, maxNum, minNum, newF[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) { // 총 T개의 TestCase
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			symbol = new int[4]; // 주어지는 기호는 총 4개
			numbers = new int[N]; // 주어지는 숫자는 총 N개
			newF = new int[N-1]; // 주어지는 기호의 개수는 총 N-1개
			minNum = Integer.MAX_VALUE;
			maxNum = Integer.MIN_VALUE;
			
			for(int i = 0; i < 4; i++) { // 기호 배열에 기호 입력
				symbol[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0; i < N; i++) { // 숫자 배열에 숫자 입력
				numbers[i] = Integer.parseInt(st2.nextToken());
			}
			
			makeNew(0);					
			
			answer = maxNum - minNum;	// 문제에서 원하는 것은 최댓값과 최솟값의 차이
			System.out.printf("#%d %d\n", t, answer); // 출력.
			
		}
	}
	
	public static void makeNew(int cnt) {
		if(cnt == N-1) { // 여기서 cnt는 사용된 기호의 개수를 의미한다.
			caculate();  // 총 6개의 기호가 주어졌을 때 배열 0번부터 5번까지 총 6개의 기호를 사용하고 
		}				 // cnt가 6이 되면 계산을 시작한다.
		
		for(int i = 0; i < 4; i++) { // 기호 배열은 총 4까지 존재한다. (+, -, *, /)
			if(symbol[i] == 0) continue; // 기호배열 ( 예를들어 (2, 1, 1, 0) ) 에서 
										 // (0, 1, 1, 0) 이 됐다면 0번째를 continue 하고 1번째를 탐색하자
			symbol[i]--;				 // 사용한 기호는 -한다. (카운팅 정렬에서 누적합 하나 줄이는것과 비슷)
			newF[cnt] = i;				 // newF는 기호배열을 무엇이 얼마나 사용되는지로 나타낸다. (2, 1, 1, 0) => (0, 0, 1, 2)
			makeNew(cnt+1);				 // 재귀함수를 통해 완전탐색을 시도한다.
			symbol[i]++;                 // 해당 재귀가 끝났다면 내가 사용했던 symbol은 다시 원상복귀 시켜놓는다.
		}
	}
	public static void caculate() {
		int sum = numbers[0];			 // sum은 앞에서부터 계산을 시작한 값을 누적한다. 첫 시작은 숫자배열의 0번째 값
		for(int i = 0; i < N-1; i++) {   // 0은 +를, 1은 -를, 2는 *를, 3은 /를 나타낸다.
			if(newF[i] == 0) {			 // (2, 1, 1, 0) 배열은 +가 2번, -가 1번, *가 1번 사용된다는 뜻이고
				sum += numbers[i+1];	 // 이를 (0, 0, 1, 2)로 나타낸 것이다. (0번이 2번, 1번이 1번, 2번이 1번 사용)
			} else if (newF[i] == 1) {
				sum -= numbers[i+1];
			} else if (newF[i] == 2) {
				sum *= numbers[i+1];
			} else if (newF[i] == 3){
				sum /= numbers[i+1];
			}
		}
		
		if(sum > maxNum) 			 	 // 최댓값, 최솟값 구하기.
			maxNum = sum;
		if(sum < minNum)
			minNum = sum;
	}
}