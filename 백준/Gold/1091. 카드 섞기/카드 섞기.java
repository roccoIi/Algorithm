import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] result = new int[N];
		int[] shuffle = new int[N];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			shuffle[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(mainSolution(N, result, shuffle));
	}
	
	static int mainSolution(int N, int[] result, int[] shuffle) {
		
		int[] curr = new int[N];
		int[] originArr = new int[N];
		
		// 초기 카드세팅
		for(int i = 0; i < N; i++) {
			curr[i] = i;
			originArr[i] = i;
		}
		
		int totalCnt = 0;
				
		while(true) {
			if(compare(N, curr, result)) return totalCnt;
			
			curr = move(curr, shuffle);
			totalCnt++;
			
			if(same(N, curr, originArr)) return -1;
		}
		
	}
	
	static int[] move(int[] curr, int[] shuffle) {
		int[] newArr = new int[curr.length];
		
		for(int i = 0; i < curr.length; i++) {
			int number = curr[i];
			int index = shuffle[i];
			
			newArr[index] = number;
		}
		
		return newArr;
	}
	
	// 현재 카드가 어느 위치에 있는가 비교
	static boolean compare(int N, int[] curr, int[] result) {
		for(int i = 0; i < N; i++) {
			int card = curr[i]; // 카드번호
			int player = i % 3;  // 플레이어(0~2)
			
			// 해당카드를 소유한 플레이어가 일치하지 않으면 false
			if(result[card] != player) return false;
		}
		
		return true;
	}
	
	// i번째 카드가 누구에게 있는가 비교확인
	static boolean same(int N, int[] curr, int[] result) {
		for(int i = 0; i < N; i++) {
			if(curr[i] != result[i]) return false;
		}
	
		return true;
	}

}