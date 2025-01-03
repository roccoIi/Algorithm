import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 3의 제곱수 합을 낮은 순서대로 정렬해보면
	 *    1 3 9 27 81
	 * 1) 1 0 0 0  0  -> 1
	 * 2) 0 1 0 0  0  -> 3
	 * 3) 1 1 0 0  0  -> 4
	 * 4) 0 0 1 0  0  -> 9
	 * 5) 1 0 1 0  0  -> 10
	 * 3진법으로 순서대로 올라가는게 낮은 순서라는 것을 확인할 수 있다.
	 * 즉, 4번째 순서라면 4를 이진법 비트로 나타내서 3진법에 적용하고
	 *    10번째 순서라면 10을 이진법 비트로 나타내서 3진법에 적용하면 된다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		long N = Long.parseLong(br.readLine());
		long answer = 0;
		long three = 1;
		
		// 주어진 N을 2진법으로 변환
		while(N > 0) {
			if((N & 1) == 1) {
				answer += three;
			}
			three *= 3;
			N = N >> 1;
		}

		System.out.println(answer);	
	}
}