import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		int T = sc.nextInt();
		for(int i = 1; i <= T; i++) {
			boolean flag = true; //하나라도 안되면 바로 깃발 돌려버리기
			//제시되는 숫자 입력받기 N, M, K
			int person = sc.nextInt();
			int makeTime = sc.nextInt();
			int product = sc.nextInt();
			
			// 도착하는 손님 시간순으로 재정렬
			int[] arr = new int[person];
			for(int j = 0; j < person; j++) {
				arr[j] = sc.nextInt();
			}
			Arrays.sort(arr);
			
			int personLine = 0; // 배열을 위한 숫자
			int nowProduct = 0; // 현재 다 구워진 붕어빵 수
			for(int t=1; t<=11111; t++) { // t는 현재 경과한 시간으로 생각한다.
				if(arr[0] == 0) { //0초에 손님 도착하면 붕어빵 없으니 바로 깃발 돌리기
					flag = false;
					break;
				}
				
				if(t % makeTime ==0) { // 완성되는 시간의 배수마다 만들어지는 붕어빵수++
					nowProduct += product;
				}
				
				if(personLine < person && arr[personLine] == t) { //배열을 벗어나지 않고 현재 시간이 손님 도착할 시간이라면
					personLine++; // 다음손님으로 대기줄 올리고
					nowProduct--; // 지금 가지고있는 붕어빵 하나 --
					if(nowProduct < 0) { //없어가지고 현재 제고가 -1이라면 바로 깃발
						flag = false;
						break;
					}
				}
			}
			if(flag == false) { //깃발 올라갔으면 impossible
				System.out.printf("#%d Impossible\n", i);
			} else {
				System.out.printf("#%d Possible\n", i);
			}
		}
	}
}