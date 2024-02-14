import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int num;
	String data;
	Node left;
	Node right;
	
	Node(){}
	
	Node(int num, String data){
		this.num = num;
		this.data = data;
	}
}

public class Solution {
	
	static Node[] nodes;
	static int[] roots;
	static StringBuilder sb ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int lChild;
		int rChild;
		for(int t = 1; t <= 10; t++) {
			sb = new StringBuilder();
			
			int N = Integer.parseInt(br.readLine());
			nodes = new Node[N+1];
			roots = new int[N+1];
			
			for(int i = 1; i <= N; i++) {
				nodes[i] = new Node();
			}
			
			
			for(int i = 1; i <= N; i++) {
				String str = br.readLine();
				StringTokenizer st = new StringTokenizer(str);
				
				nodes[i].num = Integer.parseInt(st.nextToken());
				nodes[i].data = st.nextToken();
				
				while(st.hasMoreElements()) {
					if(nodes[i].left == null) {
						lChild = Integer.parseInt(st.nextToken());
						nodes[i].left = nodes[lChild];
						roots[lChild]++;
					} else {
						rChild = Integer.parseInt(st.nextToken());
						nodes[i].right = nodes[rChild];
						roots[rChild]++;
					}
				}		
			}
			int root = -1;
			for(int i = 1; i <= N; i++) {
				if(roots[i] == 0) root = i;
			}
			
			sb.append("#").append(t).append(" ");
			inOrder(nodes[root]);
			System.out.println(sb);
		}
	}
	
	static void inOrder(Node node) {
		if(node == null) return;
		
		inOrder(node.left);
		sb.append(node.data);
		inOrder(node.right);
	}
}