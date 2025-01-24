import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		String[] dpMin = new String[101];
		
		dpMin[1] = "0";
		dpMin[2] = "1";
		dpMin[3] = "7";
		dpMin[4] = "4";
		dpMin[5] = "2";
		dpMin[6] = "6";
		dpMin[7] = "8";
		
		for(int i = 8; i <= 100; i++) {
			long tmp = Long.MAX_VALUE;
			for(int j = 2; j <= i/2; j++) {
				sb = new StringBuilder();
				if(dpMin[j].charAt(0) <= dpMin[i - j].charAt(0)) {		
					sb.append(dpMin[j]).append(changeNum(dpMin[i-j]));
				} else {
					sb.append(dpMin[i-j]).append(changeNum(dpMin[j]));
				}
				long num = Long.parseLong(sb.toString());
				tmp = tmp > num ? num : tmp;
			}
			dpMin[i] = Long.toString(tmp);
		}

		while(T-- > 0) {
			sb = new StringBuilder();
			int num = Integer.parseInt(br.readLine());
			
			if((num & 1) == 1) sb.append("7");
			else sb.append("1");
			
			for(int i = 1; i < num / 2; i++) {
				sb.append("1");
			}
			
			answer.append(dpMin[num]).append(" ").append(sb).append('\n');
		}
		
		System.out.println(answer);
	}
	
	static String changeNum(String num) {
		if(num.equals("6")) return "0";
		return num;
	}
}