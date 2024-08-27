import java.util.*;
import java.io.*;


public class Main {
	static int N, arr[][], ans;
	static int[][] dir = {{0, 1, 0, -1}, {-1, 0, 1, 0}};
	static int[][][] spread = {{{-2, -1, -1, -1, 0, 1, 1, 1, 2, 0}, {0, -1, 0, 1, -2, -1, 0, 1, 0, -1}},
	/* 여기는 총 10개 */		  {{0, 1, 0, -1, 2, 1, 0, -1, 0, 1}, {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0}},
							  {{2, 1, 1, 1, 0, -1, -1, -1, -2, 0}, {0, 1, 0, -1, 2, 1, 0, -1, 0, 1}},
	/* 여기는 총 9개*/			  {{0, -1, 0, 1, -2, -1, 0, 1, 0, -1}, {2, 1, 1, 1, 0, -1, -1, -1, -2, 0}}}; 
	static int[] percent = {2, 10, 7, 1, 5, 10, 7, 1, 2};
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static Node location;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ans = 0;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 시작을 (1, 1) ~ (N, N)으로 잡지 않고 (0, 0) ~ (N-1, N-1)로 잡아서 단순히 2로 나누면 소숫점 때고 몫이 중앙값이다.
		location = new Node(N/2, N/2);
		int direction = 0;
		int rotation = 1; // 한바퀴 회전함;
		
		while(true) {
			// 방향 (0, 1, 2, 3) 중 0, 1은 홀수, 2, 3은 짝수로 이동횟수가 증가하게 된다.
			int turn = direction < 2 ? rotation * 2 - 1 : rotation * 2;
			
			// 해당 방향으로 계산된 이동횟수만큼 이동한다.
			for(int i = 0; i < turn; i++) {
				moveTornado(location, direction);
			}
			
			// 만약 회전방향이 한바퀴를 돌았다면 이동횟수를 1 증가시키고 다시 0부터
			if(++direction > 3) {
				rotation++;
				direction = 0;
			}
			
			// 좌표 (0, 0)에 도착했을때 반복을 종료한다. 
			if(location.r == 0 && location.c == 0) break;
		}
		// 정답입니다~
		System.out.println(ans);
	}
	
	static void spreadSend(Node node, int direction) {
		int send = arr[node.r][node.c];
		for(int i = 0; i < 10; i++) {
			// 방향에 따라 모래가 퍼지는 방향과 그 값이 변화한다.
			// 공통 : 만약 범위를 벗어났다면 정답에 누적시키고 그 외에는 지도에 누적한다.
			int nr = node.r + spread[direction][0][i];
			int nc = node.c + spread[direction][1][i];
			
			if(i == 9) {
				if(check(nr, nc)) {
					arr[nr][nc] += arr[node.r][node.c];
					arr[node.r][node.c]= 0; 
				} else {
					ans += arr[node.r][node.c];
				}	
			} else {
				if(check(nr, nc)) {
					arr[nr][nc] += send * percent[i] / 100;
					arr[node.r][node.c] -= send * percent[i] / 100;
				} else {
					ans += send * percent[i] / 100;
					arr[node.r][node.c] -= send * percent[i] / 100;
				}		
			}
		}
	}
	
	// 방향에 따른 좌표를 계산하고 해당 좌표에서 모래 흩뿌리기 시작
	static void moveTornado(Node node, int direction) {
		int r = location.r + dir[0][direction];
		int c = location.c + dir[1][direction];
		if(r < 0 || c < 0) return;
		location = new Node(r, c);
		spreadSend(location, direction);
	}
	
	// 경계를 벗어났는지 체크
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
	
	// 그냥 출력 함수
	static void printArr() {
		System.out.println("=========================");
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("=========================");
	}
}