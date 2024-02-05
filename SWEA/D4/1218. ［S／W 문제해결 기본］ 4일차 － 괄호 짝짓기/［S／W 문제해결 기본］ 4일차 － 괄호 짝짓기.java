import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			Stack<Object> stack = new Stack<>();
			int num = Integer.parseInt(br.readLine());
			String str = br.readLine();
			int answer = 1;
			
			for(int i = 0; i < num; i++) {
				if(str.charAt(i) == ')') { // 입력되는 것이 닫는 괄호라면 if문 시작
					if(stack.peek().equals('(')) { // top이 각 괄호의 짝이라면
						stack.pop(); // pop(삭제)
					} else {
						answer = 0; // 짝이 아니라면 출력 answer을 0으로 변경 후 반복문 즉시종료
						break;
					}
				} else if(str.charAt(i) == '}') {
					if(stack.peek().equals('{')) {
						stack.pop();
					} else {
						answer = 0;
						break;
					}
				} else if(str.charAt(i) == ']') {
					if(stack.peek().equals('[')) {
						stack.pop();
					} else {
						answer = 0;
						break;
					}
				} else if(str.charAt(i) == '>') {
					if(stack.peek().equals('<')) {
						stack.pop();
					} else {
						answer = 0;
						break;
					}
				} else {
					stack.push(str.charAt(i)); // 여는 괄호라면 stack에 push
				}
			}	
			System.out.printf("#%d %d\n", t, answer);	
		}
	}
}