import java.io.*;
import java.util.*;

public class Main {
	static int count, N;
	static int[] arr = {1, 5, 10, 50};
	static boolean[] visited = new boolean[1001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		

		N = Integer.parseInt(br.readLine());
		count = 0;
		
		dfs(0, 0, 0);
		System.out.println(count);
	}
	
	static void dfs(int start, int idx, int num) {
		if(idx == N) {
			if(visited[num]) return;
			visited[num] = true;
			count++;
			return;
		}
		
		for(int i = start; i < 4; i++) {
			dfs(i, idx + 1, num + arr[i]);
		}
	}
}