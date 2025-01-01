import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		HashSet<Long> set = new HashSet<>();
		Queue<Long> q = new LinkedList<>();
		set.add(N);
		q.add(N);
		
		while(!q.isEmpty()) {
			long num = q.poll();
			
			// 목표로 하는 숫자와 일치한다면 YES반환 후 while문 종료;
			if(num == M) {
				sb.append("YES");
				break;
			}
			
			// 아직 목표 숫자보다 크다면 나눠서 다시 queue와 set에 넣는다.
			// 목표 숫자보다 작아졌다면 어차피 나눠도 목표숫자 도달 못하니 버린다.
			if(num > M) {
				long divideNum = num / 2;
				long divideNumPlusOne = num / 2 + 1;
				
				if(!set.contains(divideNum)) {
					set.add(divideNum);
					q.add(divideNum);
				}
				
				if(num % 2 == 1 && !set.contains(divideNumPlusOne)) {
					set.add(divideNumPlusOne);
					q.add(divideNumPlusOne);
				}
			}
		}
		
		if(sb.length() == 0) sb.append("NO");
		System.out.println(sb);
	}
}