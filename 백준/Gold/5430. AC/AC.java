import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String function = br.readLine();
			
			int N = Integer.parseInt(br.readLine());
			String array = br.readLine();
			
			int[] numbers = null;
			
			if(N == 0) {
				numbers = new int[0];
			} else {
				numbers = Arrays.stream(array.substring(1, array.length()-1).split(","))
						.mapToInt(Integer::parseInt)
						.toArray();	
			}
			
			
			sb.append(language(function, numbers)).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static String language(String function, int[] numbers) {
		Deque<Integer> dq = new ArrayDeque<>();
		for(int num : numbers) {
			dq.add(num);
		}
		
		int direction = 1;
		for(int i = 0; i < function.length(); i++) {
			if(function.charAt(i) == 'R') direction *= -1;
			else {
				if(dq.isEmpty()) return "error";
				
				if(direction > 0) {
					dq.removeFirst();
				} else {
					dq.removeLast();
				}
			}
		}
		
		return dqFormat(dq, direction);
	}
	
	static String dqFormat(Deque<Integer> dq, int direction) {
		StringBuilder sb = new StringBuilder("[");
		
		if(dq.isEmpty()) return sb.append("]").toString();
		
		if(direction > 0) {
			sb.append(dq.pollFirst());
			while(!dq.isEmpty()) {
				sb.append(",").append(dq.pollFirst());
			}
		} else {
			sb.append(dq.pollLast());
			while(!dq.isEmpty()) {
				sb.append(",").append(dq.pollLast());
			}
		}
		
		sb.append("]");
		return sb.toString();
	}
}