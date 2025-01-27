import java.io.*;
import java.util.*;

public class Main {
	static class Gem{
		int size, value;
		
		Gem(int size, int value){
			this.size = size;
			this.value = value;
		}
	}
	static int N, K, backpack[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 정렬기준: (1순위) 보석의 무게 오름차순, (2순위) 보석의 가치 내림차순
		PriorityQueue<Gem> pq = new PriorityQueue<>(
				(s1, s2) -> {
					if(s1.size != s2.size) {
						return Integer.compare(s1.size, s2.size);
					}
					else {
						return Integer.compare(s2.value, s1.value);
					}
				});
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// 가방의 크기 종류
		backpack = new int[K];
		for(int i = 0; i < K; i++) { //30만 
			backpack[i] = Integer.parseInt(br.readLine());
		}
		
		// 수용가능한 가방들의 크기는 오름차순으로 정렬해놓는다.
		Arrays.sort(backpack);
		
		long answer = 0;
		PriorityQueue<Gem> temp = new PriorityQueue<>((s1, s2) -> Integer.compare(s2.value, s1.value));
		for(int i = 0; i < K; i++) {
			// 현재 가방 크기에 들어갈 수 있는 모든 보석을 temp 큐에 넣는다.
			while(!pq.isEmpty() && pq.peek().size <= backpack[i]) {
				temp.add(pq.poll());
			}
			
			if(!temp.isEmpty()) answer += temp.poll().value;
		}
		
		System.out.println(answer);
	}
}