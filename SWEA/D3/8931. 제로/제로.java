import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int t = 1; t <= testCase; t++) {
			Stack<Integer> cost = new Stack<>();
			int k = Integer.parseInt(br.readLine());
			int sum = 0;
			
			// 입력값을 받아 0이 아니라면 push, 0이라면 pop
			for(int i = 0; i < k ;i++) {
				int num = Integer.parseInt(br.readLine());
				if(num != 0) {
					cost.push(num);
				} else if(num == 0){
					cost.pop();
				}
			}
			
			// stack이 빌때까지 pop하여 sum에 누적하기.
			while(!(cost.isEmpty())) 
				sum += cost.pop();
					
			// 최종 출력
			System.out.printf("#%d %d\n", t, sum);
		}
	}
}