import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int start, end, cost;
		
		Node(int start, int end, int cost){
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	static int V, E, parents[];
	static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        parents = new int[V+1];
        for(int i = 0; i <= V; i++) {
        	parents[i] = i;
        }
        
        list = new ArrayList[V+1];
        for(int i = 0; i <= V; i++) {
        	list[i] = new ArrayList<>();
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	
        	pq.add(new Node(start, end, cost));
        }
        
        System.out.println(kruskal(pq));  
    }
    
    static int kruskal(PriorityQueue<Node> pq) {
    	int answer = 0;
    	while(!pq.isEmpty()) {
    		Node curr = pq.poll();
    		
    		if(findSet(curr.start) == findSet(curr.end)) continue;
    		unionSet(curr.start, curr.end);
    		answer += curr.cost;
    	}
    	
    	return answer;
    }
    
    static int findSet(int x) {
    	if(parents[x] == x) return x;
    	else return parents[x] = findSet(parents[x]);
    }
    
    static void unionSet(int x, int y) {
    	x = findSet(x);
    	y = findSet(y);
    	
    	parents[y] = x;
    }
    
}