import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		TreeSet<Long> set = new TreeSet<>((s1, s2) -> Long.compare(s2, s1));
		set.add(N);
		
		while(!set.isEmpty()) {
			// 자료구조 Set을 iterator로 변환시킨다.
			Iterator<Long> iterator = set.iterator();
			
			long num = iterator.next();
			iterator.remove(); // 이제 필요없으니 삭제
			
			// 목표로 하는 숫자와 일치한다면 YES반환 후 while문 종료;
			if(num == M) {
				sb.append("YES");
				break;
			}
			
			// 아직 목표 숫자보다 크다면 나눠서 다시 set에 넣는다.
			// 목표 숫자보다 작아졌다면 어차피 나눠도 목표숫자 도달 못하니 버린다.
			if(num > M) {
				set.add(num / 2);
				if(num % 2 == 1) set.add(num / 2 + 1);
			}
		}
		
		if(sb.length() == 0) sb.append("NO");
		System.out.println(sb);
	}
}