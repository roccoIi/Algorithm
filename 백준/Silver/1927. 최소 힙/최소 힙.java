import java.io.*;
import java.util.*;


public class Main {
	static int[][] dir = {{1, 0, 0}, {0, -1, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if(n != 0) pq.add(n);
			else {
				if(pq.isEmpty()) sb.append(0).append('\n');
				else sb.append(pq.poll()).append('\n');
			}
		}
		
		System.out.println(sb);
	}
	
	static int mainSolution(int[][] map) {
		return 0;
	}
}