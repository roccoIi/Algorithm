import java.io.*;
import java.util.*;

public class Main {
	static int M, target;
	static ArrayList<Integer> list = new ArrayList<>();
	static String[] arr = {"", "Messi ", "Messi Gimossi "};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		M = Integer.parseInt(br.readLine());
		
		// Messi의 글자수, Messi Gimossi의 글자수
		list.add(0);
		list.add(5);
		list.add(13);
		
		target = 1;
		
		// arr[2]의 길이 13, arr[1]의 길이 5, 이를 조합한 글자는 3번째 인덱스를 가진다.
		getLength(3, 13, 5);
		System.out.println(fibo(target, M));
	}
	
	static void getLength(int idx, int before, int be_before) {
		list.add(before + be_before + 1);
		
		if(before + be_before + 1 >= M) {
			target = idx;
			return;
		}
		getLength(idx + 1, before + be_before + 1, before);
	}
	
	static String fibo(int idx, int m) {
		
		// idx 2 이하일 경우 기본으로 주어진 배열에서 정답을 받아올 수 있다.
		// 단, 만약 해당 위치가 공백일 경우 "Messi Messi Gimossi"를 반환한다.
		if(idx <= 2) {
			return arr[idx].charAt(m-1) == ' ' ? "Messi Messi Gimossi" : Character.toString(arr[idx].charAt(m-1));
		}
		
		// 만일 목표(m)로 하는 글자수가 바로 직전 index의 글자수보다 크다면, n-2의 인덱스로 넘어갔다는 의미이다.
		// 다음 글자의 위치는 "목표 - 직전 단어의 글자수 - 1(index는 0부터 시작)" 을 넘겨준다.
		if(list.get(idx - 1) + 1 < m) {
			return fibo(idx - 2, m - list.get(idx - 1) - 1);
		}
		
		// 목표 글자보다 작거나 같다면,n-1의 인덱스로 넘어갔다는 의미이다.
		// 다음 인덱스는 현재와 같다.
		return fibo(idx -1 , m);
	}
	
}