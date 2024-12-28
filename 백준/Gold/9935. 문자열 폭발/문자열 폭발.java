import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String word = br.readLine();
		String target = br.readLine();
		
		for(int i = 0; i < word.length(); i++) {
			sb.append(word.charAt(i));
			
			if(sb.length() >= target.length() 
					&& sb.substring(sb.length() - target.length()).equals(target)) {
				sb.delete(sb.length() - target.length(), sb.length());
			}
		}
		
		if(sb.length() == 0) {
			sb.append("FRULA");
		}
		
		System.out.println(sb);
	}
}