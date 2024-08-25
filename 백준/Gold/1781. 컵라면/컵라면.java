import java.util.*;
import java.io.*;


public class Main {
	static class Node{
		int time, value;
		
		Node(int time, int value){
			this.time = time;
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int N = Integer.parseInt(br.readLine());
		//Input을 받을 pq이다. (우선순위: 1. 시간(오른차순) 2. 컵라면(내림차순))
		PriorityQueue<Node> startQ = new PriorityQueue<>(
				(s1, s2) -> {
					if(s1.time != s2.time) {
						return Integer.compare(s1.time, s2.time);
					} else {
						return Integer.compare(s2.value, s1.value);
					}
				});
		
		// 정답 배열을 저장할 pq이다. (우선순위: 1.컵라면(내림차순))
		PriorityQueue<Node> q = new PriorityQueue<>(
				(s1, s2) -> Integer.compare(s1.value, s2.value));
		
		int ans = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			startQ.add(new Node(time, value));
		}
		
		// q의 사이즈 : 현재 흐른 시간
		// input의 데드라인이 현재 시간보다 작거나 같다면 가장 작은 가치 값과 비교해서
		// 가치가 더 큰쪽을 q에 넣는다.
		// input의 데드라인이 더 크다면 그냥 q에 넣는다.
		while(!startQ.isEmpty()) {
			Node node = startQ.poll();
			if(node.time <= q.size()) {
				if(q.peek().value < node.value) {
					q.remove();
					q.add(node);
				}
			} else {
				q.add(node);
			}
		}
		
		while(!q.isEmpty()) {
			ans += q.poll().value;
		}
		
		System.out.println(ans);
		
	}
}