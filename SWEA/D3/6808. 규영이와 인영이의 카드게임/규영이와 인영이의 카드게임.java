import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * [회고]
 *  1. 규영이 카드가 주어졌을때 인영이의 카드를 받기 위해 boolean 배열을 만들어서 큐영이가 카드를 받을때마다 체크하고
 *  	남은 카드들을 인영이에게 주었다. (임시배열 temp로다가)
 *  2. 카드와 똑같은 길이의 check배열과 진짜 인영이의 카드뭉치인 inYoung배열을 만들었다.
 *  3. check배열을 체크해가면서 인영이에게 카드를 주었고 모두 나눠줬을 때 게임 시작 (순열)
 *  4. 하나씩 비교하면서 qSum과 iSum에 점수를 넣었고 최종 점수를 비교해서 qWin, iWin에 승점을 기록했다.
 *  5. 모든 비교 종료 후 qWin과 iWin 출력 후 종료
 */

public class Solution {	
	static boolean[] check;
	static int[] qYoung = new int[9]; // 규영 카드뭉치
	static int[] inYoung = new int[9]; // 인영 카드뭉치
	static int[] temp = new int[9]; // 처음 주어지는 배열로 구할 인영이의 임시 카드뭉치
	static int qWin, iWin;
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; StringBuilder sb;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int t = 1; t <= testCase; t++) {
        	sb = new StringBuilder();
        	int idx = 0;
        	int num;
        	qWin = 0; iWin = 0;
        	st = new StringTokenizer(br.readLine());
      
        	check = new boolean[19]; 
        	for(int i = 0; i < 9; i++) {
        		num = Integer.parseInt(st.nextToken());
        		check[num] = true; // 처음 주어지는 카드를 받을때마다 체크해놓고
        		qYoung[i] = num;  // 규영이 카드뭉치에 추가한다.
        	}
        	for(int i = 1; i <check.length; i++) {
        		if(check[i]) continue; // 배열에 체크 안된 인덱스들을 인영이 임시 카드뭉치에 할당
        		temp[idx++] = i;
        	}
        	
        	check = new boolean[9];
        	playGame(0);
        	
        	sb.append("#").append(t).append(" ").append(qWin).append(" ").append(iWin);
        	System.out.println(sb);
        }
        
        
	}
	
	static void playGame(int idx) {
		if(idx == 9) { // 모든 카드를 나눠주었을때 기저조건 발생
			int qSum = 0;
			int iSum = 0;
			for(int i = 0; i < 9; i++) { // 9개 카드를 인덱스순서로 비교한다.
				if(qYoung[i] > inYoung[i]) {
					qSum += (qYoung[i] + inYoung[i]);	
				} else {
					iSum += (qYoung[i] + inYoung[i]);
				}
			}
			// 이기는팀 우리팀
			if(qSum > iSum) qWin++;	
			else if(qSum < iSum) iWin++;
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(check[i]) continue; // 만약에 체크 되어있으면 다음 인덱스 확인
			
			inYoung[idx] = temp[i]; // 안되어있으면 인영이의 진짜 카드뭉치에 카드 넣고
			check[i] = true; // 체크하고
			playGame(idx+1); // 재귀
			check[i] = false; // 체크를 푼다.
		}
	}	
}