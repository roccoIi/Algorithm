import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int dir[][] = {{0, 0, 1, -1}, {1, -1, 0, 0}};
	static boolean visited[][];
	static double answer, percents[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        percents = new double[4];
        for(int i = 0; i < 4; i++) {
        	percents[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }
        
        // 방문배열 및 초기 위치 방문체크
        visited = new boolean[N*2+1][N*2+1];
        visited[N][N] = true;
        
        dfs(N, N, 0, 1.0);
        System.out.println(answer);
    }
    
    static void dfs(int r, int c, int count, double result) {
    	if(count == N) {
    		answer += result;
    		return;
    	}
    	
    	for(int d = 0; d < 4; d++) {
    		// 확률이 아예 없으면 넘긴다
    		if(percents[d] == 0.0) continue;
    		
    		int nr = r + dir[0][d];
    		int nc = c + dir[1][d];
    		
    		// 이미 방문했거나 범위를 벗어난 지역이라면 넘긴다
    		if(checkBoundary(nr, nc) || visited[nr][nc]) continue;
    		
    		visited[nr][nc] = true;
    		dfs(nr, nc, count + 1, result * percents[d]);
    		visited[nr][nc] = false;	
    	}
    }
    
    // 범위를 벗어났을 경우 true 반환
    static boolean checkBoundary(int r, int c) {
    	return r < 0 || r > N*2 || c < 0 || c > N*2;
    }
    
}