import java.io.*;
import java.util.*;

public class Main {
	static int answer, map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = 4;
		
   here:while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map = new int[6][3];
			answer = 0;
			
			for(int r = 0; r < 6; r++) {
				int teamCount = 0;
				for(int c = 0; c < 3; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					teamCount += map[r][c];
				}
				if(teamCount != 5) {
					sb.append(answer).append(" ");
					continue here;
				}
			}
			
			match(0, 1);
			sb.append(answer).append(" ");
		}
	
		System.out.println(sb);
	}
	
	static void match(int curr, int next) {
		if(answer == 1) {
			return;
		}
		
		if(curr == 5) {
			answer = 1;
			return;
		}
		// 0: 승리, 1: 무승부, 2: 패배
		// curr 팀이 승리
		if(map[curr][0] > 0 && map[next][2] > 0) {
			map[curr][0]--;
			map[next][2]--;
			if(next == 5) {
				match(curr + 1, curr + 2);
			} else {
				match(curr, next + 1);
			}
			map[curr][0]++;
			map[next][2]++;
		}
		
		// curr 팀이 무승부
		if(map[curr][1] > 0 && map[next][1] > 0) {
			map[curr][1]--;
			map[next][1]--;
			if(next == 5) {
				match(curr + 1, curr + 2);
			} else {
				match(curr, next + 1);
			}
			map[curr][1]++;
			map[next][1]++;
		}
		
		// curr 팀이 패배
		if(map[curr][2] > 0 && map[next][0] > 0) {
			map[curr][2]--;
			map[next][0]--;
			if(next == 5) {
				match(curr + 1, curr + 2);
			} else {
				match(curr, next + 1);
			}
			map[curr][2]++;
			map[next][0]++;
		}
		
	}
	
}