import java.util.*;
import java.io.*;

public class Main {
	static int s, e, visited[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		visited = new int[100001];
		
		System.out.println(bfs(s));
	}	
	
	public static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		visited[start] = 1;
		
		int size = q.size();
		
		while(!q.isEmpty()) {
			for(int i = 0; i < size; i++) {
				int curr = q.poll();
				
				if(curr == e) return visited[e]-1;
				
				if(curr + 1 <= 100000 && visited[curr+1] == 0) {
					visited[curr+1] = visited[curr] + 1;
					q.add(curr+1);
				}
				
				if(curr - 1 >= 0 && visited[curr-1] == 0) {
					visited[curr-1] = visited[curr] + 1;
					q.add(curr-1);
				}
				
				if(curr * 2 <= 100000 && visited[curr*2] == 0) {
					visited[curr*2] = visited[curr] + 1;
					q.add(curr*2);
				}
			}
		}
		
		return -1;
	}
}