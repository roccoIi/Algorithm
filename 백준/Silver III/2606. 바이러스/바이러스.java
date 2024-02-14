import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int computers, count;
	static int[] check;
	static int[][] lineArr;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		computers = Integer.parseInt(br.readLine());
		int line = Integer.parseInt(br.readLine());
		
		
		lineArr = new int[computers+1][computers+1]; // 인접행렬
		check = new int[computers+1]; // 방문기록
		count = 0; // 총 감염된 컴퓨터 수
		
		for(int i = 0; i < line; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			lineArr[a][b] = lineArr[b][a] = 1; // 인접 행렬 입력
		}
		
		bfs(1);
		System.out.println(count);
		
	}
	
	public static void bfs(int num) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(num);
		check[num]++; // 방문체크
		int a;
		while(!q.isEmpty()) {
			a = q.poll();
			for(int i = 1; i <= computers; i++) {
				if(lineArr[a][i] == 1 && check[i] == 0) {
					q.offer(i); // 새로 방문할 곳 큐에 넣고
					check[i]++; // 방문체크 하고
					count++; // 감염+1
				}
			}
		}
	}
}