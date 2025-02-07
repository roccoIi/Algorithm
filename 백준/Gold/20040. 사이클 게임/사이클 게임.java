import java.io.*;
import java.util.*;

public class Main {
	static int N, M, parents[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(findSet(x) == findSet(y)) {
				System.out.println(i + 1);
				return;
			} else {
				unionSet(x, y);
			}
		}
		System.out.println(0);
	}
	
	static int findSet(int x) {
		if(parents[x] == x) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		parents[y] = x;
	}
}