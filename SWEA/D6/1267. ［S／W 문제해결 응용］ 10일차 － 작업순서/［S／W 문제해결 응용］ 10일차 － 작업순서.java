import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int T = 1; T <= 10; T++) {
			sb.append("#").append(T);
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int[] degree = new int[V + 1]; // 노드당 진입차수 개수

			// 인접 리스트 생성
			List<Integer>[] list = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}

			// 주어진 배열 인접리스트에 받는다.
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				list[A].add(B);
				degree[B]++;
			}
			
			
			// 진입차수가 없는 노드를 찾아 큐에 넣는다.
			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i <= V; i++) {
				if (degree[i] == 0) q.offer(i);
			}
			
			// 큐가 빌때까지 찾는다.
			while (!q.isEmpty()) {
				int num = q.poll();
				sb.append(" ").append(num);

				for (int i = 0; i < list[num].size(); i++) {
					int target = list[num].get(i);
					degree[target]--;
					if (degree[target] == 0) q.offer(target);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}