import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 1. 최대 100번까지 있으므로 1 ~ 100 까지의 인접리스트 만들어서 숫나 넣어놓는다.
	 * 2. bfs로 자식노드 찾는다. 연락과정 한 사이클이 동시에 일어나므로 q.size 만큼만 진행한다.
	 * 3. 한번 다 돌았을 때 큐가 채워져있다면 자식노드가 있다는 뜻이므로 임시 리스트(tmp) 초기화한다.
	 * 4. 한번 다 돌았을 때 큐가 비워져있다면 자식노드 없는 애들만 임시리스트(tmp)에 모여있다.
	 * 5. tmp에 모인 아이들 중 최대값 찾아서 출력한다.
	 * */
	
	static List<Integer>[] list;
	static ArrayList<Integer> tmp;
	static boolean[] check;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = 10;
		
		for(int T = 1; T <= testCase; T++) {
			sb.append("#").append(T).append(" ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int maxNum = Integer.MIN_VALUE;
			check = new boolean[101];
			tmp = new ArrayList<>();
			list = new ArrayList[101];
			
			for(int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list[from].add(to);
			}
			
			bfs(start);
			// 더이상 자식노드가 없던 애들 중 최대값 찾아서 출력
			for(int i = 0; i < tmp.size(); i++) {
				maxNum = Math.max(tmp.get(i), maxNum);
			}
			sb.append(maxNum).append("\n");
		}
		
		System.out.println(sb);	
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		check[start] = true;
		
		while(!q.isEmpty()) {
			int size = q.size(); // 큐의 크기만큼만 반복 (같은 순서)
			
			for(int i = 0; i < size; i++) {
				int num = q.poll();
				tmp.add(num);
				for(int j = 0; j < list[num].size(); j++) {
					int target = list[num].get(j);
					if(!check[target]) {
						check[target] = true;
						q.offer(target);
						tmp.add(target);
					}
				}
			}
			// 큐가 비어있지 않다면 아직 탐색할 노드가 남아있다.
			if(!q.isEmpty()) tmp = new ArrayList<>();
		}
	}
}