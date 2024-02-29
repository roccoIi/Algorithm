import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * [회고]
 *  1. 체스판 첫줄 첫번째 - 둘째줄 첫번째 - ... 이렇게 가면서 각 자리에 둘수 있나 없나 확인하면 될것이라고 생각
 *  2. 순열을 통해 기저조건을 만들고 재귀를 들어가려고 하니 visited 배열이랑 복사할 배열을 굳이 안만들어도 되겠다고 생각했다.
 *  3. 체스판 하나를 boolean으로 만들어놓고 거기에 직접 체크 시작 (visited 배열 겸직)
 *  4. 해당 위치에 자리할 수 있는지 없는지를 따로 메소드로 빼면 좋을꺼같아서 작성했다.
 *  5. 마지막줄까지 퀸을 놓고 재귀에 들어갔을때 idx가 N과 같아지면 종료, 끝까지 잘 도착했기때문에 카운트를 하나 올린다.
 * 
 * [메소드]
 *  1. queen(int idx) : 오늘 배운 순열을 활용한 메소드다. 단지 재귀에 들어가는 조건을 좀 더 더했다.
 *  	- 아래 서술할 조건을 통과했다면(return true) 해당위치에 체스를 놓고 (true) 재귀로 들어간다
 *  	- 경계조건을 통과하지 못했다면 다음 칸 탐색한다.
 *  
 *  2. check(int r, int c) : 그 위치에 퀸을 놓을수 있는지 없는지 확인
 *  	- 좌상, 상, 우상 3방면을 확인한다. (위에서부터 놓으면서 내려오기 때문에 하단은 확인 불필요)
 *  	- 해당 위치가 경계를 벗어나는지, 쭉 진행했을때 퀸이 걸려있는지 확인한다.
 *  	- 끝까지 갔을때 모두 통과했다면 return true, 중간에 한번이라도 걸린다면 return false
 */

public class Solution {	
	static int[] dr = new int[] {-1, -1, -1};
	static int[] dc = new int[] {-1, 0, 1};
	static boolean[][] chess;
	static int N, cnt;
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for(int t = 1; t <= testCase; t++) {
        	N = Integer.parseInt(br.readLine());
        	chess = new boolean[N][N];
			cnt = 0;
			
			queen(0);
			System.out.printf("#%d %d\n", t, cnt);
        }       
	}
	
	// 체스판에 퀸을 놓을 수 있는지 없는 지 확인
	static void queen(int idx) {
		if(idx == N) { // 체스판 끝까지 갔다면 count+1 후 return
			cnt++;
			return;
		}
		
		for(int i = 0; i < N ; i++) {
			if(check(idx,i)) { // 경계조건을 통과한다면 해당 위치 true
				chess[idx][i] = true;				
			} else { // 못했다면 다음칸 확인
				continue;
			}
			queen(idx+1); // true로 바꾼 후에 다음 행 탐색하러 재귀
			chess[idx][i] = false; // 재귀 빠져나와서 다시 원상복귀
		}
	}
	
	// 경계조건 확인
	static boolean check(int r, int c) {
		for(int d = 0; d < 3; d++) { // 상단 3개 방향 확인 (좌상, 상, 우상)
			int nr = r + dr[d];
			int nc = c + dc[d];
			while(nr >=0 && nc >= 0 && nr < N && nc < N) { // 경계조건을 통과했을때 계속해서 진행
				if(chess[nr][nc]) { // 해당 방향에 퀸(true)가 있으면 return false
					return false;
				}
				nr += dr[d]; // 체스판 끝에 닿을때까지 계속 진행
				nc += dc[d];
			}
		}
		return true;
	}
}