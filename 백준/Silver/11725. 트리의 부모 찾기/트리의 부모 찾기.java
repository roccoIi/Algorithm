import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] edge = new int[N-1][2];
		for(int r = 0; r < N-1; r++) {
			int[] temp = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			for(int c = 0; c < 2; c++) {
				edge[r][c] = temp[c];
			}
		}
		
		int[] answer = mainSolution(N, edge);
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i < answer.length; i++) {
			sb.append(answer[i]).append('\n');
		}
		System.out.println(sb);
	}
	
	
	static ArrayList<Integer>[] list;
	static int[] mainSolution(int N, int[][] edge) {
		list = new ArrayList[N+1];
		
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int r = 0; r < edge.length; r++) {
			int a = edge[r][0];
			int b = edge[r][1];
			
			list[a].add(b);
			list[b].add(a);
		}
		
		int[] answer = findParents(N, 1);
		
		return answer;
	}
	
	static int[] findParents(int N, int root) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(root);
		
		int[] parents = new int[N+1];
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for(int next : list[curr]) {
				if(parents[next] != 0) continue;
				
				parents[next] = curr;
				q.add(next);
			}
		}
		
		return parents;
	}
}