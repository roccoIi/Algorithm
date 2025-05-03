import java.util.*;
import java.io.*;

public class Main {
	static int N, M, dist[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        dist = new int[N+1][N+1];
        for(int r = 0; r < M; r++) {
        	st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		
    		// 정방향
    		dist[start][end] = 1;        		
    		// 역방향
    		dist[end][start] = -1;
        	
        }
        
        for(int k = 1; k <= N; k++) {
        	for(int i = 1; i <= N; i++) {
        		for(int j = 1; j <= N; j++) {     			
        			// 둘 다 정방향일때는 길이 이어져있다.
        			if(dist[i][k] == 1 && dist[k][j] == 1) dist[i][j] = 1;
        			
        			// 둘 다 역방향일때도 길이 이어져있다.
        			if(dist[j][k] == -1 && dist[k][i] == -1) dist[j][i] = -1;	
        		}
        	}
        }
        
        for(int r = 1; r <= N; r++) {
        	int answer = 0;
        	for(int c = 1; c <= N; c++) {
        		if(dist[r][c] != 0) answer++;
        	}
        	sb.append((N-1) - answer).append('\n');
        }
        
        System.out.println(sb);
	}
}