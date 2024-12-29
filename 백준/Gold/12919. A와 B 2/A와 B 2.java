import java.io.*;
import java.util.*;

public class Main {
	static String now, goal;
	static int maxA, maxB;
	static StringBuilder sb = new StringBuilder();
	static HashSet<String> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		now = br.readLine();
		goal = br.readLine();
		
		DFS(goal);
		if(sb.length() == 0) sb.append("0");
		
		System.out.println(sb);
	}
	
	static void DFS(String start) {			
		if(sb.length() != 0 || start.length() < now.length()) return;
		
		if(start.equals(now)) {
			sb.append("1");
			return;
		}
		
		if(start.charAt(start.length()-1) == 'A') {
			DFS(deleteA(start));
		}
		
		if(start.charAt(0) == 'B') {
			DFS(deleteB(start));
		}
	}
	
	// 뒤에 문자열 추가하기(A, B 추가할때 사용)
	static String deleteA(String seed) {
		return seed.substring(0, seed.length()-1);
	}
	
	// 문자열 추가 후 뒤집기 (2번 방법)
	static String deleteB(String seed) {
		return new StringBuilder(seed.substring(1)).reverse().toString();
	}
}