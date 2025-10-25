import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] edge = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		int delete = Integer.parseInt(br.readLine());
		
		System.out.println(mainSolution(N, edge, delete));
	}
	
	static ArrayList<Integer>[] list;
	static int mainSolution(int N, int[] edge, int delete) {
		
		list = new ArrayList[N];
		for(int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < edge.length; i++) {
			if(edge[i] == -1) continue;
			
			list[edge[i]].add(i);
		}
		
		int count = 0;
		boolean[] isDeleted = deleteNode(N, delete);
		for(int i = 0; i < list.length; i++) {
			if(isDeleted[i]) continue;
			
			if(list[i].size() == 0 || isZero(isDeleted, i)) {
				count++;
			}
		}
		
		return count;
	}
	
	static boolean[] deleteNode(int N, int delete) {
		boolean[] isDeleted = new boolean[N];
		isDeleted[delete] = true;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(delete);
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for(int next : list[curr]) {
				isDeleted[next] = true;
				q.add(next);
			}
		}	
		return isDeleted;
	}	
	
	static boolean isZero(boolean[] isDeleted, int parent) {
		for(int num : list[parent]) {
			if(!isDeleted[num]) return false;
		}
		
		return true;
	}
}