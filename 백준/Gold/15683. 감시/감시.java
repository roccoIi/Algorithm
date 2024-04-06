import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [회고] 
 * 1. 어떻게 풀어야할지는 그냥 처음부터 브루트포스였다. 단, 코드의 반복을 줄이고 싶었다.
 * 2. 첫번째 cctv일때, 두번째 cctv일때, ...로 if문 여러개 써서 설게하는 방식은 과거에 해봤으니 코드의 길이를 줄이고 싶었다.
 * 3. 각 cctv별 경우의수를 cctvDir 배열에 3차원 방식으로 저장했다.
 * 4. 배열 속 숫자들은 방향을 의미한다.
 * 5. 5번 cctv는 모든 경우에서 동일한 결과를 나타내기에 반복은 무의미하여 처음에 처리하고 시작했다.
 * 6. 지도를 복사해 가져가는 방법에서 애를 먹었다.
 * 7. 내가 2차원 배열을 들고갈때는 지도별로 구분지어질때 직전에 복사해서 복사본을 들고가는 방법이 성공했다.
 * 8. 제일 좋은 방법은 지도에 표시하지 않는 방법이 아닐까..?
 * -------(놓친점)---------
 * 9. 탐색을 위해 반복문의 시작점을 c-1로 했던 것을 까먹고 초기 값에 0을 주어서 범위에 일치하지 않
 *    탐색이 되지 않았던 실수 발생.
 * 10. 처음 5번 cctv를 일괄 처리하는 과정에서 0의 개수를 함께 받았더니 기존 0이    
 * 	  -1로 변해 카운트되지 않던 점 수정. 0 카운트를 먼저 진행
 * ------(놓친점)----------
 * 11. 재귀 들어간 지점부터 다시 반복문을 시작할때 경계에 다다르면 0부터 시작하지 않고
 *     다시 입력받은 지점부터 반복문이 시작되던 오류 해결 
 */


public class Main {

	static int R, C, maxSee;
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	static int[][][] cctvDir = {{}, // 각 cctv별 탐색 경우의 수 입력.(index 0은 비어있다.)
            {{1}, {2}, {3}, {4}},   // [1: 상, 2: 하, 3: 우, 4: 좌]
            {{1, 3}, {2, 4}},
            {{1, 2}, {2, 3}, {3, 4}, {1, 4}},
            {{1, 2, 3}, {2, 3, 4}, {1, 3, 4}, {1, 2, 4}},
            {{1, 2, 3, 4}}};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int[][] map = new int[R][C];
		int cnt = 0;
		int zeroCnt = 0;
		maxSee = Integer.MIN_VALUE;

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0의 개수 확인
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 0) zeroCnt++;
			}
		}

		// 사방을 전부 탐색하는 5번 CCTV는 언제나 동일한 결과를 가져오므로 미리 체크한다.
		// 겸사겸사 0 (사각지대)의 수도 체크한다.
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 5) {
					for (int d = 1; d <= 4; d++) {
						cnt += seeCCTV(r, c, d, map);
					}
				}
			}
		}
		find(0, 0, map, cnt); // 해당 함수 첫 반복문에 c+1부터 탐색하기 때문에 초기값은 -1

		System.out.println(zeroCnt - maxSee);
	}

	static void find(int r, int c, int[][] map, int cnt) {
		boolean flag = true;
		
		// 재귀 들어왔을 때 기저조건
		if (r >= R) {
			maxSee = Math.max(maxSee, cnt);
			return;
		}

		int tmpCnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) { // 만약 오른쪽 끝에서 재귀를 탔다면 다음 행으로 넘어가도록 +1
				// 지금 막 들어온 애면 탐색했던 그 지점부터 탐색하도록 i, j값을 바꿔준다.
				if(flag) {
					i = r;
					j = c;
					flag = false;
				}
			
				int num = map[i][j];
				if (num == 1 || num == 2 || num == 3 || num == 4) {
					for (int k = 0; k < cctvDir[num].length; k++) {
						// 각 cctv별 경우의수 진행 시 지도복사 및 tmpCnt초기
						tmpCnt = 0;
						
						// 여기서 지도별로 구분지어진다. 이 지도를 가지고 특정 인덱스의 cctv를 탐색할 예정이기 때문이다.
						int[][] tmp = new int[R][C];
						for (int a = 0; a < R; a++) {
							for (int b = 0; b < C; b++) {
								tmp[a][b] = map[a][b];
							}
						}
						// cctv방향별 진행.
						for (int l = 0; l < cctvDir[num][k].length; l++)
							tmpCnt += seeCCTV(i, j, cctvDir[num][k][l], tmp);
						
						// 재귀
						// 다음칸으로 변경해서 넘겨준다.
						if(j == C-1) {
							find(i+1, 0, tmp, cnt + tmpCnt);
						} else {
							find(i, j+1, tmp, cnt + tmpCnt);

						}
					}
				}
			}
		}
			
		// 반복문 종료되면 최댓값 갱신 후 리턴
		maxSee = Math.max(maxSee, cnt);

	}
	// 각 방향(dir) 별 진행 [1: 상, 2: 하, 3: 우, 4: 좌]
	// 해당 방향의 0을 모두 -1로 바꿔준다.
	static int seeCCTV(int r, int c, int dir, int[][] map) {
		int cnt = 0;
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (!check(nr, nc) || map[nr][nc] == 6)
				return cnt; // 벽만나거나 경계 벗어날때만 종료 후 cnt 반환
			if (map[nr][nc] == 0) {
				map[nr][nc] = -1;
				cnt++;
			}
		}
	}

	// 경계조건 확인
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
}