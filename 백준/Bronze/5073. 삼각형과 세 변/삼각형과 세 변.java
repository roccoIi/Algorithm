import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			HashSet<Integer> set = new HashSet<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>((s1, s2) -> s2 - s1);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 3; i++) {
				int num = Integer.parseInt(st.nextToken());
				set.add(num);
				pq.add(num);
			}
			
			if(pq.peek() == 0) break;
			
			if(pq.poll() >= pq.poll() + pq.poll()) {
				sb.append("Invalid").append('\n');
			} else if(set.size() == 3) {
				sb.append("Scalene").append('\n');
			} else if (set.size() == 2) {
				sb.append("Isosceles").append('\n');
			} else {
				sb.append("Equilateral").append('\n');
			}
		}
		
		System.out.println(sb);
	}
}