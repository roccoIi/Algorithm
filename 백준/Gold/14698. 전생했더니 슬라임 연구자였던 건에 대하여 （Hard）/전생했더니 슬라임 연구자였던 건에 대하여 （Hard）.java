import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	final static long INF = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
        	N = Integer.parseInt(br.readLine());
        	
        	st = new StringTokenizer(br.readLine());
        	PriorityQueue<Long> pq = new PriorityQueue<>();     	
        	for(int i = 0; i < N; i++) {
            	pq.add(Long.parseLong(st.nextToken()));
        	}
        	
        	if(N == 1) {
        		sb.append(1).append('\n');
        		continue;
        	}
        	
        	long answer = 1;
        	while(pq.size() > 1) {
        		long num = pq.poll() * pq.poll();
        		
        		answer *= num % INF;
        		answer %= INF;
        		pq.add(num);
        	}
        	
        	sb.append(answer).append('\n');
        }  
        System.out.println(sb);
    }
}