import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [회고]
 * 1. 주어진 수 [1, 2, 1, 2] 를 하나씩 더해보면서 합이 3이 되었을때 멈춰야 한다고 생각했다.
 * 2. 단, 여기서 첫번째 1과 세번째1 / 두번째 2와 네번째 2 는 서로 다른 1과 2라고 생각했다.
 * 3. 기저조건은 합(sum)이 4가 되었을때(카운트+1), 합(sum)이 5가 넘을때(그냥 종료), index가 배열을 넘어설때(그냥 종료)
 * 4. 코드 옆에 적힌 주석은 input값을 기준으로 작성
 * 
 * [input]
 * 1
 * 4 3
 * 1 2 1 2
 * 
 * */


public class Solution {
	static int N, target, count;
	static int[] arr;
	static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine()); // 테스트케이스
		
		for(int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 주어지는 수
			target = Integer.parseInt(st.nextToken()); // 목표로 하는 수
			arr = new int[N];
			visit = new boolean[N]; // 해당 숫자를 넣었는지 아닌지 판단하기 위한 boolean배열
			count = 0; // 합이 3(target)과 같다면 +1예정
			
			// 주어진 배열 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			find(0,0);
			
			System.out.printf("#%d %d\n", t, count);
			
			
			
			
		}
	}
	
	static void find(int idx, int sum) {
		// 기저조건
		if(sum == target) { // 만일 합이 3이 된다면 카운트+1 후 종료
			count++;
			return;
		} else if(sum > target) { // 합이 3을 넘어버리면 카운트 하지 말고 종료
			return;
		}
		
		if(idx >= N) { // 인덱스를 넘어버리면 종료
			return;
		}
		
		// 재귀
		visit[idx] = true; // 방문했다면
		find(idx+1, sum + arr[idx]); // 해당 배열의 숫자 넣고 재귀
		
		visit[idx] = false; // 방문하지 않았다면
		find(idx+1, sum); // 해당 배열의 숫자는 넣지 않고 재귀
	}
}