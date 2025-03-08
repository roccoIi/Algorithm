import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int idx, distance;
		
		Node(int idx, int distance){
			this.idx = idx;
			this.distance = distance;
		}
	}
	static int N, M, C, computerCnt, dist[];
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Node>[] list;
	static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	int T = Integer.parseInt(br.readLine());
    	while(T-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		N = num(st.nextToken());
    		M = num(st.nextToken());
    		C = num(st.nextToken());
    		
    		computerCnt = 0;
    		visited = new boolean[N+1];
    		dist = new int[N+1];
    		Arrays.fill(dist, INF);
    		dist[C] = 0;
    		
    		list = new ArrayList[N+1];
    		for(int i = 1; i <= N; i++) {
    			list[i] = new ArrayList<>();
    		}
    		
    		for(int i = 0; i < M; i++) {
    			st = new StringTokenizer(br.readLine());
    			int A = num(st.nextToken());
    			int B = num(st.nextToken());
    			int C = num(st.nextToken());
    			if(A == B) continue;
    			list[B].add(new Node(A, C));
    		}
    		getTime(new Node(C, 0));
    		
    		int maxNum = -1;
    		for(int i = 1; i <= N; i++) {
    			if(dist[i] != INF) maxNum = maxNum < dist[i] ? dist[i] : maxNum;
    			if(visited[i]) computerCnt++;
    		}
    		
    		sb.append(computerCnt).append(" ").append(maxNum).append('\n');
    	}

    	System.out.println(sb);
    }
    
    static void getTime(Node start) {
    	PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) 
				-> Integer.compare(s1.distance, s2.distance));
		pq.add(start);
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.idx]) continue;
			visited[curr.idx] = true;
			
			for(Node next : list[curr.idx]) {
				if(dist[next.idx] > dist[curr.idx] + next.distance) {
					dist[next.idx] = dist[curr.idx] + next.distance;
					pq.add(new Node(next.idx, dist[next.idx]));
				}
			}
		}
    }
   
    
    static int num(String str) {
    	return Integer.parseInt(str);
    }
}