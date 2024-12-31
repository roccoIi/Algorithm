import java.io.*;
import java.util.*;

public class Main {
	static int arr[][];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int water = Integer.parseInt(br.readLine());
		long waterAmount = 0;
		
		// 초기 물의 양을 입력받고 모두 더해 탱크의 초기 물 양을 구한다.
		// 두번째 행은 해당 수도꼭지가 열려있는지 닫혀있는지를 표시한다. (1: 열림, -1: 닫힘)
		st = new StringTokenizer(br.readLine());
		arr = new int[2][water+1];
		for(int i = 1; i <= water; i++) {
			arr[0][i] = Integer.parseInt(st.nextToken());
			arr[1][i] = 1;
			waterAmount += arr[0][i];
		}
		sb.append(waterAmount).append('\n');
		
		// 수도꼭지 조작을 입력받는다.
		int control = Integer.parseInt(br.readLine());
		for(int i = 0; i < control; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 1) x번째 수도꼭지의 나사를 돌려 1분에 y리터의 물을 내보내기로 한다.
			int task = Integer.parseInt(st.nextToken());
			int faucet = Integer.parseInt(st.nextToken());
			if(task == 1) {
				int amount = Integer.parseInt(st.nextToken());
				
				// 기존 수도꼭지에서 나오던 양과 새로 나올 양의 차이 계산
				int diff = arr[0][faucet] - amount;
				if(diff < 0 && arr[1][faucet] > 0) {
					waterAmount += Math.abs(diff);
				} else if(diff > 0 && arr[1][faucet] > 0) {
					waterAmount -= Math.abs(diff);
				}
				arr[0][faucet] = amount;
				
			// 2) x번째 수도꼭지의 토글버튼(on/off 스위치)를 누른다.
			} else {
				if(arr[1][faucet] > 0) {
					waterAmount -= arr[0][faucet];
				} else {
					waterAmount += arr[0][faucet];
				}
				
				// 수도꼭지를 여닫는다.
				arr[1][faucet] *= -1;
			}
			sb.append(waterAmount).append('\n');
		}
		System.out.println(sb);
	}
}