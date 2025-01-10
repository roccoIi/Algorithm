import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1]; // 진입차수 저장배열
		
		// 우선순위 저장 리스트 초기화
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 각 리스트에는 해당 인덱스 번호의 문제를 풀었을 때 풀 수 있는 문제들을 가지고 있다.
		// ex) 4번 문제는 3번 문제보다 먼저 푼다. -> list[4].add(3);
		// 그와 동시에 진입차수를 +1 한다.
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			list[num].add(next);
			arr[next]++;
		}
		
		// 난이도가 낮은 문제를 우선으로 풀어야함으로 우선순위 큐를 사용해 오름차순 정렬
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			if(arr[i] == 0) q.add(i);
		}
		
		// 먼저 풀어야하는 문제가 없는 문제들을 큐에 넣고 하나씩 빼면서
		// 해당 문제를 풀었을 때 풀 수 있는 문제들을 큐에 넣어놓고, 큐가 빌때까지 반복
		while(!q.isEmpty()) {
			int curr = q.poll();
			sb.append(curr).append(" ");
			
			for(int i = 0; i < list[curr].size(); i++) {
				int num = list[curr].get(i);
				if(--arr[num] == 0) q.add(num);
			}
		}
		
		// 정답 출력 
		System.out.println(sb);
	}
}