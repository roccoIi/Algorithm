import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] arr = new int[100][100];
			
		for(int T = 1; T <= 10; T++) {
			int rPoint = 99;
			int cPoint = 0;
			boolean move = true; //상하이동 true, 좌우이동 false
			boolean LR = true; // true : 왼쪽 이동, false : 오른쪽 이동
			
			int num = Integer.parseInt(br.readLine());
			
			for(int r = 0; r < 100; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c = 0; c < 100; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 도착지점 좌표 찾기
			for(int c = 0; c < 100; c++) {
				if(arr[99][c] == 2) {
					cPoint = c;
					break;
				}
			}
			
			while(rPoint-1>=0) {
				if(move) { // 상하이동중
					if(cPoint == 0) { //왼쪽 경계에 붙어 있을 경우
						if(arr[rPoint][cPoint+1] == 1) {
							move = false;
							LR = false;
							cPoint++;
						} else {
							rPoint--;
						}
					} else if(cPoint == 99) { // 오른쪽 경계에 붙어있을 경우
						if(arr[rPoint][cPoint-1] == 1) {
							move = false;
							LR = true;
							cPoint--;
						} else {
							rPoint--;
						}
					} else {//  그 외 왼쪽 오른쪽 모두 경계가 아닌경우
						if(arr[rPoint][cPoint-1] == 1) {
							move = false;
							LR = true;
							cPoint--;
						} else if(arr[rPoint][cPoint+1] == 1) {
							move = false;
							LR = false;
							cPoint++;
						} else {
							rPoint--;
						}
					}
				} else if(!(move)) { //좌우이동중
					if(arr[rPoint-1][cPoint] == 1) {
						move = true;
						rPoint--;
					} else {
						if(LR) {
							cPoint--;
						} else {
							cPoint++;
						}
					}
				}
			}
			System.out.printf("#%d %d\n", T, cPoint);
		}
	}
}