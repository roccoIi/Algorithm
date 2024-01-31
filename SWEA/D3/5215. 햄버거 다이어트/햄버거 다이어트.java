import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ingreNum = Integer.parseInt(st.nextToken());
			int maxKcal = Integer.parseInt(st.nextToken());

			// 햄버거 재료 테이블
			int[][] ingredients = new int[ingreNum+1][2];
			for(int i = 1; i <= ingreNum; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				// 0열은 맛, 1열은 칼로리
				ingredients[i][0] = Integer.parseInt(st2.nextToken());
				ingredients[i][1] = Integer.parseInt(st2.nextToken());
			}
			
			// dp테이블 생성
			// 세로는 재료의 개수, 가로는 1단위로 칼로리마다 넣을 수 있는지 없는지 판단
			// +1은 0인 이전행이 0일때 그 수를 가져와야하는 상황이 발생하기 때문
			int[][] dp = new int[ingreNum+1][maxKcal+1];
			
			// 모든 재료들을 탐색하면서 진행 할 예정
			for(int r = 1; r <= ingreNum; r++) {
				// 모든 칼로리들을 탐색하면서 칼로리를 넘는지 안넘는지 탐색할 예정
				for(int c = 1; c <=maxKcal; c++) {
					// 1. 가방에 넣지 못할때
						if(ingredients[r][1] > c) { //만약 해당 재료의 칼로리가 칼로리 수용량보다 많다면
													// 지금 이 재료를 넣지 못하면 그 전까지 넣어놨던게 가장 크다
						dp[r][c] = dp[r-1][c];
					} else {
						// 2. 가방에 넣을 수 있을때
						// 2-1. 이전까지 담아놨던게 오히려 더 큼
						// 2-2. 지금 탐색중인 재료의 맛을 더하고 그때 남은 칼로리를 봤을때 남은 칼로리의 최적해를 더한다.
						// 	=> ex ) 총 7kg을 담을 수 있는 주머니에 4kg, $3 짜리 보석을 넣었다. 남은 무게는 3kg
						//  => 		즉, 주머니가 3kg일때 담을 수 있는 가장 높은 가치를 더하면 된다.
						// 2-1과 2-2 중 더 큰 값을 저장한다.
						dp[r][c] = Math.max(dp[r-1][c], dp[r-1][c-ingredients[r][1]] + ingredients[r][0]);
					}
				}
			}
			System.out.printf("#%d %d\n", t, dp[ingreNum][maxKcal]);
		}
	}
}