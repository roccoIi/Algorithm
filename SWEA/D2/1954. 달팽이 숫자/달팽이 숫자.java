import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= t; T++) {
			int num = Integer.parseInt(br.readLine());
			int[][] arr = new int[num][num];
			int idx = 1; // 누적숫자 (배열에 입력할 숫자)
			int count = 0; // 지나온 횟수 카운트 (짝홀수 사용 예정)
			int rPoint = 0; // 좌표 초기값 설정
			int cPoint = -1; // 좌표 초기값 설정
			
			for(int i = 0; i < num; i++) {
				if(count == 0) { // 첫줄 한정 (한줄로 쭉 1234...)
				for(int j = 0; j < num; j++) {
						arr[rPoint][++cPoint] = idx++; // 먼저 ++하고 cPoint 계산하기 때문에 초기값 -1 로 설정
					}
				count++; 
				} else if(count % 2 == 1) { // 수행 횟수가 홀수일 경우 => 아래, 왼쪽 방향 진행
					for(int j = 0; j < num-count; j++) {
						arr[++rPoint][cPoint] = idx++;
					}
					for(int j = 0; j < num-count; j++) {
						arr[rPoint][--cPoint] = idx++;
					}
					count++;
				} else if(count % 2 == 0) { // 수행 횟수가 짝수일 경우 => 위, 오른쪽 방향 진행
					for(int j = 0; j < num - count; j++) {
						arr[--rPoint][cPoint] = idx++;
					}
					for(int j = 0; j < num - count; j++) {
						arr[rPoint][++cPoint] = idx++;
					}
					count++;
				}
			}
			System.out.println("#" + T);
			for(int r = 0; r < num; r++) {
				for(int c = 0; c < num; c++) {
					System.out.print(arr[r][c] + " ");
				}
				System.out.println();
			}	
		}
	}
}