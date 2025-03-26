import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int vertex, edge;
		
		Node(int vertex, int edge){
			this.vertex = vertex;
			this.edge = edge;
		}
	}
	static int N, answer[][], total;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        
        N = Integer.parseInt(br.readLine());
        answer = new int[N+1][N+1];
        
        st = new StringTokenizer(br.readLine());
        LinkedList<Node> list = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	Node node = new Node(i, Integer.parseInt(st.nextToken()));
        	total += node.edge;
        	list.add(node);
        }

        if((total & 1) == 1) sb.append(-1);
        else {
        	findGraph(list);
        	
        	for(int r = 1; r <= N; r++) {
            	for(int c = 1; c <= N; c++) {
            		sb.append(answer[r][c]).append(" ");
            	}
            	sb.append('\n');
            }
        }
        
        System.out.println(sb);
    }
    
    static void findGraph(LinkedList<Node> list) {
    	while(!list.isEmpty()) {
        	list.sort((s1, s2) -> Integer.compare(s2.edge, s1.edge));
        	
        	Node curr = list.remove(0);
        	
        	for(int i = 0; i < curr.edge; i++) {
        		Node next = list.remove(0);
        		answer[curr.vertex][next.vertex]++;
        		answer[next.vertex][curr.vertex]++;
        		
        		if(next.edge - 1 == 0) continue;
    			list.add(new Node(next.vertex, next.edge - 1));
        	}
        }
    }
}