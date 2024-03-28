import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static class Island implements Comparable<Island>{
		int end;
		double length;
		
		public Island(int end, double length) {
			this.end = end;
			this.length = length;
		}

		@Override
		public int compareTo(Island o) {
			return Double.compare(this.length, o.length);
		}
	}
	static int[] dist;
	static List<Island>[] adjArr;
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= testCase; T++) {
			int islandCount = Integer.parseInt(br.readLine());
			dist = new int[islandCount];
			visit = new boolean[islandCount];
			adjArr = new ArrayList[islandCount];
			Arrays.fill(dist, INF);
			sb.append("#").append(T).append(" ");
			
			// location 0행: x좌표 / 1행: y좌표
			int[][] location = new int[2][islandCount];
			for(int r = 0; r < 2; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < islandCount; c++) {
					location[r][c] = Integer.parseInt(st.nextToken());
				}
			}
		
			
			double E = Double.parseDouble(br.readLine()); // 환경부담세율
			
			//배열 초기화(add로 넣을라면 리스트가 존재해야함)
 			for(int i = 0; i < islandCount; i++) {
				adjArr[i] = new ArrayList<>();
			}
			
			
			//2개짜리 조합 만들기
			for(int i = 0; i < islandCount-1; i++) {
				for(int j = i+1; j < islandCount; j++) {
					
					double length = Math.pow(location[0][i] - location[0][j],2) + Math.pow(location[1][i] - location[1][j],2);
					adjArr[i].add(new Island(j, length));
					adjArr[j].add(new Island(i, length));
				}
			}
			
			// 프림 알고리즘
			PriorityQueue<Island> pq = new PriorityQueue<>();
			pq.addAll(adjArr[0]); // 0부터 시작
			
			// 시작지점은 방문체크 후 while문 시작
			int check = 1;
			double answer = 0;
			visit[0] = true;
			
			while(check != islandCount) {
				Island island = pq.poll();
				if(visit[island.end]) continue;

				int end = island.end;
				double length = island.length;	
				
				answer += (length * E);
				visit[end] = true;
				check++;
				
				pq.addAll(adjArr[end]);
				
			}
			sb.append(Math.round(answer)).append("\n");
		}
		System.out.println(sb);
	}
}