import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		System.out.println(mainSolution(x, b));
	}
	
	static String mainSolution(int x, int b) {
		if(x == 0) return "0";
		
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		if(b > 0) { //양수진법
			if(x < 0) {
				sb.append("-");
				x *= -1;
			}
			
			while(x != 0) {
				stack.add(x % b);
				x /= b;
			}
			
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			
			return sb.toString();
				
		}
		
		while(x != 0) {
			
			int remain = x % b;
			x /= b;
			
			if(remain < 0) {
				remain += Math.abs(b);
				x++;
			}
			
			stack.add(remain);
		}
		
		
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.toString();
	}
}