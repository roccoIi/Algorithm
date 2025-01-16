import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[][], maxNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][10];
		maxNum = -1;

		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= 9; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1-1) 타순의 4번타자는 1번 선수이다.
		int[] batting = new int[10];
		batting[4] = 1;
		
		// 1-2) 4번 자리는 이미 배정되었다.
		boolean[] visited = new boolean[10];
		visited[4] = true;
		
		// 1-3) 2번 선수부터 자리를 배정받는다.
		battingOrder(visited, batting, 2);
		
		System.out.println(maxNum);
	}
	
	// 타순 설정
	static void battingOrder(boolean[] visited, int[] batting, int num) {
		if(num == 10) {
			int score = play(batting);
			maxNum = maxNum < score ? score : maxNum;
			return;
		}
		
		for(int i = 1; i <= 9; i++) { // i번 자리에 넣을 선수 설정
			if(visited[i]) continue;
			
			batting[i] = num;
			
			visited[i] = true;
			battingOrder(visited, batting, num + 1);
			visited[i] = false;	
		}
	}
	
	// 야구 진행
	static int play(int[] batting) {
		
		int score = 0;
		int player = 1;	
		int playerNum = 0;
		boolean[] base;
		
		// 총 N이닝을 진행할 예정
		for(int i = 1; i <= N; i++) {
			base = new boolean[4];
			int outCnt = 0;
			
			// 아웃 카운트 3이 되기 전까지만 진행
			while(outCnt < 3) {
				playerNum = batting[player];
				
				int num = arr[i][playerNum];
				
				if(num == 0) { // 아웃
					outCnt++;
				} else { // 1루타 ~ 홈런
					base[0] = true;
					score += point(num, base);
				}

				player = player + 1 > 9 ? 1 : player + 1;	
			}
		}
		return score;
	}
	
	// 1루타 ~ 홈런일 경우 각 베이스를 돌면서 발생한 점수 계산
	static int point(int num, boolean[] base) {
		int count = 0;
		
		for(int i = 3; i >= 0; i--) {
			if(base[i]) {
				base[i] = false;
				if(i + num > 3) count++;
				else base[i+num] = true;
			}
		}
		
		return count;
	}
}