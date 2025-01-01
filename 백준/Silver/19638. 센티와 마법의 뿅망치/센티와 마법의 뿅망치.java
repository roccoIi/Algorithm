import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long H = Long.parseLong(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int hammerCnt = 0;
		
		// 거인들 키는 우선순위 큐로 받는다.
		PriorityQueue<Long> pq = new PriorityQueue<>((s1, s2) -> Long.compare(s2, s1));
		for(int i = 0; i < N; i++) {
			pq.add(Long.parseLong(br.readLine()));
		}
		
		// 모든 망치질이 끝날때까지 거인들의 키를 비교하여 큐에 관리한다.
		while(T > hammerCnt) {
			long tallestGiant = pq.poll();
			
			if(tallestGiant >= H) {
				if(tallestGiant > 1) {
					pq.add(tallestGiant / 2);	
				} else {
					pq.add(tallestGiant);
					break;
				}
				hammerCnt++;
			} else {
				sb.append("YES").append('\n').append(hammerCnt);
				break;
			}
		}
		
		// 위에서 결론이 나지 않았을 때 제일 키 큰 거인이 센티보다 작을경우 YES, 클경우 NO 
		if(sb.length() == 0) {
			long tallestGiant = pq.poll();
			
			if(tallestGiant < H) {
				sb.append("YES").append('\n').append(hammerCnt);
			} else {
				sb.append("NO").append('\n').append(tallestGiant);
			}
		}
		
		System.out.println(sb);
	}
}