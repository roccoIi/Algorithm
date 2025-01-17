import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		double P = Double.parseDouble(st.nextToken());
		
		// 각 정점들의 진입차수를 저장할 배열
		int[] arr = new int[N+1];
		
		// 정점들간의 간선 정보를 입력받으면서 진입차수를 누적한다.
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())]++;
			arr[Integer.parseInt(st.nextToken())]++;
		}
		
		// 2번 정점부터 진입차수가 1일 경우 해당 정점은 리프노드이다.
		int leafCnt = 0;
		for(int i = 2; i <= N; i++) {
			if(arr[i] == 1) leafCnt++; 
		}

		System.out.println(P / leafCnt);
	}
}