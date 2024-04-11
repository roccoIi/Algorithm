import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, maxHouse; // N: 도시크기 (5 <= N <= 20), M: 가구당 지불비용 (1 <= M <= 10)
	static int[][] map;
	static int[][] dir = { { -1, 1, 0, 0 }, { 0, 0, -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			maxHouse = Integer.MIN_VALUE;

			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 1; i <= N+1; i++) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						int house = bfs(r, c, i);
						int profit = house * M;
						int cost = i * i + (i - 1) * (i - 1);
						if (profit >= cost) // 흑자일때 갱신
							maxHouse = Math.max(house, maxHouse);
					}
				}
			}
			sb.append(maxHouse).append("\n");
		} // testCase 종료
		System.out.println(sb);
	}// main 종료

	// 운영영역 내 가구 수
	static int bfs(int r, int c, int length) {
		boolean[][] visit = new boolean[N][N];
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c));
		visit[r][c] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			if(map[node.r][node.c] == 1) cnt++;
			for (int d = 0; d < 4; d++) {
				int nr = node.r + dir[0][d];
				int nc = node.c + dir[1][d];
				if (!check(nr, nc))
					continue;
				if (!visit[nr][nc] && getLength(r, c, nr, nc) <= length) {
					visit[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
		return cnt;
	}// bfs종료

	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}// check 종료

	static int getLength(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2) + 1;
	}// getLength 종료
}