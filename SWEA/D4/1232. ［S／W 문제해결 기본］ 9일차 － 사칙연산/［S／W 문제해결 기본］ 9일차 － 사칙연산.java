import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	String object;
	Node left;
	Node right;
	
	Node(){}
	
	Node(String object){
		this.object = object;
	}
}
public class Solution {
	static int answer;

	public static void main(String[] args) throws IOException {
		StringBuilder sb; StringTokenizer st;	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int t = 1; t <= 10; t++) {
			sb = new StringBuilder();
			int cases = Integer.parseInt(br.readLine());
			int[] roots = new int[cases+1];
			Node[] nodes = new Node[cases+1];
			int lNumber;
			int rNumber;
			
			for(int i = 1; i <= cases; i++) {
				nodes[i] = new Node();
			}
			
			for(int i = 1; i <= cases; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				
				// 토큰이 2개 이상이라면 Node.object에 연산자 입력 후 왼쪽 오른쪽 입력, 아니라면 object에 숫자)
				if(st.countTokens() > 1) {
					nodes[i].object = st.nextToken();
					while(st.hasMoreTokens()) {
						if(nodes[i].left == null) {
							lNumber = Integer.parseInt(st.nextToken());
							nodes[i].left = nodes[lNumber];
							roots[lNumber]++;
						} else {
							rNumber = Integer.parseInt(st.nextToken());
							nodes[i].right = nodes[rNumber];
							roots[rNumber]++;
						}
					}
				} else {
					nodes[i].object = st.nextToken();
				}
			}
			
			// 루트노드 찾기
			int root = -1;
			for(int i = 1; i <= cases; i++) {
				if(roots[i] == 0) root = i;
			}
			answer = 0;
			
			sb.append("#").append(t).append(" ").append(inorder(nodes[root]));
			System.out.println(sb);

		}
	}
	static int inorder(Node node) {
		// node의 오른쪽이 비었다는 것은 자식 노드가 없다는 것 ( 해당 object 숫자로 변환하여 반환)
		if(node.right == null) return Integer.parseInt(node.object);
		
		if(node.object.equals("+")) {
			answer = inorder(node.left) + inorder(node.right);
		} else if(node.object.equals("-")) {
			answer = inorder(node.left) - inorder(node.right);
		} else if(node.object.equals("*")) {
			answer = inorder(node.left) * inorder(node.right);
		} else if(node.object.equals("/")) {
			answer = inorder(node.left) / inorder(node.right);
		}
		
		return answer;
	}
}