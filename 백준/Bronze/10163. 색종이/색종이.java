import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[1001][1001];
		int N = Integer.parseInt(br.readLine()); // 색종이의 수
		int target = 1;
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // X축 좌표
			int y = Integer.parseInt(st.nextToken()); // Y축 좌표
			int xValue = Integer.parseInt(st.nextToken()); // 너비
			int yValue = Integer.parseInt(st.nextToken()); // 높이
			
			for(int j = 0; j < xValue; j++) { // 색종이 칠하기
				for(int k = 0; k < yValue; k++) {
					arr[x+j][y+k] = i;
				}
			}
		}
		while(target <= N){ // 색종이 수만큼 카운팅
			int count = 0;
			for(int r = 0; r <= 1000; r++) {
				for(int c = 0; c <=1000; c++) {
					if(arr[r][c] == target) count++;
				}
			}
			target++;
			sb.append(count).append("\n");
		}
		System.out.println(sb);	
	}
}