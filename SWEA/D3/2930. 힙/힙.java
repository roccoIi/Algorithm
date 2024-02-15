import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] heap;
	static int heapSize, ch, p, tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; StringBuilder sb;
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= testCase; t++) {
			heapSize = 0;
			// 출력문 준비
			sb = new StringBuilder();
			sb.append("#").append(t);
			
			// 총 진행되는 사이클 입력받기
			int N = Integer.parseInt(br.readLine());
			heap = new int[N+1]; // 전부 입력만 있다는 가정하에 배열은 N+1로 생성
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				if(Integer.parseInt(st.nextToken()) == 1) { // 1일경우 heapPush
					heapPush(Integer.parseInt(st.nextToken()));
				} else { // 2일 경우 heapPop
					if(heapSize == 0) {
						sb.append(" ").append(-1);
					} else {
						sb.append(" ").append(heapPop());
					}
				}
			}
			System.out.println(sb);
		}
	}
	//swap 메서드
	static void swap(int a, int b) {
		tmp = heap[a];
		heap[a] = heap[b];
		heap[b] = tmp;
	}
	
	// 입력 메서드
	static void heapPush(int num) {
		heap[++heapSize] = num;
		
		ch = heapSize;
		p = heapSize / 2;
		
		while(p >= 1 && heap[p]<heap[ch]) {
			swap(p, ch);
			
			ch = p;
			p = ch / 2;
		}
	}
	
	// 출력 메서드
	static int heapPop() {
		int root = heap[1]; // 루트노드가 최대값
		heap[1] = heap[heapSize--];
		
		p = 1;
		ch = p * 2;
		// 해당 ch는 왼쪽자식(2의 배수)만 지칭한다.
		// 아래 while문에서 ch를 판단하지 않은채 진행한다면 오른쪽자식의 경우의수는 무시된다.
		//while(ch <= heapSize && heap[p] < heap[ch]) => 오류 발생
		while(ch <= heapSize) {
			if(ch + 1 <= heapSize && heap[ch] < heap[ch+1]) {
				ch++;
			}
			if (heap[p] < heap[ch]) {
				swap(p,ch);	
			}
			p = ch;
			ch = p * 2;	
		}
		return root; // (전)루트노드 출력하기
	}
}