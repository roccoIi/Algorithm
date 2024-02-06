import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Character, Integer> map = new HashMap<>();
		
		map.put('+', 1);
		map.put('*', 2);
		map.put('(', 0);
		
		
		for(int t = 1; t <= 10; t++) {
			Stack<Character> stack = new Stack<>();
			int length = Integer.parseInt(br.readLine());
			String str = br.readLine();
			String temp = "";
			int sum = 0;
			int count = 0;
			
			
			// 후위표기식 작성
			for(int i = 0; i < length; i++) {
				char c = str.charAt(i);
				if('0'<=c && c<='9') {
					temp += c;
				} else if (c == '('){
					count++;
					stack.push(c);
				} else if (c == ')'){
					count++;
					while(stack.peek() != '(') {
						temp += stack.pop();
					}
					stack.pop();
				} else {
					if(stack.isEmpty()) {
						stack.push(c);
					} else {
						while(!stack.isEmpty() && map.get(c) <= map.get(stack.peek())) {
							temp += stack.pop();
						}
						stack.push(c);
					}
				}
			}
			while(!stack.isEmpty()) {
				temp += stack.pop();
			}
			
			// 후위표기식 계산
			Stack<Integer> newStack = new Stack<>();
			for(int i = 0; i < length-count; i++) {
				char c = temp.charAt(i);
				
				if('0' <= c && c <= '9') {
					newStack.push(c-'0');
				} else {
					if(map.get(c) == 1) {
						sum = newStack.pop() + newStack.pop();
					} else {
						sum = newStack.pop() * newStack.pop();
					}
					newStack.push(sum);
				}
			}
			System.out.printf("#%d %d\n", t, sum);
		}
	}
}