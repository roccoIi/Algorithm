import java.util.*;
import java.io.*;


public class Main {
	static boolean[][] visited;
	static int N, M, map[][];
	static int[][] dir = {{0, 0, -1, -1, -1, 0, 1, 1, 1}, {0, -1, -1, 0, 1, 1, 1, 0, -1}};
	static int[][] copyDir = {{-1, -1, 1, 1}, {-1, 1, 1, -1}};
	static Queue<Node> clouds = new LinkedList<>();
	static Queue<Node> addWater = new LinkedList<>();
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 첫 구름 생성 ((1,1) -> (N,N)이라고 했지만 말 안드뤄)
		clouds.add(new Node(N-1, 0));
		clouds.add(new Node(N-1, 1));
		clouds.add(new Node(N-2, 0));
		clouds.add(new Node(N-2, 1));
		
        // 전체 마법 실행
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			moveCloud(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			copyCloud();
			addCloud();
		}
		System.out.println(countBusket());
	}
	
	// 1.구름이동, 2.구름아래 바구니 물 증가, 3.구름삭제
	static void moveCloud(int direction, int length) {
		int r = dir[0][direction] * length;
		int c = dir[1][direction] * length;
		int tmpR, tmpC, nr, nc = 0;
		
		while(!clouds.isEmpty()) {
			// 1. 구름이동
			Node node = clouds.poll();
			tmpR = (node.r + r) % N;
			tmpC = (node.c + c) % N;
			nr = tmpR < 0 ? N + tmpR : tmpR;
			nc = tmpC < 0 ? N + tmpC : tmpC;
			
			// 2. 구름 아래 바구니 물 양 증가
			map[nr][nc]++;
			
			// 3. 구름 삭제 및 증가한 바구니 기억.
			visited[nr][nc] = true;
			addWater.add(new Node(nr, nc));
		}
	}
	
	// 4. 물복사 마법 실행
	static void copyCloud() {
		while(!addWater.isEmpty()) {
			Node node = addWater.poll();
			for(int d = 0; d < 4; d++) {
				int nr = node.r + copyDir[0][d];
				int nc = node.c + copyDir[1][d];
				if(copyCheck(nr, nc)) {
					map[node.r][node.c]++;
				}
			}
		}
	}
	
	// 5. 새로운 구름 생성
	static void addCloud() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				// 2 이상이면서 방금 증가했던 곳이 아닐때 구름 생성 
				if(map[r][c] >= 2 && !visited[r][c]) {
					clouds.add(new Node(r, c));
					map[r][c] -= 2;
					
				// 증가했던 곳이라면 이미 지나갔으니 초기화
				} else if (visited[r][c]) { 
					visited[r][c] = false;
				}
			}
		}
	}
	
	// 출력을 위한 바구니 물 양 계산
	static int countBusket() {
		int ans = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				ans += map[r][c];
			}
		}
		return ans;
	}
	
	// 물 복사마법 실행 시 물 경계값 확인
	static boolean copyCheck(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N && map[r][c] > 0;
	}
	
	// 지도 출력
	static void printMap() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("==========출력완료==========");
	}
}