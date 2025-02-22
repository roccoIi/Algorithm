import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer>[] possibleNumber, numberIndex;
	static int answer[];
	static boolean numberCheck[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		possibleNumber = new ArrayList[N+1];
		numberIndex = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			numberIndex[i] = new ArrayList<>();
			possibleNumber[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());
			for(int j = num - 1; j <= num + 1; j++) {
				if(j <= 0 || j > N) continue;
				possibleNumber[i].add(j);
				numberIndex[j].add(i);
			}
		}
		
		answer = new int[N+1];
		numberCheck = new boolean[N+1];
		for(int i = N; i > 0; i--) {
			if(numberCheck[i]) continue;
			numberCheck[i] = true;

			// 현재 탐색중인 숫자의 위치를 찾아 확정짓는다.
			answer[getIndex(i)] = i;

			// 할당한 숫자는 후보에서 제거한다.
			removeNumber(i);	
		}
		
		for(int i = 1; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
	
	// 8이 들어갈 수 있는 자리중 가장 뒤에 있는 자리 인덱스 번호
	static int getIndex(int num) {
		int maxNum = -1;
		for(int x : numberIndex[num]) {
			if(answer[x] != 0) continue;
			maxNum = Math.max(maxNum, x);
		}
		return maxNum;
	}
	
	// 8이 들어갈 수 있는 위치들에서 8을 제거한다.
	static void removeNumber(int num) {
		// x : 1 / 3 (8은 1번과 3번 위치에 들어갈 수 있었다.)
		for(int x : numberIndex[num]) {
			for(int y = 0; y < possibleNumber[x].size(); y++) {
				if(possibleNumber[x].get(y) == num) {
					possibleNumber[x].remove(y);
					break;
				}
			}
			
			// 만약 해당 위치에 이미 정해진 숫자가 있다면 1개 남아도 넘긴다.
			if(answer[x] != 0) continue;
			
			// 1번자리에서 8을 제거하면 들어갈 수 있는 숫자는 7밖에 안남는다! (size == 1)
			// 7은 1번자리에 할당하고, 7이 들어갈 수 있는 위치들에서 7을 제거한다.
			if(possibleNumber[x].size() == 1) {
				int newNum = possibleNumber[x].get(0);
				if(numberCheck[newNum]) continue;
				
				answer[x] = newNum;
				numberCheck[newNum] = true;
				removeNumber(newNum);
			}
		}
	}
}