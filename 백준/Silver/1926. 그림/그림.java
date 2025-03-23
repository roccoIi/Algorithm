import java.io.*;
import java.util.*;

public class Main {
	static int R, C, map[][], answer, maxSize;
	static boolean visited[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];
        
        for(int r = 0; r < R; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < C; c++) {
        		map[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(int r = 0; r < R; r++) {
        	for(int c = 0; c < C; c++) {
        		if(!visited[r][c] && map[r][c] == 1) maxSize = Math.max(maxSize, findBFS(r, c));
        	}
        }
        
        sb.append(answer).append('\n').append(maxSize);
        System.out.println(sb);
    }
    
    static int findBFS(int r, int c) {
    	Queue<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {r, c});
    	visited[r][c] = true;
    	int areaSize = 1;
    	
    	while(!q.isEmpty()) {
    		int[] curr = q.poll();
    		
    		for(int d = 0; d < 4; d++) {
    			int nr = curr[0] + dir[0][d];
    			int nc = curr[1] + dir[1][d];
    			
    			if(checkBoundary(nr, nc) || visited[nr][nc] || map[nr][nc] != 1) continue;
    			visited[nr][nc] = true;
    			
    			q.add(new int[] {nr, nc});
    			areaSize++;
    		}
    	}
    	
    	answer++;
    	return areaSize;
    }
    
    static boolean checkBoundary(int r, int c) {
    	return r < 0 || r >= R || c < 0 || c >= C;
    }
}