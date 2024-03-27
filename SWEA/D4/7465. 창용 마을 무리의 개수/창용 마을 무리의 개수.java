import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	static int[] p;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; StringBuilder sb = new StringBuilder();;
		
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= testCase; T++) {
			st = new StringTokenizer(br.readLine());
			sb.append("#").append(T).append(" ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			p = new int[N+1];
			
			//makeSet()
			for(int i = 1; i <= N; i++) {
				p[i] = i;
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				union(A,B);
			}
			
			// p 배열에 인덱스와 값이 같은 사람만이 그룹의 대장이다.
			// 총 그룹의 수는 대장 수만 찾으면 된다.
			int count = 0;
			for(int i = 1; i <= N; i++) {
				if(i == p[i]) count++;
			}
			
			sb.append(count).append("\n");
		}	
		System.out.println(sb);
		
	}
	
	static void union(int x, int y) {
		p[findSet(y)] = findSet(x);
	}
	
	static int findSet(int x) {
		if(x != p[x]) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
	
}