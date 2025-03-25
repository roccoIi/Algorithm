import java.io.*;
import java.util.*;

public class Main {
	static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> pq_less = new PriorityQueue<>((s1, s2) -> Integer.compare(s2, s1));
        PriorityQueue<Integer> pq_more = new PriorityQueue<>();
        
        for(int i = 0; i < N; i++) {
        	int curr = Integer.parseInt(br.readLine());
        	
        	if(pq_less.size() == pq_more.size()) pq_less.add(curr);
        	else pq_more.add(curr);
        	
        	if(!pq_less.isEmpty() && !pq_more.isEmpty()) {
        		
        		if(pq_more.peek() < pq_less.peek()) {
        			int temp = pq_more.poll();
        			pq_more.add(pq_less.poll());
        			pq_less.add(temp);
        		}
        	}
        	sb.append(pq_less.peek()).append('\n');
        }
        
        System.out.println(sb);
    }
}