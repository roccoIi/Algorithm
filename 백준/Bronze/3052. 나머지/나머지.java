import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[42];
        
		// 42로 나누는 나머지 (0~41)이 들어갈 배열 생성
		// 나머지에 해당하는 배열 위치 +1
		for(int i = 0; i < 10; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[num%42]++;
		}
        
		// 나머지가 존재하는 (배열 값이 0이 아닌) 위치 카운트
		int count = 0;
		for(int i : arr) {
			if(i !=0) {
				count++;
			}
		}
		System.out.println(count);
	}	
	
}