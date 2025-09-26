import java.util.*;
import java.io.*;

public class Main {
	static int L, C;
	static char arr[], code[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		dfs(0, 0, new char[L]);
		
		System.out.println(sb);
	}
	
	public static void dfs(int start, int depth, char[] code) {
		if(depth == L) {
			if(isValid(code)) {
				sb.append(makeCode(code)).append('\n');
			}
			return;
		}
		
		for(int i = start; i < C; i++) {
			code[depth] = arr[i];
			dfs(i+1, depth+1, code);
		}
	}
	
	public static boolean isValid(char[] code) {
		int consonant = 0; // 자음
		int vowel = 0; // 모음
		
		for(int i = 0; i < L; i++) {
			if(code[i] == 'a' || code[i] == 'e' || code[i] == 'i' || code[i] == 'o' || code[i] == 'u') {
				vowel++;
			} else {
				consonant++;
			}
		}
		
		if(consonant >= 2 && vowel >=1) return true;
		else return false;
	}
	
	public static String makeCode(char[] code) {
		String str = "";
		for(int i = 0; i < L; i++) {
			str += code[i];
		}
		
		return str;
	}
}