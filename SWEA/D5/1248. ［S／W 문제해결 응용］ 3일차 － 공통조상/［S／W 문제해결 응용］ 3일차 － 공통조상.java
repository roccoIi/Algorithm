import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node{
	Node up;
	int num;
	Node left;
	Node right;
	
	Node(){}
	
	Node(int num){
		this.num = num;
	}

}

public class Solution {
	static int N, lines, targetNum1, targetNum2, root, sum;
	static List<Integer> list1;
	static  List<Integer> list2;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb; StringTokenizer st;	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());
		for(int i = 1; i <= testCase ; i++) {
			st = new StringTokenizer(br.readLine());
			int child; int parent;
			N = Integer.parseInt(st.nextToken());
			lines = Integer.parseInt(st.nextToken());
			targetNum1 = Integer.parseInt(st.nextToken());
			targetNum2 = Integer.parseInt(st.nextToken());
			list1 = new ArrayList<>();
			list2 = new ArrayList<>();

			
			Node [] nodes = new Node[N+1];
			int[] roots = new int[N+1];
			
			for(int j = 1; j <= N; j++) {
				nodes[j] = new Node(j);
			}
			
			// 자식노드를 입력함과 동시에 부모노드 주소까지 넣어준다.
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= lines; j++) {
				parent = Integer.parseInt(st.nextToken());
				child = Integer.parseInt(st.nextToken());
				roots[child]++;
				if(nodes[parent].left == null) {
					nodes[parent].left = nodes[child];
					nodes[child].up = nodes[parent];
				} else {
					nodes[parent].right = nodes[child];
					nodes[child].up = nodes[parent];

				}
			}

			// 각 타겟의 조상노드들을 리스트에 저장 후 비교하면서 가장 먼저 일치하는 노드 출력 (내림차순으로 리스트에 저장되어있음)
			addList(nodes[targetNum1], list1);
			addList(nodes[targetNum2], list2);
			int a = -1;
			end:for(int r = 0; r < list1.size(); r++) {
				for(int c = 0; c < list2.size(); c++) {
					if(list1.get(r) - list2.get(c) == 0) {
						a = list1.get(r);
						break end;
					}
				}
			}
			
			// 서브트리 합
			sum = 0;
			count(nodes[a]);
			
			//출력
			sb = new StringBuilder();
			sb.append("#").append(i).append(" ").append(a).append(" ").append(sum);
			System.out.println(sb);
		}
	}
	
	static void addList(Node node, List<Integer> list) {

		if(node == null) return; // 루트노드 찾을때까지
		
		list.add(node.num); // 루트노드가 아니라면 리스트에 해당 num 추가
		addList(node.up, list); // 부모노드 따라 올라온다.
		
	}
	
	static void count(Node node) {
		
		if(node == null) return;
		
		count(node.left);
		sum++;
		count(node.right);
		
	}
}