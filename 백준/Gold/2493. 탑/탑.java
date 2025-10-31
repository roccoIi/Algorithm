import java.io.*;
import java.util.*;


public class Main {
	static class Tower{
		int idx, h;
		
		Tower(int idx, int h){
			this.idx = idx;
			this.h = h;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Tower> towers = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if(towers.isEmpty()) {
				sb.append(0).append(" ");
				towers.add(new Tower(i, height));
			} else {
				while(!towers.isEmpty() && towers.peek().h <= height ) {
					towers.pop();
				}
				
				if(towers.isEmpty()) sb.append(0).append(" ");
				else sb.append(towers.peek().idx).append(" ");
				
				towers.add(new Tower(i, height));
			}
		}
		
		System.out.println(sb);
	}
	
	static int[] mainSolution(int[][] map) {
		
		return new int[] {};
	}
}