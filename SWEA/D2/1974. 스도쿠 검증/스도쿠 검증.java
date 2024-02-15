import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; StringBuilder sb;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= testCase; t++) {
			int[][] arr = new int[9][9];
			boolean answer = true;
			
			// 전체 스도쿠 배열 입력
			for(int r = 0; r < 9; r++){
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < 9; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) { // 가로탐색, 한줄씩 우선큐에 입력
					pq.offer(arr[r][c]);
				}
				for(int c = 1; c <= 9; c++) { // 하나씩 출력하면서 1씩 증가하는 값이 큐에서 출력되는 값과 동일한지 확인
					if(pq.poll() != c) {
						answer = false; // 다르다면 즉시 false
					}
				}
				for(int c = 0; c < 9; c++) { // 세로탐색
					pq.offer(arr[c][r]);
				}
				for(int c = 1; c <= 9; c++) {
					if(pq.poll() != c) {
						answer = false;
					}
				}
			}
		
			// 3 X 3 탐색
			for(int r = 1; r < 9; r+=3) {
				for(int c = 1; c < 9; c+=3) {
					pq.add(arr[r][c]);
					for(int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						pq.add(arr[nr][nc]);
					}
					for(int i = 1; i <= 9; i++) {
						if(pq.poll() != i) {
							answer = false;
						}
					}
				}
			}
			
			if(answer) {
				System.out.printf("#%d %d\n", t, 1);
			} else {
				System.out.printf("#%d %d\n", t, 0);
			}
		}		
	}
}