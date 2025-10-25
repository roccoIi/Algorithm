import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] numbers = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		
		int[] answer = mainSolution(numbers);
		
		StringBuilder sb = new StringBuilder();	
		sb.append(answer[0]);
		
		for(int i = 1; i < answer.length; i++) {
			sb.append(" ").append(answer[i]);
		}
		
		System.out.println(sb);
	}
	
	static int[] mainSolution(int[] numbers) {
		int[] num = new int[1<<20];
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < numbers.length; i++) {
			int x = numbers[i] / 32;
			int y = numbers[i] % 32;
			
			if((num[x] & (1 << y)) != 0) continue;
			
			num[x] |= (1 << y);
			list.add(numbers[i]);
		}
		
		int[] answer = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
	}
}