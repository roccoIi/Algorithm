import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 기어는 전부 리스트배열로 받았다. 톱니바퀴의 번호와 index를 맞추기 위해 1부터 시작.
 * 2. 기어의 회전가능 여부는 돌릴 톱니바퀴의 인덱스를 기준으로 오른쪽과 왼쪽을 나누어 탐색하고 canTurn 배열에 저장했다.
 * 3. 기어의 회전은 리스트의 맨 앞을 제거해 맨 뒤에 놓거나 맨 뒤를 제거해 맨 앞에 놓는, 큐처럼 구현했다.
 *   (이럴꺼면 그냥 덱으로 할껄그랬다.)
 * 4. 총 테스트케이스만큼 반복하면서 기어의 회전여부를 확인하고 왼쪽과 오른쪽을 나눠 돌려주었다.
 * 5. 한번 돌리면 -1을 곱해서 방향을 바꿔준다.
 * */

public class Main {
	static List<Integer>[] gears = new ArrayList[5];
	static boolean[] canTurn;
	static int[][] test;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// list 배열 초기화
		for(int i = 0; i < 5; i++) {
			gears[i] = new ArrayList<>();
		}
		
		// list배열 1~4번 인덱스에 각각 8개의 톱니바퀴 숫자를 넣어준다.
		for(int i = 1; i <= 4; i++) {
			String str = br.readLine();
			for(int j = 0; j < 8; j++) {
				gears[i].add(str.charAt(j) - '0');
			}
		}
		
		// test배열에는 각 테스트를 넣어준다. [0번 idx]: 톱니바퀴 번호, [1번 idx]: 방향
		int testCase = Integer.parseInt(br.readLine());
		test = new int[testCase][2];
		for(int i = 0; i < testCase; i++) {
			st = new StringTokenizer(br.readLine());
			test[i][0] = Integer.parseInt(st.nextToken());
			test[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i < testCase; i++) {
			// 기어가 돌 수 있는지 없는지 저장할 boolean 배열
			canTurn = new boolean[5];
			int targetGear = test[i][0];
			int direction = test[i][1];
			
			
			// 각 기어들이 돌아갈 수 있는지 없는지 canTurn 배열에 저장한다.
			// 기준이 되는 기어는 무조건 돌아가니깐 True;
			canTurn[targetGear] = true;
			checkGear(targetGear);
			
			// 회전가능여부를 파악하고 좌우 나누어서 돌려준다.
			// 회전시킬 기어 + 오른쪽 기어들
			for(int j = targetGear; j <= 4; j++) {
				if(canTurn[j]) {
					turnGear(j, direction);
					direction *= -1;					
				}
			}
			
			// 회전시킬 기어 기준 왼쪽 기어들
			direction = test[i][1] * -1;
			for(int j = targetGear-1; j >0; j--) {
				if(canTurn[j]) {
					turnGear(j, direction);
					direction *= -1;					
				}
			}	
		}
		
		
		// 점수 계산
		int score = 0;
		for(int i = 1; i <= 4; i++) {
			score += (gears[i].get(0) * Math.pow(2, i-1));
		}
		
		System.out.println(score);
		
	}
	
	static void checkGear(int num) {
		// 회전시킬 기어 기준 오른쪽 확인
		for(int i = num; i < 4; i++) {
			if(gears[i].get(2) != gears[i+1].get(6)) {
				canTurn[i+1] = true;
			} else {
				// 한번 못돌아가면 그 이후것들은 전부 못돌아가니 break로 해당방향 탐색 종료	
				break; 
			}
		}
		// 회전시킬 기어 기준 왼쪽 확인
		for(int i = num; i > 1; i--) {
			if(gears[i].get(6) != gears[i-1].get(2)) {
				canTurn[i-1] = true;
			} else {
				break;
			}
		}
	}
	
	//기어 돌리기
	static void turnGear(int index, int direction) {
		if(direction == 1) {
			int num = gears[index].remove(7);
			gears[index].add(0,num);
		} else {
			int num = gears[index].remove(0);
			gears[index].add(7, num);
		}
	}
}