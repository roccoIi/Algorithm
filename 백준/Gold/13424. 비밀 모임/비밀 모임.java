import java.io.*;
import java.util.*;

public class Main {
	static int room, path;
	static List<Node>[] map;
	final static int INF = 987654321;
	
	static class Node{
		int point, distance;
		
		Node(int point, int distance){
			this.point = point;
			this.distance = distance;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 0; T < testCase; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			room = Integer.parseInt(st.nextToken());
			path = Integer.parseInt(st.nextToken());
			map = new ArrayList[room+1];
						
			for(int i = 0; i <= room; i++) {
				map[i] = new ArrayList<>();
			}
			
			// 양방향 입력
			for(int i = 0; i < path; i++) {
				st = new StringTokenizer(br.readLine());
				int roomA = Integer.parseInt(st.nextToken());
				int roomB = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());
				
				map[roomA].add(new Node(roomB, distance));
				map[roomB].add(new Node(roomA, distance));
			}
			
			// 각 친구들의 지점별 최단거리 저장할 배열 생성
			int friendsCnt = Integer.parseInt(br.readLine());
			int[][] friends = new int[friendsCnt][room+1];
			
			// 다익스트라 알고리즘을 통해 해당 친구들의 지점별 최단거리 저장
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < friendsCnt; i++) {
				friends[i] = dijkstra(Integer.parseInt(st.nextToken()));
			}
			
			// 저장된 인원별, 지점별 최단거리 배열을 기준으로
			// 모든 친구들의 지점별 거리가 가장 적은 지점을 탐색한다.
			// 단, 거리가 같을 경우 지점 번호가 낮은것을 출력한다.
			int minDist = Integer.MAX_VALUE;
			int answer = 0;
			for(int i = 1; i <= room; i++) {
				int tmp = 0;
				for(int j = 0; j < friendsCnt; j++) {
					tmp += friends[j][i];
				}
				
				// 최소거리 찾기 + 같을경우 낮은 번호 우선
				if(minDist > tmp) {
					minDist = tmp;
					answer = i;
				} else if(minDist == tmp && i < answer) {
					answer = i;
				}
			}
			
			sb.append(answer).append('\n');

		}
		System.out.println(sb);
	}
	
	static int[] dijkstra(int start) {
		// 거리가 짧은걸 우선으로 갱신하기 위해 거리기준 오름차순 정렬
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> s1.distance - s2.distance);
		boolean[] visited = new boolean[room+1];
		int[] dist = new int[room+1];
		Arrays.fill(dist, INF);
		
		pq.add(new Node(start, 0));
		dist[start]= 0;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			// 방문한 지점일 경우 패쓰, 미방문일 경우 방문체크 후 진행
			if(visited[curr.point]) continue;
			visited[curr.point] = true;

			for(Node next : map[curr.point]) {
				// [거리 갱신 조건]
				// 1) 지금 방문 하려는 노드가 방문이력이 없을 것!
				// 2) 현재 방문하려는 노드까지 저장된 거리가 내 현재 위치에서 해당 노드까지 가려는 거리의 합보다 클 것! 
				if(!visited[next.point] && dist[next.point] > dist[curr.point] + next.distance) {
					dist[next.point] = dist[curr.point] + next.distance;
					pq.add(new Node(next.point, dist[next.point]));
				}
			}
		}
		
		return dist;
	}
}