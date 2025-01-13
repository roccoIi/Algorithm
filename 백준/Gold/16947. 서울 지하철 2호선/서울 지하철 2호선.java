import java.io.*;
import java.util.*;

public class Main {
	static int N, dist[], prev[], startPoint;
	static ArrayList<Integer>[] list;
	static boolean[] visited, isCycle;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		isCycle = new boolean[N+1];
		dist = new int[N+1];
		prev = new int[N+1];
		Arrays.fill(dist, -1);
		
		// 연결리스트 초기화
		list = new ArrayList[N+1];
		for(int i = 1; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 지하철 역간 연결정보 리스트에 입력
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int station_1 = Integer.parseInt(st.nextToken());
			int station_2 = Integer.parseInt(st.nextToken());
			list[station_1].add(station_2);
			list[station_2].add(station_1); // 양방향
		}
		
		visited[1] = true;
		findCycle(1);
		countDistance();
		
		for(int i = 1; i <= N; i++) {
			sb.append(dist[i]).append(" ");
		}
		System.out.println(sb);
		
		
		
	}
	
	static boolean findCycle(int now) {
		for(int next : list[now]) {
			// 바로 직전에 탐색한 정점과 동일할경우 패쓰
			if(next == prev[now]) {
				continue;
			}
			
			// 만약 방문했던 정점을 다시 방문했다면 사이클이 생성되었다.
			if(visited[next]) {
				
				// 사이클에 들어가기 직전의 정점을 아래의 while문의 앤드포인트로 잡는다.
				// startPoint는 사이클이 시작되는 그 분기점으로 잡는다. 해당 지점에서 여러갈래로 퍼져나가며
				// 사이클의 거리를 측정할 예정이다.
				int endCycle = prev[next];
				startPoint = next;
				
				while(now != endCycle) {
					isCycle[now] = true;
					now = prev[now];
				}
				return true;
			}
			
			// 여기까지 왔다면 다음 정점을 탐색하러 들어간다.
			// 다음 정점의 이전이 지금임을 표시하고 방문제크 후 dfs
			prev[next] = now;
			visited[next] = true;
			if(findCycle(next)) {
				return true;
			}
		}

		return false;
	}
	
	static void countDistance() {
		Queue<Integer> q = new LinkedList<>();
		q.add(startPoint);
		dist[startPoint] = 0;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			// 다음 탐색할 지역의 거리가 -1이 아니라면 이미 초기화 됐으므로 넘어간다.
			for(int next : list[curr]) {
				if(dist[next] != -1) continue;
				
				// 사이클의 한 지점에서 시작했으므로 한번 사이클을 벗어나면 다시 사이클에 돌아올 일은 없다.
				// 그러므로 초기는 0으로 초기화하되, 사이클이 아닌 곳을 마주한다면 현재 거리에서 +1
				// 현재 거리에 해당 거리를 입력하고 다음으로 넘어간다.
				int nextDist = 0;
				if(!isCycle[next]) {
					nextDist = dist[curr] + 1;
				}
				
				dist[next] = nextDist;
				q.add(next);
			}
		}
	}
}