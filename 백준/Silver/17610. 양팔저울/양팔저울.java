import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int totalNum = 0;
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			totalNum += arr[i];
		}
				
		// 캐시역할을 할 set, 숫자들을 저장할 list
		boolean[] visited = new boolean[totalNum + 1];
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			int listSize = list.size();
			
			for(int j = 0; j < listSize; j++) {
				int plusNum = Math.abs(arr[i] + list.get(j));
				int minusNum = Math.abs(arr[i] - list.get(j));
				
				// 더했을때와 뺐을경우 모두 진행
				addNum(visited, list, plusNum);
				addNum(visited, list, minusNum);
			}
			
			// 숫자 자기자신도 리스트에 넣는다.
			addNum(visited, list, arr[i]);
		}

		System.out.println(totalNum - list.size());
	}
	
	static void addNum(boolean[] visited, ArrayList<Integer> list, int num) {
		if(num <= 0 || visited[num]) return;
		
		visited[num] = true;
		list.add(num);
	}
}