import java.io.*;
import java.util.*;

public class Main {
	static int N, L, map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < N; c++) {
        		map[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int answer = 0;
        
        // 행 체크
        for(int r = 0; r < N; r++) {
        	boolean[] visited = new boolean[N];
        	boolean flag = false;
        	for(int c = 0; c < N-1; c++) {
        		int num = map[r][c] - map[r][c+1];
        		
        		if(num == 0) continue;
        		if(num > 1 || num < -1) {
        			flag = true;
        			break;
        		}
        		else {
        			int column = num < 0 ? c : c+1;
        			if(!isSlopeRow(r, column, map[r][column], num, visited)) {
        				flag = true;
        				break;
        			}
        		}
        	}
        	if(!flag) answer++;
        }
        
        // 열 체크
        for(int c = 0; c < N; c++) {
        	boolean[] visited = new boolean[N];
        	boolean flag = false;
        	for(int r = 0; r < N-1; r++) {
        		int num = map[r][c] - map[r+1][c];
        		
        		if(num == 0) continue;
        		if(num > 1 || num < -1) {
        			flag = true; 
        			break;
        		}
        		else {
        			int row = num < 0 ? r : r+1;
        			if(!isSlopeColumn(row, c, map[row][c], num, visited)) {
        				flag = true;
        				break;
        			}
        		}
        	}
        	if(!flag) answer++;
        }
        
        System.out.println(answer); 
    }
    
    
    static boolean isSlopeRow(int r, int c, int num, int dir, boolean[] visited) {
    	int end = c + dir*L;
    	
    	for(int i = c; i != end; i += dir) {
    		if(overBoundary(r, i) || map[r][i] != num || visited[i]) return false;
    	}
    	
    	for(int i = c; i != end; i += dir) {
    		visited[i] = true;
    	}
    	
    	return true;
    }
    
    static boolean isSlopeColumn(int r, int c, int num, int dir, boolean[] visited) {
    	int end = r + dir*L;
    	
    	for(int i = r; i != end; i += dir) {
    		if(overBoundary(i, c) || map[i][c] != num || visited[i]) return false;
    	}
    	
    	for(int i = r; i != end; i += dir) {
    		visited[i] = true;
    	}
    	
    	return true;
    }

    static boolean overBoundary(int r, int c) {
    	return r < 0 || r >= N || c < 0 || c >= N;
    }
}