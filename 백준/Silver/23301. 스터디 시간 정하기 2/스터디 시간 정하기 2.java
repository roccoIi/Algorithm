import java.io.*;
import java.util.*;

public class Main {
	static int N, T, time[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		time = new int[1001];
		int maxTime = -1;
		
		for(int i = 0; i < N; i++) {
			int times = Integer.parseInt(br.readLine());
			for(int t = 0; t < times; t++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				for(int j = start; j < end; j++) {
					time[j]++;
				}
				maxTime = maxTime < end ? end : maxTime;
			}
		}
		
		int tmpTime = 0;
		for(int i = 0; i < T; i++) {
			tmpTime += time[i];
		}
		
		int maxSat = tmpTime;
		int start = 0;
		int end = T;
		int answerStart = start;
		int answerEnd = end;
		
		
		while(end < maxTime) {
			tmpTime -= time[start++];
			tmpTime += time[end++];
			
			if(maxSat < tmpTime) {
				maxSat = tmpTime;
				answerStart = start;
				answerEnd = end;
			}	
		}
		
		sb.append(answerStart).append(" ").append(answerEnd);
		System.out.println(sb);
	}
}