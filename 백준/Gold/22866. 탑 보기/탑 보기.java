import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int index, height;
		
		Node(int index, int height){
			this.index = index;
			this.height = height;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[N+1];
        int[] index = new int[N+1];
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	index[i] = 987654321;
        }
        
        Stack<Node> stack = new Stack<>();
        int[] size = new int[N+1];
        for(int i = 1; i <= N; i++) {
        	
        	// 1) 현재 건물의 높이보다 큰 건물이 나올때까지 비운다.
        	while(!stack.isEmpty() && stack.peek().height <= arr[i]) {
        		stack.pop();
        	}
        	
        	// 2) 현재 스택의 size가 볼 수 있는 건물의 수다.
        	// 2) 그때의 Index를 저장한다.
        	size[i] += stack.size();
        	if(stack.size() > 0) index[i] = stack.peek().index;
        	
        	// 3) 현재 건물의 번호와 높이를 넣는다.
        	stack.push(new Node(i, arr[i]));
        }
        
        stack = new Stack<>();
        
        for(int i = N; i >= 1; i--) {
        	
        	// 1) 현재 건물의 높이보다 큰 건물이 나올때까지 비운다.
        	while(!stack.isEmpty() && stack.peek().height <= arr[i]) {
        		stack.pop();
        	}
        	
        	// 2) 현재 스택의 size가 볼 수 있는 건물의 수다.
        	// 2) 그때의 Index를 저장한다.
        	size[i] += stack.size();
        	if(stack.size() > 0) {
        		if(Math.abs(index[i] - i) > Math.abs(stack.peek().index - i)){
        			index[i] = stack.peek().index;
        		}
        	}
        	
        	// 3) 현재 건물의 번호와 높이를 넣는다.
        	stack.push(new Node(i, arr[i]));
        }
        
        for(int i = 1; i <= N; i++) {
        	if(size[i] == 0) sb.append(0).append('\n');
        	else {
        		sb.append(size[i]).append(" ").append(index[i]).append('\n');
        	}
        }
        
        System.out.println(sb);
    } 
}