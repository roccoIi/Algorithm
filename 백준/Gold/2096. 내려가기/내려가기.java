import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * [회고]
 * 1. 문제에서는 원룡이가 갈 수 있는 곳으로 표현했지만 문제를 풀기 위해선 원룡이가 각 지역에서 받을 수 있는 수로 이해해야했다.
 * 2. 1번 위치에 있을 때 갈 수 있는 지역이 1번과 2번으로 이해하는 것이 아닌
 * 	  1번 위치에 있을 때 받아올 수 있는 번호가 이전 줄의 1번과 2번으로 이해하는 것이다.
 * 3. 즉, dp를 위해 초기 값을 계산해볼 때 n번 이후 n+1을 생각하는 것이 아니라 n번일 때 n-1과의 관계를 생각하는 것이다.
 */ 


public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine().trim());
		int[][] arr = new int[N+1][3];
		int[][] dpMax = new int[N+1][3];
		int[][] dpMin = new int[N+1][3];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 최솟값과 최댓값 dp의 초기값은 처음 주어지는 배열의 첫 행과 같다.
		for(int i = 0; i < 3; i++) 
			dpMax[0][i] = dpMin[0][i] = arr[0][i];
		
		for(int i = 1; i <= N; i++) {
			// 최댓값 구하기 
			dpMax[i][0] = arr[i][0] + Math.max(dpMax[i-1][0], dpMax[i-1][1]);
			dpMax[i][1] = arr[i][1] + Math.max(dpMax[i-1][0], Math.max(dpMax[i-1][1], dpMax[i-1][2]));
			dpMax[i][2] = arr[i][2] + Math.max(dpMax[i-1][1], dpMax[i-1][2]);
			
			// 최솟값 구하기
			dpMin[i][0] = arr[i][0] + Math.min(dpMin[i-1][0], dpMin[i-1][1]);
			dpMin[i][1] = arr[i][1] + Math.min(dpMin[i-1][0], Math.min(dpMin[i-1][1], dpMin[i-1][2]));
			dpMin[i][2] = arr[i][2] + Math.min(dpMin[i-1][1], dpMin[i-1][2]);
		}
		
		// dpMax와 dpMin의 마지막행의 최댓값과 최솟값을 구하여 출력한다.
		int ansMax = Math.max(dpMax[N][0], Math.max(dpMax[N][1], dpMax[N][2]));
		int ansMin = Math.min(dpMin[N][0], Math.min(dpMin[N][1], dpMin[N][2]));
		
		sb.append(ansMax).append(" ").append(ansMin);
		System.out.println(sb);
	}
}