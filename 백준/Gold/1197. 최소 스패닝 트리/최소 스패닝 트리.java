import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int point, cost;
		
		Node(int point, int cost){
			this.point = point;
			this.cost = cost;
		}
	}
	static int V, E;
	static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[V+1];
        for(int i = 0; i <= V; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	
        	list[start].add(new Node(end, cost));
        	list[end].add(new Node(start, cost));
        }
        
        System.out.println(prim(new Node(1, 0)));
    }
    
    static int prim(Node node) {
    	PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));
    	boolean[] visited = new boolean[V+1];
    	
    	pq.add(node);
    	int answer = 0;
    	while(!pq.isEmpty()) {
    		Node curr = pq.poll();
    		
    		if(visited[curr.point]) continue;
    		visited[curr.point] = true;
    		
    		for(Node next : list[curr.point]) {
    			if(visited[next.point]) continue;
    			pq.add(next);
    		}
    		
    		answer += curr.cost;
    	}
    	
    	return answer;
    }
}