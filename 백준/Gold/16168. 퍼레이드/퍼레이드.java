import java.io.*;
import java.util.*;

public class Main {
	static int V, E, parents[], degree[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
		
		degree = new int[V+1];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			unionSet(x, y);
			degree[x]++;
			degree[y]++;
		}
		
		
		int oddCnt = 0;
		for(int i = 1; i <= V; i++) {
			if((degree[i] & 1) == 1) oddCnt++;
			if(findSet(i) != findSet(1)) {
				System.out.println("NO");
				return;
			}
		}
		
		if(oddCnt == 0 || oddCnt == 2) System.out.println("YES");
		else System.out.println("NO");
	}
	
	static int findSet(int x) {
		if(parents[x] == x) return x;
		else return parents[x] = findSet(parents[x]);
	}
	
	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		parents[y] = x;
	}
}