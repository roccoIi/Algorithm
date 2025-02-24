import java.io.*;
import java.util.*;

public class Main {
	static int R, C, map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];

		for(int r = 1; r <= R; r++) {
			String[] str = br.readLine().split("");
			for(int c = 1; c <= C; c++) {
				map[r][c] = Integer.parseInt(str[c-1]);
			}
		}
		
		int maxNum = 0;
		for(int r = 1; r <= R; r++) {
			for(int c = 1; c <= C; c++) {
				if(map[r][c] == 0) continue;
				
				map[r][c] = Math.min(Math.min(map[r-1][c-1], map[r-1][c]), map[r][c-1]) + 1;
				maxNum = map[r][c] > maxNum ? map[r][c] : maxNum;
			}
		}
		
		System.out.println(maxNum * maxNum);
	}
}