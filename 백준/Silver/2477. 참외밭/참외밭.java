import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int maxTotal = 1;
		int minTotal = 1;
		
		// 평당 수확가능한 참외 개수
		int value = Integer.parseInt(br.readLine());
		
		// 1열은 방향, 2열은 길이
		int[][] arr = new int[6][2];
		for(int r = 0; r < 6; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 2; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방향이 총 몇개 있는지에 대한 배열
		int[] numbers = new int[5];
		for(int r = 0; r < 6; r++) 
			numbers[arr[r][0]]++;
	
		// 방향이 1번 등장한 index가 전체 넓이를 구할 수 있는 가장 긴 변
		for(int i = 1; i <= 4; i++) {
			if(numbers[i] == 1) {
				for(int j = 0; j < 6; j++) {
					if(arr[j][0] == i) {
						maxTotal *= arr[j][1];
					}
				}
			}
		}
		
		// 앞뒤로 방향이 둘 다 홀수거나 둘 다 짝수일때 작은 사각형의 넓이를 구할 수 있다.
		for(int i = 0; i < 6; i++) {
			int before = (i + 5) % 6; // 맨 앞과 맨 뒤일 수 있으니 나머지로 index를 구한다.
			int after = (i + 7) % 6;
			if((arr[before][0] % 2 ==0 && arr[after][0] % 2 ==0)
					||(arr[before][0] % 2 ==1 && arr[after][0] % 2 ==1)) {
				minTotal *= arr[i][1];
			}
		}
		System.out.println((maxTotal - minTotal) * value);		
	}
}