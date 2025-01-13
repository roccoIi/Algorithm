import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int now, last;
		
		Node(int now, int last){
			this.last = last;
			this.now = now;
		}
	}
	static class Station{
		int number, dist;
		
		Station(int number, int dist){
			this.number = number;
			this.dist = dist;
		}
	}
	final static int INF = 987654321;
	static int N, circulateGraph[];
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		circulateGraph = new int[N+1];
		Arrays.fill(circulateGraph, INF);
		
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
		
		// 순환노선 전처리 진행 (set에 지금까지 지나온 역을 저장하고, 순환이 확인될 경우 해당 역들 체크)
		for(int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			HashSet<Integer> set = new HashSet<>();
			set.add(i);
			findCirculate(i, i, 0, set, false);
		}
		
		// 순환노선이 아닌 역들만 거리계산 진행
		for(int i = 1; i <= N; i++) {
			if(circulateGraph[i] == INF) caculateDistance(new Station(i, 0));
		}
		
		// 전체 정답을 출력한다.
		for(int i = 1; i <= N; i++) {
			sb.append(circulateGraph[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	// 현재 지하철 역에서 순환노선까지의 거리 측정 (BFS 구현)
	static void caculateDistance(Station start) {
		Queue<Station> q = new LinkedList<>();
		boolean[] visit = new boolean[N+1];
		visit[start.number] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			Station curr = q.poll();
			
			for(int num : list[curr.number]) {
				if(visit[num]) continue;
				
				if(circulateGraph[num] == 0) {
					circulateGraph[start.number] = circulateGraph[start.number] > curr.dist + 1 ? curr.dist + 1 : circulateGraph[curr.number];
					return;
				} else {
					visit[num] = true;
					q.add(new Station(num, curr.dist + 1));
				}
			}
		}
	}
	
	// 순환노선 찾는 전처리 과정 (DFS 구현)
	static void findCirculate(int start, int now, int last, HashSet<Integer> set, boolean check) {
		// 이미 싸이클 찾았으면 종료
		if(check) return;
		
		for(int num : list[now]){
			
			// 바로 직전과 동일하거나 이미 방문했다면 패쓰
            if(num == last || visited[num]) continue;
            
			// 진입지점과 동일한 숫자라면 사이클이 발생했음을 의미한다.
			if(num == start) {			
				set.forEach(number -> circulateGraph[number] = 0);
				check = true;
				return;				
			}

			// 위에서 종료되지 않았다면 다음 역으로 진행한다.
			visited[num] = true;
			set.add(num);
			findCirculate(start, num, now, set, false); // 시작, 현재, 과거, set, boolean
			set.remove(num);
			
			// 만약 순환노선을 찾고 돌아왔다면 방문해제를 하지 않는다.
			if(circulateGraph[num] != 0) visited[num] = false;
		}
	}
}