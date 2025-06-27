import java.util.*;
import java.io.*;

public class Solution {
	static HashSet<String> set;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int answer = 0;
			
			st = new StringTokenizer(br.readLine());
			set = new HashSet<>();
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			while(a-- > 0) {
				set.add(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			while(b-- > 0) {
				if(set.contains(st.nextElement())) answer++;
			}
			
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		
		System.out.println(sb);
	}	
}