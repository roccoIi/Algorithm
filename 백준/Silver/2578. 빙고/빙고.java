import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] bingo = new int[25];
		int[] answer = new int[25];	
		String temp = " ";
		int count = 0;
		
		
		// 빙고판 제작 (1차 배열)
		for(int i = 0; i < 5; i++) {
			temp += br.readLine() + " ";
		}
		StringTokenizer st = new StringTokenizer(temp);
		
		for(int i = 0; i < 25; i++) {
			bingo[i] = Integer.parseInt(st.nextToken());
		}
		
		// 문자열 초기화
		temp = " ";
		// 정답 배열 생성 (1차 배열)
		for(int i = 0; i < 5; i++) {
			temp += br.readLine() + " ";
		}
		StringTokenizer st2 = new StringTokenizer(temp);
		
		for(int i = 0; i < 25; i++) {
			answer[i] = Integer.parseInt(st2.nextToken());
		}
		
		
		// 빙고판 맞춰보기
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				if(answer[i] == bingo[j]) {
					bingo[j] = 0;
					break;
				}
			}
			
			// 빙고가 된 열이 있는지 확인
			for(int j = 0; j < 5; j++) {
				int sum = 0;
				sum = bingo[j] + bingo[j+5] +bingo[j + 10] + bingo[j+ 15] + bingo[j+ 20];
				
				if(sum == 0) 
					count++;
			}
			
			
			// 빙고가 된 행이 있는지 확인
			for(int j = 0; j <= 20; j +=5) {
				int sum = 0;
				sum = bingo[j] + bingo[j+1] + bingo[j+2] + bingo[j+3] + bingo[j+4];
				
				if(sum == 0) 
					count++;
			}
			
			// 빙고가 된 대각선이 있는지 확인 (우상)
			int sum = bingo[4] + bingo[8] +  bingo[12] +  bingo[16] +  bingo[20];
			
			if(sum == 0) {
				count++;
				sum = 0;
			}
			
			// 빙고가 된 대각선이 있는지 확인 (좌상)
			sum = bingo[0] + bingo[6] +  bingo[12] +  bingo[18] +  bingo[24];
			
			if(sum == 0) {
				count++;
				sum = 0;
			}
			
			//빙고 확인
			if(count >= 3) {
				System.out.println(i+1);
				break;
			} else { // 빙고3개 안됐으면 초기화
				count = 0;
			}		
		}
	}
}