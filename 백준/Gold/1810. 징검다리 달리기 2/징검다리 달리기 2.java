import java.io.*;
import java.util.*;
/**
 * [기억할것]
 *  1. 다익스트라는 거리가 짧은 순서대로 탐색하게 된다.
 *  
 * [구현 방법]
 *  1. map을 통해 각 정점들의 임의 인덱스 번호를 부여한다. (map은 key-value, value-key 둘다 탐색가능)
 *  2. 각 정점들은 Node로 관리?
 */
public class Main {
	static class Node{
		String point;
		double distance;
		
		Node(String point, double distance){
			this.point = point;
			this.distance = distance;
		}
	}
	final static double INF = 987654321.0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		
		// testCase 입력값
		// map의 Key를 Node 객체로 가져가면 새로운 Node객체를 생성할 때 주소가 달라 제대로 찾지 못한다.
		// (만약 객체로 진행하려면 equals()와 hashCode()를 오버라이드 하여 Map의 키로 사용 가능하도록 해야한다.)
		HashMap<String, Integer> map = new HashMap<>();
		HashSet<String> goal = new HashSet<>();
		map.put("0 0", 0);
		for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			map.put(str, i);
			
			String[] tmp = str.split(" ");
			if(Integer.parseInt(tmp[1]) == F) goal.add(str);
		}
				
		// 거리배열 초기화
		double[] dist = new double[N+1];
		Arrays.fill(dist, INF);
		dist[0] = 0;
		
		// 다익스트라 세팅
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Double.compare(s1.distance, s2.distance));
		boolean[] visited = new boolean[N+1];
		pq.add(new Node("0 0", 0));
				
		// 다익스트라 진행
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[map.get(curr.point)]) continue;
			visited[map.get(curr.point)] = true;
			
			String[] str = curr.point.split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
						
			for(int X = -2; X <= 2; X++) {
				for(int Y = -2; Y <= 2; Y++) {
					if(X == 0 && Y == 0) continue;
					int nx = x + X;
					int ny = y + Y;
					
					if(!checkBoundary(nx, ny)) continue;
					
					String newPoint = nx + " " + ny;
					if(map.containsKey(newPoint)) {
						int index = map.get(newPoint);
						double gap = distanceGap(x, y, nx, ny);
						if(!visited[index] && dist[index] > dist[map.get(curr.point)] + gap) {
							dist[index] = dist[map.get(curr.point)] + gap;
							pq.add(new Node(newPoint, dist[index]));
						}
					}
				}
			}
		}
		
		double minDistance = INF;
		for(String str : goal) {
			minDistance = minDistance > dist[map.get(str)] ? dist[map.get(str)] : minDistance;
		}
		
		if(minDistance == INF) {
			System.out.println("-1");
		} else {
			System.out.println(Math.round(minDistance));
		}
	}
	
	static boolean checkBoundary(int x, int y) {
		return x >= 0 && x <= 1000000 && y >= 0 && y <= 1000000;
	}

	static double distanceGap(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	}
}