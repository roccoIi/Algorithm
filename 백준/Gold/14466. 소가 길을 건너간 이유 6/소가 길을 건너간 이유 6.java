import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, K, R, group[][];
	static boolean[][] visited;
	static ArrayList<Node>[][] list;
	static int[][] dir = {{1, -1, 0, 0}, {0, 0, -1, 1}}; // 상하좌우
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1][N+1];
        group = new int[N+1][N+1];
        
        list = new ArrayList[N+1][N+1];
        for(int r = 1; r <= N; r++) {
        	for(int c = 1; c <= N; c++) {
        		list[r][c] = new ArrayList<>();
        	}
        }
        
        for(int i = 0; i < R; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int nr = Integer.parseInt(st.nextToken());
        	int nc = Integer.parseInt(st.nextToken());
        	list[r][c].add(new Node(nr, nc));
        	list[nr][nc].add(new Node(r, c));
        }
  
        int idx = 1;
        for(int r = 1; r <= N; r++) {
        	for(int c = 1; c <= N; c++) {
        		if(visited[r][c]) continue;
        		
        		findFarm(new Node(r, c), idx++);
        	}
        }
        
        int[] groupNum = new int[idx + 1];
        for(int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	groupNum[group[r][c]]++;
        }
        
        int answer = 0;
        for(int i = 1; i < idx; i++) {
        	for(int j = i+1; j <= idx; j++) {
        		answer += groupNum[i] * groupNum[j];
        	}
        }
        
        System.out.println(answer); 
    }
    
    static void findFarm(Node start, int idx) {
    	Queue<Node> q = new ArrayDeque<>();
    	visited[start.r][start.c] = true;
    	group[start.r][start.c] = idx;
    	q.add(start);
    	
    	while(!q.isEmpty()) {
    		Node curr = q.poll();
    		
    		for(int d = 0; d < 4; d++) {
    			int nr = curr.r + dir[0][d];
    			int nc = curr.c + dir[1][d];
    			
    			if(checkBoundary(nr, nc) || visited[nr][nc] || isRoad(curr, nr, nc)) continue;
    			visited[nr][nc] = true;
    			
    			group[nr][nc] = idx;
    			q.add(new Node(nr, nc));
    		}
    	}
    }
    
    static boolean isRoad(Node node, int nr, int nc) {
    	for(Node curr : list[node.r][node.c]) {
    		if(curr.r == nr && curr.c == nc) return true;
    	}
    	return false;
    }
    
    static boolean checkBoundary(int r, int c) {
    	return r <= 0 || r > N || c <= 0 || c > N;
    }
 
}