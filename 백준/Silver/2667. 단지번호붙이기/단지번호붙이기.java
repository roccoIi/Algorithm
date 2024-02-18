import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	/*
	[진행 방식]
	1. 지도를 배열에 입력후 BFS로 진행한다.
	----
	(bfs)
	2. 이중 for문을 통해 지도를 완전 탐색하며 아파트가 있는 위치를 찾는다. 
	3. 사방탐색을 통해 주변에 아파트가 있는지 확인 한 후 큐에 저장, 큐가 비었을때 아파트 단지가 하나 확인된다.
	4. 여기서 확인한 아파트는 지도에서 0으로 변환한다.
	5. 단지가 하나 생성될때마다 지금까지 체크한 아파트를 list에 넣는다.
	----
	6. 리스트 정렬 후 단지수와 함께 출력
	
	 */
	
	static int[][] map;
	static int[] dr = {-1, 0, 0, 1}; static int[] dc = {0, -1, 1, 0};
	static List<Integer> list;
	static int N, nr, nc, count, aptNum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		list = new ArrayList<>(); // 단지당 아파트 수 오름차순 출력을 위한 list
		count = 0; // 총 단지수를 저장할 변수
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// 지도 생성
		for(int r = 0; r < N; r++) {
			String str = br.readLine();
			for(int c = 0; c < N; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		
		bfs();
		Collections.sort(list);
		sb.append(count);
		for(int i = 0; i < list.size(); i++) {
			sb.append("\n").append(list.get(i));
		}
		System.out.println(sb);
		
		
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>(); // bfs 진행을 위한 큐 생성
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == 1) { // map을 돌아다니며 1(아파트)가 있을때 탐색 시작
					map[r][c] = 0; // 찾은 아파트는 0으로 바꾸어 방문을 기록한다.
					aptNum = 1; // 아파트 하나 +1
					q.offer(new int[] {r, c}); // 큐에 좌표를 저장해둔다.
					
					while(!q.isEmpty()) { // 큐가 빌때까지 진행
						int[] number = q.poll(); // 좌표를 꺼낸다.
						int a = number[0];
						int b = number[1];
						for(int d = 0; d < 4; d++) { // 사방탐색 진행
							nr = a + dr[d];
							nc = b + dc[d];
							
							if(check(nr,nc) && map[nr][nc] == 1) { // 이동할 위치가 경게를 벗어나지 않고 아파트가 위치할때 큐에 좌표 저장
								q.offer(new int[] {nr, nc});
								map[nr][nc] = 0; // 찾은 위치는 0으로 변환
								aptNum++; // 아파트 +1
							}
						}
					}
					count++; // 큐가 비었다는 것은 사방에 더이상 아파트가 없다는 뜻이니 단지 +1
					list.add(aptNum); // 지금까지 찾은 아파트 리스트에 저장
				}
			}
		}
	}
	
	public static boolean check(int nr, int nc) { // 경계값 확인
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}