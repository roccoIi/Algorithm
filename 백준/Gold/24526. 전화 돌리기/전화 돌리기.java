import java.util.*;
import java.io.*;

public class Main {
	static int N, M, p[];
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        p = new int[N+1];
        
        for(int i = 1; i <= N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	
        	// 연결리스트를 역방향으로 받는다.
        	list[end].add(start);
        	p[start]++;
        }
        
        System.out.println(topologySort());
	}
		
	static int topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) {
			if(p[i] == 0) q.add(i);
		}
		
		int answer = q.size();
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for(int next : list[curr]) {		
				if(--p[next] == 0) {
					q.add(next);
					answer++;
				}
			}
		}
		
		return answer;
	}
}
