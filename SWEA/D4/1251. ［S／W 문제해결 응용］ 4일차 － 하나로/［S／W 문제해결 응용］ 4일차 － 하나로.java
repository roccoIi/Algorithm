import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * class [Island]
 * 	=> 파라미터 x,y일때와 x,y,value일때의 역할이 좀 다르다.
 * 	=> x,y일때는 섬의 x좌표와 y좌표를 담는다.
 *  => x,y,value 일때는 첫번째 섬과 두번째 섬, 두 섬 사이의 거리를 담는다. (클래스 재사용할라고 그냥 썼다)
 * 
 * [회고]
 *  1. 간선들이 주어지지 않아 배워먹은걸 쓸 수 있을지가 의문이였다. 간선이 주어졌을대 정렬해서 알고리즘 돌리는 것만 배웠기 때문이다.
 *  2. 간선이 주어지지 않는다면 모든 간선을 구하는 수밖에 없다. 조합을 통해 모든 간선들을 구하고 이걸 클래스로 정의해 totalArr에 저장했다.
 *  3. 섬이 3개 있다면 순서대로 0, 1, 2 번호를 부여했다고 가정하고 (0번 섬,1번 섬),(0번 섬,2번 섬),(1번 섬,2번 섬) 식으로 모든 조합을 구하고 거리도 구했다.
 *  4. 모든 간선을 구한 후에는 크루스칼 알고리즘을 사용하여 최소비용을 구했다.
 * 
 * */

class Island implements Comparable<Island>{
	int x;
	int y;
	double value;
	
	public Island(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Island(int x, int y, double value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	@Override
	public int compareTo(Island o) {
		double num = this.value - o.value;
		if(num < 0) {
			return -1;
		} else if(num > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}

public class Solution {
	static Island[] islandArr, totalArr;
	static int[] p, tmp;
	static int total, islandCount, totalIndex;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; StringBuilder sb = new StringBuilder();
		
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= testCase; T++) {
			sb.append("#").append(T).append(" ");
			islandCount = Integer.parseInt(br.readLine()); // 총 섬의 개수
			total = (islandCount * (islandCount - 1))/2; // 섬의 개수로 만들어 질 수 있는 간선의 수
			totalArr = new Island[total]; // 섬 개수별로 모든 조합을 구할 생각이다. 그 조합들을 저장해놓을 배열이다.
			totalIndex = 0; // 그리고 그 배열들의 인덱스를 관리할 변수이다.
			tmp = new int[2]; // 섬은 2개끼리만 비교한다. 그 2개의 섬 번호들 조합 임시로 저장해놓을 배열
			p = new int[islandCount]; // 크루스칼 알고리즘을 위한 p, 최소비용 구할때 사용할꺼다
			islandArr = new Island[islandCount]; // 섬의 x좌표, y좌표 저장한 객체를 저장할 배열이다. 파라미터 2개짜리다.
			
			
			// 주어진 x좌표 y좌표 입력받고 각각 배열에 넣는다.
			int[] xPoint = new int[islandCount];
			int[] yPoint = new int[islandCount];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < islandCount; i++) {
				xPoint[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < islandCount; i++) {
				yPoint[i] = Integer.parseInt(st.nextToken());
			}
			// 환경부담세율 E 입력받는다.
			double E = Double.parseDouble(br.readLine());
			
			//makeSet()
			for(int i = 0; i < islandCount; i++) {
				p[i] = i;
				islandArr[i] = new Island(xPoint[i], yPoint[i]);
			}
			// 섬의 개수만큼 조합 진행한다.
			combination(0,0);
			
			// 조합 메소드에서 채워넣은 섬들간 모든 조합들의 배열이다. 거리 순으로 정렬한다.
			Arrays.sort(totalArr);
			
			
			// 최소신장트리_크루스칼 알고리즘
			int line = 0; // 노드-1(V-1) 만큼 진행되면 종료조건
			int idx = 0;
			double answer = 0; // 정답 저장
			while(line != islandCount-1) {
				int x = totalArr[idx].x;
				int y = totalArr[idx].y;
				
				if(findSet(x) != findSet(y)) {
					union(x, y);
					answer += (Math.pow(totalArr[idx].value,2) * E);
					line++;
					idx++;
				} else {
					idx++;
				}
			}
			sb.append(Math.round(answer)).append("\n");
		}
		System.out.println(sb);
	}
	
	static void combination(int idx, int sidx) {
		// 섬 2개끼리만 비교할꺼다.
		if(sidx == 2) {
			// x좌표끼리 같으면 y가 거리, y좌표끼리 같으면 x가 거리, 둘다 다르면 피타고라스가 거리다.
			double num = 0;
			if(islandArr[tmp[0]].x == islandArr[tmp[1]].x) {
				num = Math.abs(islandArr[tmp[0]].y - islandArr[tmp[1]].y);
			} else if(islandArr[tmp[0]].y == islandArr[tmp[1]].y) {
				num = Math.abs(islandArr[tmp[0]].x - islandArr[tmp[1]].x);
			} else {
				num = islandLength(islandArr[tmp[0]].x, islandArr[tmp[1]].x, islandArr[tmp[0]].y, islandArr[tmp[1]].y);				
			}
			// 구한 거리를 모든 섬 조합 배열에 넣는다. 파라미터 3개짜리다.(섬1, 섬2, 거리)
			totalArr[totalIndex++] = new Island(tmp[0], tmp[1], num);
			return;
		}
		
		for(int i = idx; i <= islandCount-2+sidx; i++) {
			tmp[sidx] = i;
			combination(i+1, sidx+1);
		}
	}
	
	// 피타고라스
	static double islandLength(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(Math.abs(x1-x2),2) +  Math.pow(Math.abs(y1-y2),2));
	}
	
	// 최소신장트리_크루스칼 알고리즘
	static void union(int x, int y) {
		p[findSet(y)] = p[findSet(x)];
	}
	
	// 최소신장트리_크루스칼 알고리즘
	static int findSet(int x) {
		if(x != p[x]) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
}