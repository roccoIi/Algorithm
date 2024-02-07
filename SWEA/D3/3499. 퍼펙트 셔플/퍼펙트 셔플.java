import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		int testCase = Integer.parseInt(br.readLine());
		for(int t = 1; t <= testCase; t++) {
			String answer = "";
			double deckCount = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<String> deck1 = new LinkedList<>();
			Queue<String> deck2 = new LinkedList<>();
			boolean flag = true;
			
			// 2개의 큐에 절반 나눠서 할당
			for(int i = 1; i <= deckCount; i++) {
				if(i <= deckCount/2+0.5) {
					deck1.offer(st.nextToken());
				} else {
					deck2.offer(st.nextToken());
				}
			}
			
			// 번갈아가면서 문자열 저장
			for(int i = 0; i < deckCount; i++){
				if(flag){
					flag = false;
					answer += deck1.poll() + " ";
				} else{
					flag = true;
					answer += deck2.poll() + " ";
				}
			}
			
			//출력
			System.out.printf("#%d %s\n", t, answer);
		}
	}
}