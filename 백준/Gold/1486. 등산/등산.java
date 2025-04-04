import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c, dist;
		
		Node(int r, int c, int dist){
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	static int N, M, T, D, map[][], dist[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        // 'A'-'Z': 0 - 26, 'a'-'z': 26 - 51
        for(int r = 0; r < N; r++) {
        	String str = br.readLine();
        	for(int c = 0; c < M; c++) {
        		char tmp = str.charAt(c);
        		map[r][c] = tmp - 'A' < 26 ? tmp - 'A' : tmp - 'a' + 26;
        	}
        }
        
        // total: (0, 0)부터 시작해서 각 지점까지 왕복하는데 소요되는 시간
        int[][] total = new int[N][M];
        dist = new int[N][M];
        for(int i = 0; i < 2; i++) {
        	// 1) dist배열 초기화 (987654321)
        	initDist(dist);
        	
        	// 2) 각 좌표에 도달하는 최소시간 계산 ([i] 0: (0, 0)에서 각 좌표, 1: 각 좌표에서 (0, 0)까지) 
        	getTime(0, 0, i);
        	
        	// 3) total에 각 상황별 계산된 dist값을 누적하여 더한다.
        	addDist(total);
        }
        
        // 높이가 가장 높으면서도 D시간을 초과하지 않는 경우에만 갱신
        int maxNum = 0;
    	for(int r = 0; r < total.length; r++) {
    		for(int c = 0; c < total[0].length; c++) {
    			if(total[r][c] <= D && maxNum < map[r][c]) maxNum = map[r][c];
    		}
    	}
        
    	// 출력
        System.out.print(maxNum);
    }
    
    
    // 다익스트라를 통해 각 지점까지의 최소시간을 구한다.
    static void getTime(int r, int c, int reverse) {
    	PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.dist, s2.dist));
    	boolean[][] visited = new boolean[N][M];
    	pq.add(new Node(r, c, 0));
    	
    	while(!pq.isEmpty()) {
    		Node curr = pq.poll();
    		
    		if(visited[curr.r][curr.c]) continue;
    		visited[curr.r][curr.c] = true; 
    		
    		for(int d = 0; d < 4; d++) {
    			int nr = curr.r + dir[0][d];
    			int nc = curr.c + dir[1][d];
    			
    			if(!checkBoundary(nr, nc) || !isPossible(curr, nr, nc)) continue;
    			
    			int gap = reverse == 1 ? reverseCalculateGap(curr, nr, nc) : calculateGap(curr, nr, nc);
    			if(dist[nr][nc] > dist[curr.r][curr.c] + gap) {
    				dist[nr][nc] = dist[curr.r][curr.c] + gap;
    				pq.add(new Node(nr, nc, dist[nr][nc]));
    			}
    		}
    	}
    }
    
    // total배열에 누적
    static void addDist(int[][] total) {
    	for(int r = 0; r < total.length; r++) {
    		for(int c = 0; c < total[0].length; c++) {
    			total[r][c] += dist[r][c];
    		}
    	}
    }
    
    // (0, 0)에서 각 좌표까지의 거리 
    static int calculateGap(Node curr, int nr, int nc) {
    	if(map[curr.r][curr.c] >= map[nr][nc]) return 1;
    	else return (int)Math.pow(map[nr][nc] - map[curr.r][curr.c], 2);
    }
    
    // 각 좌표에서 (0, 0)까지의 거리
    static int reverseCalculateGap(Node curr, int nr, int nc) {
    	if(map[curr.r][curr.c] <= map[nr][nc]) return 1;
    	else return (int)Math.pow(map[nr][nc] - map[curr.r][curr.c], 2);
    }
    
    // dist배열 초기화 (987654321)
    static void initDist(int[][] dist) {
    	for(int[] arr : dist) {
    		Arrays.fill(arr, INF);
    	}
    	dist[0][0] = 0;
    }
    
    // T이상의 차이에는 등산이 불가능하다.
    static boolean isPossible(Node curr, int nr, int nc) {
    	return Math.abs(map[curr.r][curr.c]- map[nr][nc]) <= T; 
    }
    
    // 현재 좌표가 범위를 벗어났는지 체크
    static boolean checkBoundary(int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c < M;
    }
}