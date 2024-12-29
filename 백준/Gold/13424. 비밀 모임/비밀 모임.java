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
			
			for(int i = 0; i < path; i++) {
				st = new StringTokenizer(br.readLine());
				int roomA = Integer.parseInt(st.nextToken());
				int roomB = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());
				
				map[roomA].add(new Node(roomB, distance));
				map[roomB].add(new Node(roomA, distance));
			}
			
			int friendsCnt = Integer.parseInt(br.readLine());
			int[][] friends = new int[friendsCnt][room+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < friendsCnt; i++) {
				friends[i] = dijkstra(Integer.parseInt(st.nextToken()));
			}
			
			int minDist = Integer.MAX_VALUE;
			int answer = 0;
			for(int i = 1; i <= room; i++) {
				int tmp = 0;
				for(int j = 0; j < friendsCnt; j++) {
					tmp += friends[j][i];
				}
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