import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static HashSet<Integer> set = new HashSet<>();
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			set = new HashSet<>(); // 중복되는 숫자 캐싱
			list = new ArrayList<>(); // 만들어질 수 있는 숫자를 저장하기 위한 list
			String[] str = br.readLine().split("");
			visited = new boolean[str.length]; // dfs를 위한 방문체크배열
			for(int i = 1; i <= str.length; i++) {
				makeList(0, i, new StringBuilder(), str);
			}

			// 총 리스트에 들어가있는 숫자들은 모두 소수이다.
			answer.append(list.size()).append('\n');
		}
		
		System.out.println(answer);
	}
	
	// 에라토스테네스의 체
	static boolean isPrime(int num) {
		if(num == 1 || num == 0) return false;
		
		for(int i = 2; i * i <= num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	static void makeList(int now, int idx, StringBuilder sb, String[] str) {
		if(now == idx) {
			int num = Integer.parseInt(sb.toString());
			
			if(set.contains(num)) return;
			set.add(num);
			
			// 해당 숫자가 소수일 경우에만 list에 넣는다.
			if(isPrime(num)) list.add(num);
			return;
		}
		
		for(int i = 0; i < str.length; i++) {
			if(visited[i]) continue;
			
			sb.append(str[i]);
			visited[i] = true;
			makeList(now + 1, idx, sb, str);
			visited[i] = false;
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}