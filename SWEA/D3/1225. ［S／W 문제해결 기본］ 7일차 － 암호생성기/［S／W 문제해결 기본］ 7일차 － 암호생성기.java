import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 10; i++) {
			int mn = 1;
			int count = 0;
			int testCase = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> queue = new LinkedList<>();
			
			// 제시되는 숫자 큐에 저장
			for(int j = 0; j < 8; j++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			// 큐 속의 숫자가 0이 될때까지 while문 반복
			while(true) {
				count ++;
				int num = queue.poll() - mn;
				if(num <= 0) { // 0과 같아지거나 낮아지면 0을 큐에 넣고 종류
					queue.offer(0);
					break;
				}
				queue.offer(num);
				if(count == 5) { // 5회 반복하면 1사이클 돌았으니 count와 마이너스 초기화 0
					count = 0;
					mn = 0;
				}
				mn++;
				
			}
			
			// 출력
			System.out.print("#" + testCase);
			while(!queue.isEmpty()) {
				System.out.print(" " + queue.poll());
			}
			System.out.println();
		}
	}
}