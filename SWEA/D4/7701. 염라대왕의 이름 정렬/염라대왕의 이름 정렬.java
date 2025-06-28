import java.util.*;
import java.io.*;

public class Solution {	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append('\n');
			
			int N = Integer.parseInt(br.readLine());
			TreeSet<String> set = new TreeSet<>((s1, s2) -> {
				if(s1.length() == s2.length()) {
					return s1.compareTo(s2);
				}
				return Integer.compare(s1.length(), s2.length());
			});
			
			for(int t = 0; t < N; t++) {
				set.add(br.readLine());
			}
			
			for(String str : set) {
				sb.append(str).append('\n');
			}
		}
		System.out.println(sb);
	}	
}