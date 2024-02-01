import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int T = 1; T <= 10; T++) {
			int maxCount = Integer.MIN_VALUE;
			int t = Integer.parseInt(br.readLine());
			char[][] str = new char[100][100];
			for(int i = 0; i < 100; i++) {
				str[i] = br.readLine().toCharArray();
			}
			
			
			for(int i = 0; i < 99; i++) {
				for(int j = 0; j < 99; j++) {
					
					// 가로탐색
					// 만일 ABBA의 회문일 경우
					if(str[i][j+1] == str[i][j]) {
						int count = 2;
						int num = 1;
						// 범위를 넘지 않는 선에서 한칸씩 옆으로 확장해나가며 탐색
						while(j - num >=0 && j + 1 + num <=99 && str[i][j+1 + num] == str[i][j - num]) {
							count += 2; num++;
						}
						if(count > maxCount)
							maxCount = count;
					} else { // 만일 ABA의 회문일 경우
						int count = 1;
						int num = 1;
						while(j - num >=0 && j + num <=99 && str[i][j + num] == str[i][j - num]) {
							count += 2; num++;
						}
						if(count > maxCount)
							maxCount = count;
					}
					
					// 세로탐색
					// 만일 ABBA의 회문일 경우
					if(str[j+1][i] == str[j][i]) {
						int count = 2;
						int num = 1;
						while(j - num >=0 && j + 1 + num <=99 && str[j + 1 + num][i] == str[j - num][i]) {
							count += 2; num++;
						}
						if(count > maxCount)
							maxCount = count;
					} else {  // 만일 ABA의 회문일 경우
						int count = 1;
						int num = 1;
						while(j - num >=0 && j + num <=99 && str[j + num][i] == str[j - num][i]) {
							count += 2; num++;
						}
						if(count > maxCount)
							maxCount = count;
					}
				}
			}
			
			System.out.printf("#%d %d\n", t, maxCount);
		}
	}
}