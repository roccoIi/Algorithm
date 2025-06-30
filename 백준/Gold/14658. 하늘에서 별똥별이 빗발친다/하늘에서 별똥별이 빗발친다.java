import java.util.*;
import java.io.*;

public class Main {	
	static int N, M, L, K, maxCnt;
	static Node[] nodes;
	static int[][] dirs = {{1, 1, -1, -1}, {1, -1, 1, -1}};
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		nodes = new Node[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(r, c);
		}
		
		for(Node nodeR : nodes) {
			for(Node nodeC : nodes) {
				int nr = nodeR.r;
				int nc = nodeC.c;
				int count = 0;
				
				for(Node node : nodes) {
					if(checkBoundary(nr, nc, node)) count++; 
				}
				maxCnt = Math.max(maxCnt, count);
			}
		}
		
		System.out.println(K - maxCnt);
	}
	
	static boolean checkBoundary(int r, int c, Node node) {
		return node.r >= r && node.r <= r + L && node.c >= c && node.c <= c + L;
	}
}