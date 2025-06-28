import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			sb.append(judge(N, S, E)).append('\n');
		}
		System.out.println(sb);
	}	
	
	static int judge(int N, int S, int E) {
		if((S == 1 || S == N) && (E == 1 || E == N)) return 0;
		else if(1 < S && S < N) {
			if(Math.abs(S - E) == 1) return 1;
			else return 2;
		} else {
			return 1;
		}
	}
}