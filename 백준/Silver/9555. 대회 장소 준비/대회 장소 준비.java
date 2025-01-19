import java.io.*;
import java.util.*;

public class Main {
	static int R, C, arr[][];
	static int[][] dir = {{-1, -1, -1, 0, 0, 1, 1, 1}, {-1, 0, 1, 1, -1, -1, 0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		HashSet<Integer> set;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			set = new HashSet<>();
			
			arr = new int[R][C];
			for(int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < C; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(set.contains(arr[r][c]) || arr[r][c] == -1) continue;

					for(int d = 0; d < 8; d++) {
						int nr = r + dir[0][d];
						int nc = c + dir[1][d];

						if(!checkBoundary(nr, nc) || arr[nr][nc] != arr[r][c]) continue;

						set.add(arr[r][c]);
						break;
					}
				}
			}	
			sb.append(set.size()).append('\n');
		}
		System.out.println(sb);
	}
	
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}