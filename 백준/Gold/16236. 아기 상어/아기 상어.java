import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * [회고]
	 * 
	 * 1. 물고기 탐색은 bfs를 사용해서 생각보다 쉽게 구현할 수 있었다.
	 * 2. 조건에 맞는 물고리를 뽑아내는 것에 시간이 오래 결렀다. 단순히 델타배열로 조건 맞춰서 탐색하면
	 * 	  거리 계산해서 알아서 적당한 위치에 넣어져있을 것이라 생각한것이 문제였다.
	 * 3. fish 클래스에 정렬조건을 다중으로 넣어서 (1순위)거리, (2순위) 상하, (3순위) 좌우로 우선 정렬하게 설정했다.
	 * 4. 클래스의 다중정렬를 연습할 수 있는 기회였는가보다
	 * 
	 * */
	static class Fish implements Comparable<Fish> {
		int r, c, distance, timeCount;
		
		public Fish(int r, int c, int timeCount, int distance) {
			this.r = r;
			this.c = c;
			this.timeCount = timeCount;
			this.distance = distance;
			
		}
		// [1순위] 거리, [2순위] 위부터 (r값 오름차순), [3순위] 좌부터 (c값 오름차순)
		@Override
		public int compareTo(Fish o) {
			if(this.distance < o.distance) {
				return -1;
			} else if(this.distance == o.distance) {
				if(this.r < o.r) {
					return -1;
				} else if(this.r == o.r) {
					if(this.c < o.c) {
						return -1;
					} else if(this.c == o.c) {
						return 0;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}
	}

	static int N, sharkSize, eatCount, maxTime;
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	static int[][] map;
	static boolean[][] visit;
	static PriorityQueue<Fish> fishes;
	static Queue<Fish> babyShark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		fishes = new PriorityQueue<>(); // 먹이가 될 물고기 후보들
		babyShark = new LinkedList<>(); // 상어의 현재 위치 보관할 큐
		sharkSize = 2; // 현재 상어 크기
		eatCount = 0;  // 현재 상어가 먹은 먹이의 수
		maxTime = 0;   // 최대로 돌아댕긴 시간 갱신
		int sharkR = 0;// 현재 상어의 좌표 
		int sharkC = 0;
		
		
		// 배열 입력하면서 상어(9)면 상어의 좌표만 입력하고 지도에 입력 x
		// 상어는 상어 큐에 넣어놓는다.
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken()); 
				if(num == 9) {
					babyShark.offer(new Fish(r, c, 0, 0));
					sharkR = r;
					sharkC = c;
				} else {
					map[r][c] = num;
				}
			}
		}
		
		// 먹이를 찾을 수 없다면 while문 종료
		while(true) {
			visit = new boolean[N][N];
			visit[sharkR][sharkC] = true;
			
			// 먹이 찾기
			findFish();
			if(fishes.isEmpty()) break;
			
			// 우선순위 조건에 따라 나온 물고기를 먹고 시간을 갱신한다.
			Fish fish = fishes.poll();
			maxTime = Math.max(maxTime, fish.timeCount);
			
			// 상어의 크기만큼 물고기를 먹었다면 상어크기 +1, 먹은 먹이 수 초기화(0)
			if(sharkSize == ++eatCount) {
				sharkSize++;
				eatCount = 0;
			}
			
			// 먹은 물고기 자리는 0으로 만들고 물고기 큐를 비운다.(다시 탐색해서 채울 예정)
			map[fish.r][fish.c] = 0;
			fishes.clear();
			
			// 상어의 현재좌표를 물고기 좌표로 이동시키고 다시 처음부터
			babyShark.offer(new Fish(fish.r, fish.c, fish.timeCount,0));
			sharkR = fish.r;
			sharkC = fish.c;
		}
		
		// while문 끝났을 때 최대 시간 출력
		System.out.println(maxTime);

	}
	
	static void findFish() {
		while(!babyShark.isEmpty()) {
			Fish shark = babyShark.poll();
			int r = shark.r;
			int c = shark.c;
			int time = shark.timeCount;
			int distance = shark.distance;
			for(int d = 0; d < 4; d++) { // 상하좌우 탐색 (우선순위 조건 있으니 순서는 상관X)
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(check(nr, nc) && map[nr][nc] <= sharkSize && !visit[nr][nc]) { // (이동)경계범위, 물고기 사이즈 확인, 방문확인
					if(map[nr][nc] < sharkSize && map[nr][nc] != 0) { // (먹이) 사이즈 확인, 빈공간 확인
						fishes.add(new Fish(nr, nc, time+1, distance+1)); // 먹이라면 fishes 큐에 넣는다.
					}
					visit[nr][nc] = true;
					babyShark.add(new Fish(nr, nc, time+1,distance+1)); // 단순한 이동이라면 방문체크하고 babyShark 큐에 넣는다.
				}
			}
		}
	}
	
	// 경계조건 확인
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}

}