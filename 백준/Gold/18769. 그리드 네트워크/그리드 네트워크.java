import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c, cost;
		
		Node(int r, int c, int cost){
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}
	static int R, C;
	static ArrayList<Node>[][] map;
	static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	R = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	
        	visited = new boolean[R][C];
        	map = new ArrayList[R][C];
        	initGraph();
        	
        	for(int i = 0; i < 2; i++) {
        		initEdges(br, i);
        	}
                	
        	PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));
        	pq.add(new Node(0, 0, 0));
        	
        	int answer = 0;
        	while(!pq.isEmpty()) {
        		Node curr = pq.poll();
        		
        		if(visited[curr.r][curr.c]) continue;
        		visited[curr.r][curr.c] = true;
        		
        		for(Node next : map[curr.r][curr.c]) {
        			if(visited[next.r][next.c]) continue;
        			pq.add(next);
        		}
        		
        		answer += curr.cost;
        	}
        	
        	sb.append(answer).append('\n');
        }
        
        System.out.println(sb);
    }
    
    static void initGraph() {
    	for(int r = 0; r < R; r++) {
    		for(int c = 0; c < C; c++) {
    			map[r][c] = new ArrayList<Node>();
    		}
    	}
    }
    
    static void initEdges(BufferedReader br, int flag) throws IOException {
    	StringTokenizer st;
    	
    	for(int r = 0; r < R - flag; r++) {
    		st = new StringTokenizer(br.readLine());
    		for(int c = 0; c < C - 1 + flag; c++) {
    			int cost = Integer.parseInt(st.nextToken());
    			map[r][c].add(new Node(r + flag, c + 1 - flag, cost));
    			map[r + flag][c + 1 - flag].add(new Node(r, c, cost));
    		}
    	}
    }
}