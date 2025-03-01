import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int idx, num;
		
		Node(int idx, int num){
			this.idx = idx;
			this.num = num;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
        	int N = Integer.parseInt(br.readLine());
        	
        	PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s2.num, s1.num));
        	int[][] arr = new int[2][N];
        	for(int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		int numA = Integer.parseInt(st.nextToken());
        		int numB = Integer.parseInt(st.nextToken());
        		arr[0][i] = numA;
        		arr[1][i] = numB;
        		pq.add(new Node(i, numA + numB));
        	}
        	
        	int start = 1;
        	int scoreA = 0;
        	int scoreB = 0;
        	while(!pq.isEmpty()) {
        		Node curr = pq.poll();
        		if(start > 0) {
        			scoreA += curr.num - arr[1][curr.idx];
        		} else {
        			scoreB += curr.num - arr[0][curr.idx];
        		}
        		start *= -1;
        	}
        	
        	sb.append(scoreA - scoreB).append('\n');
        }
        System.out.println(sb);
    }
    
}