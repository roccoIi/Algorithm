import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String number = br.readLine();
        
        // 덱에 숫자를 하나씩 넣어보면서, 가장 마지막 숫자보다 큰 숫자가 들어올때만 마지막수 제거하고 새로운 수를 넣는다.
        // 이때 숫자가 제거되었으므로 count는 +1 처리한다. (이미 목표로 한 숫자만큼 수를 제거했다면, 이후 제거는 없다.)
        // 목표로 한 숫자만큼의 수를 제거했다면 반복문은 종료된다.
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i = 0; i < number.length(); i++) {
        	int num = number.charAt(i) - '0';
        	
        	while(K > 0 & !dq.isEmpty() && dq.peekLast() < num) {
        		dq.removeLast();
        		K--;
        	}
        	
        	dq.add(num);
        }
        
        while(K-- > 0) {
        	dq.removeLast();
        }
        
        while(!dq.isEmpty()) {
        	sb.append(dq.pollFirst());
        }
        
        System.out.println(sb.toString());
    }
}