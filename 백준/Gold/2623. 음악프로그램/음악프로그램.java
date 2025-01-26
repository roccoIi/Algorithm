import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] inDegree = new int[N+1];
		int totalCount = 0;
		
		// 리스트 배열 초기화
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 주어진 입력을 받고, 관계리스트와 진입차수 배열을 작성한다.
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int now = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num - 1; j++) {
				int next = Integer.parseInt(st.nextToken());
				list[now].add(next);
				inDegree[next]++;
				now = next;
			}
		}
		
		// 진입차수 0인 가수번호 탐색
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) q.add(i);
		}
		
		// q가 모두 소진될때까지 탐색한다.
		while(!q.isEmpty()) {
			int curr = q.poll();
			sb.append(curr).append('\n');
			totalCount++;
			
			for(int num : list[curr]) {
				if(--inDegree[num] == 0) q.add(num);
			}
		}
		
		// 순서를 정한 가수가 총 가수에 미치지 못할 경우에는 0을 반환한다.
		if(totalCount != N) {
			sb = new StringBuilder();
			sb.append(0);
		}
		
		System.out.println(sb);
	}
}