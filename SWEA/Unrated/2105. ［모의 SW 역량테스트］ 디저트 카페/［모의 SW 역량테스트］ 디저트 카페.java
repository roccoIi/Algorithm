import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, rPoint, cPoint, maxEat; //N: 지도 크기, rPoint/cPoint: 기존 r, c좌표, maxEat: 최대방문가게 수
	static int[][] map;
	static boolean[] visit;
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {-1, 1, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[101]; // 각 인덱스 번호의 가게를 방문 했는가 하지 않았는가 확인
			maxEat = -1;
			
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					rPoint = r;
					cPoint = c;
					findRoot(r, c, 0, 0);				
				}
			}			
			sb.append(maxEat).append("\n");
		}// testCase 종료
		System.out.println(sb);
	}// main 함수 종료
	
	static void findRoot(int r, int c, int dir, int num) {
		
		if(rPoint == r && cPoint == c && dir == 3) {
			maxEat = Math.max(maxEat, num);
		}
		
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		// 직진!
		if(check(nr, nc) && !visit[map[nr][nc]]) {
			visit[map[nr][nc]] = true;
			findRoot(nr, nc, dir, num+1);
			visit[map[nr][nc]] = false;
		}
		
		// 꺽는다!
		if(dir < 3) {
			nr = r + dr[dir+1];
			nc = c + dc[dir+1];
			if(check(nr, nc) && !visit[map[nr][nc]]) {
				visit[map[nr][nc]] = true;
				findRoot(nr, nc, dir+1, num+1);
				visit[map[nr][nc]] = false;
			}
		}
		
	}
	
	
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}