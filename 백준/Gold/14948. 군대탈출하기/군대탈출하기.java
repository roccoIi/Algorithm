import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c, chance;
		
		Node(int r, int c, int chance){
			this.r = r;
			this.c = c;
			this.chance = chance;
		}
	}
	static int N, M, map[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, 1, -1}};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int maxNum = 0;
		
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				maxNum = maxNum < map[r][c] ? map[r][c] : maxNum;
			}
		}
		
		int minNum = map[0][0] > map[N-1][M-1] ? map[0][0] : map[N-1][M-1];
		
		sb.append(binarySearch(new Node(0, 0, 1), minNum, maxNum));
		System.out.println(sb);
	}
	
	static int binarySearch(Node start, int min, int max) {
		int left = min;
		int right = max;
		int mid = left + (right - left) / 2;
				
		while(left <= right) {
			
			if(findRoute(start, mid)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
			
			mid = left + (right - left) / 2;
		}
		
		return mid;
	}
	
	static boolean findRoute(Node node, int limits) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited0 = new boolean[N][M];
		boolean[][] visited1 = new boolean[N][M];
		
		q.add(node);
		visited1[node.r][node.c] = true;
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				// 진행하지 못하는 조건 3가지 => a) 경계값 이탈
				if(!checkBoundary(nr, nc)) continue;
				
				// b) 이미 방문  
				if(curr.chance == 0) {
					if(visited0[nr][nc]) continue;
				} else {
					if(visited1[nr][nc]) continue;
				}
				
				// c) 최소레벨을 초과했으나 이미 타일을 무시할 기회를 소진한 경우
				if(map[nr][nc] > limits && curr.chance <= 0) continue;
				
				// 1) 최소레벨을 초과했을 경우
				if(map[nr][nc] > limits) {
					// 뛰어넘을 기회가 있을 경우
					if(curr.chance == 1) {
						nr += dir[0][d];
						nc += dir[1][d];
						if(!checkBoundary(nr, nc) || visited0[nr][nc] || map[nr][nc] > limits) continue;
						else {
							if(nr == N - 1 && nc == M - 1) return true;
							q.add(new Node(nr, nc, curr.chance - 1));
							visited0[nr][nc] = true;
						}
					// 뛰어넘을 기회가 없을 경우
					} else {
						continue;
					}
				// 2) 최소레벨 미만의 경로일 경우
				} else {
					if(nr == N - 1 && nc == M - 1) return true;
					q.add(new Node(nr, nc, curr.chance));
					
					// 방문기록 저장
					if(curr.chance == 0) visited0[nr][nc] = true;
					else 				 visited1[nr][nc] = true;
				}	
			}
		}
		return false;
	}
	
	// 범위를 벗어나지 않는다면 True, 벗어난다면 False 반환
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}