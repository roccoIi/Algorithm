import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] frontNum = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		
		StringBuilder sb = new StringBuilder();
		int[] answer = mainSolution(N, frontNum);
		sb.append(answer[0]);
		
		for(int i = 1; i < answer.length; i++) {
			sb.append(" ").append(answer[i]);
		}
		
		System.out.println(sb.toString());
	}
	
	static int[] mainSolution(int N, int[] frontNum) {
		List<Integer> answer = new ArrayList<>();
		
		for(int i = frontNum.length; i > 0; i--) {
			answer.add(frontNum[i-1], i);
		}
		
		return answer.stream()
				.mapToInt(Integer::intValue)
				.toArray();
	}
}