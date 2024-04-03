import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 1. 원숭이의 움직임(mMove)와 말의 움직임(hMove)를 동시에 컨트롤 해야했다.
 * 2. bfs내에서 두가지 경우를 다 진행해보기로 했다.
 * 3. 움직일 기회가 남아있다면 mMove, hMove 둘다 진행
 * 4. 기회가 없다면 mMove만 진행
 * 5. 각 기회 별 방문배열은 3차원으로 구현하였다.
 * */
public class Main {
	static int N, M, chance, minMove;
	static int[][] map;
	static boolean[][][] visit;
	static int[][] mMove = {{-1, 1, 0, 0},{0, 0, -1, 1}}; // 상하좌우 monkeyMove
	static int[][] hMove = {{-2, -2, -1, 1, 2, 2, 1, -1}  // 2+a 칸씩 넘나드는 horseMove 
						   ,{-1, 1, 2, 2, 1, -1, -2, -2}};
	
	// 원숭이 객체
	static class Monkey{
		int r, c, movement, chance;

		public Monkey(int r, int c, int movement, int chance) {
			this.r = r;
			this.c = c;
			this.movement = movement;
			this.chance = chance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		chance = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		visit = new boolean[N][M][chance+1];
		minMove = Integer.MAX_VALUE;
		
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit[0][0][0] = true;
		
		bfs(new Monkey(0, 0, 0, 0));
		
		// 한번도 갱신되지 못했다면 도착지점에 도착하지 못했다. -1 반환
		System.out.println(minMove == Integer.MAX_VALUE?-1:minMove);
		
	}
	
	static void bfs(Monkey start) {
		Queue<Monkey> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			Monkey monkey = q.poll();
			
			// 도작지점에 도착했다면 최솟값을 해당 객체의 movement와 비교하여 갱신한다.
			if(monkey.r == N-1 && monkey.c == M-1) {
				minMove = Math.min(minMove, monkey.movement);
			}
			
			// 기본적인 상하좌우 움직임. 조건만 충족한다면 동작+1 하고 이동시킨다.
			for(int d = 0; d < 4; d++) {
				int nr = monkey.r + mMove[0][d];
				int nc = monkey.c + mMove[1][d];
				if(!check(nr,nc)) continue;
				if(map[nr][nc] == 0 && !visit[nr][nc][monkey.chance]) {
					q.offer(new Monkey(nr, nc, monkey.movement+1, monkey.chance));
					visit[nr][nc][monkey.chance] = true;
				}
			}
			
			// 만일 기회가 남아있다면 말의 움직임으로도 이동해본다.
			// 기회가 있다면 동작+1 하고 말처럼 움직여본다.
			// 기회 사용후 해당 기회가 포함된 방문배열에 방문표시를 한다.
			if(monkey.chance < chance) {
				for(int d = 0; d < 8; d++) {
					int nr = monkey.r + hMove[0][d];
					int nc = monkey.c + hMove[1][d];
					if(!check(nr,nc)) continue;
					if(map[nr][nc] == 0 && !visit[nr][nc][monkey.chance+1]) {
						q.offer(new Monkey(nr, nc, monkey.movement+1, monkey.chance+1));
						visit[nr][nc][monkey.chance+1] = true;
					}
				}
			}
		}
	}
	
	// 경계조건
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}