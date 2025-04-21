import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        while(N-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	int idx = Integer.parseInt(st.nextToken());
        	int height = Integer.parseInt(st.nextToken());
        	
        	// 건물의 높이가 현재 스택에 들어있는 가장 높은 수보다 작다면, 건물이 낮아졌다.
        	while(!stack.isEmpty() && stack.peek() > height) {
        		stack.pop();
        		answer++;        		
        	}
        	
        	// 건물의 높이가 현재 스택의 최대값과 같다면 높이가 같아졌으니 넘어간다.
        	if(!stack.isEmpty() && stack.peek() == height) {
        		continue; 
        	}
        	
        	// 여기까지 왔다면 더 높은 건물이 들어왔으니 스택에 넣는다.
        	if(height != 0) stack.push(height);
        }
        
        System.out.println(answer + stack.size()); 		
    }
}