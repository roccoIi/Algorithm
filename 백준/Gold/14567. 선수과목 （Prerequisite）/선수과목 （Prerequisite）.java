import java.io.*;
import java.util.*;

public class Main {
	static int N, M, semester, arr[], answer[];
	static List<Integer>[] list;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 과목의 수
		M = Integer.parseInt(st.nextToken()); // 선수 조건의 수
		arr = new int[N+1]; // 진입차수 저장할 배열
		answer = new int[N+1]; // 정답을 저장할 배열
		list = new ArrayList[N+1]; // 과목들간의 선후관계 저장할 연결리스트
		semester = 1; // n학기
		
		// 연결리스트 배열 초기화 
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 선후관계 정보 입력
		// 입력과 동시에 진입차수 추가 (arr 배열)
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			list[first].add(second);
			arr[second]++;
		}
		
		// 진입차수가 0인 친구들을 큐에 넣고 시작점으로 삼는다.
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(arr[i] == 0) {
				q.add(i);
				answer[i] = semester; // 이 때, 최초 시작점인 과목은 1학기로 저장
			}
		}
		
		// 모든과목을 다 수강할때까지 진행
		while(!q.isEmpty()) {
			semester++;
			
			// 학기별로 구분하기 위해 qSize를 구하고 해당크기만큼 반복
			int qSize = q.size();
			for(int i = 0; i < qSize; i++) {		
				int subjects = q.poll();
				
				// 해당 과목을 들으면 수강할 수 있는 과목들의 크기만큼 반복
				for(int j = 0; j < list[subjects].size(); j++) {
					int next = list[subjects].get(j);
					
					// 만일 해당 과목의 진입차수가 0이 되었다면 큐에 넣고 해당하는 학기를 정답배열에 입력한다.
					if(--arr[next] == 0) {
						q.add(next);
						answer[next] = semester;
					}
				}
			}
		}
		
		// answer 배열을 출력한다.
		for(int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}