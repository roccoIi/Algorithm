import java.io.*;
import java.util.*;

public class Main {
	static int totalCityCnt, travelCityCnt, parents[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		totalCityCnt = Integer.parseInt(br.readLine());
		travelCityCnt = Integer.parseInt(br.readLine());
		parents = new int[totalCityCnt+1];
		
		
		// 부모노드 배열 초기화
		for(int i = 1; i <= totalCityCnt; i++) {
			parents[i] = i;
		}
		// 연결정보를 받고 만약에 연결되어있다면(1) 두 노드를 잇는다. => unionSet
		// r을 기준으로 연결되어있는 c를 확인하기때문에 숫자가 낮은 노드가 부모노드가 될 것이다.
		// 문제조건 : 도시의 번호는 1부터 N까지 차례대로 매겨져 있다.
		for(int r = 1; r <= totalCityCnt; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= totalCityCnt; c++) {
				int point = Integer.parseInt(st.nextToken());
				if(point == 1) {
					unionSet(r, c);
				}
			}
		}
		// 첫번째 목적지를 start 로 잡고,
		// 이어지는 목적지들이 해당 start와 같은 그룹에 속하는지 확인
		// 부모 노드가 다른 노드가 있다면 NO 반환, 이외는 YES반환을 위해 continue;
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		for(int i = 1; i < travelCityCnt; i++) {
			int destination = Integer.parseInt(st.nextToken());
			if(findSet(start) != findSet(destination)) {
				sb.append("NO");
				break;
			}
		}
		if(sb.length() == 0) sb.append("YES");
		System.out.println(sb);
	}
	
	// 해당 도시가 속한 그룹의 부모를 찾는다.
	static int findSet(int x) {
		if(x == parents[x]) return x;
		else return parents[x] = findSet(parents[x]);
	}
	
	// 두 노드의 부모를 찾고 합친다.
	// 경로압축을 통해 바로 부모노드를 찾을 수 있으므로 굳이 랭크비교는 하지 않았다.
	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		parents[y] = x;
	}
}