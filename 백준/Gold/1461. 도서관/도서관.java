import java.io.*;
import java.util.*;

public class Main {
	static int N, M, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = 0;
		
		
		// 양수리스트(내림차순), 음수리스트(오름차순) => 절댓값 기준 오름차순 정렬
		ArrayList<Integer> positiveList = new ArrayList<>();
		ArrayList<Integer> negativeList = new ArrayList<>();	
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num >= 0) positiveList.add(num);
			else negativeList.add(num);
			
			if(Math.abs(max) < Math.abs(num)) max = num;
		}
		
		Collections.sort(positiveList, Collections.reverseOrder());
		Collections.sort(negativeList);
		
		
		int answer = 0;
		for(int i = 0; i < positiveList.size(); i++) {	
			if(i % M == 0 && positiveList.get(i) == max) {
				answer += positiveList.get(i);
			} else if(i % M == 0) {
				answer += positiveList.get(i) * 2;
			}
		}
		
		for(int i = 0; i < negativeList.size(); i++) {
			if(i % M == 0 && negativeList.get(i) == max) {
				answer += Math.abs(negativeList.get(i));
			} else if(i % M == 0) {
				answer += Math.abs(negativeList.get(i)) * 2;
			}
		}
		
		System.out.println(answer);
	}
}