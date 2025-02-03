import java.io.*;
import java.util.*;

public class Main {
	static int T, N, arr[];
	static boolean[] visited, isGroup;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N+1];
			isGroup = new boolean[N+1];
			arr = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			list = new ArrayList<>();
			for(int i = 1; i <= N; i++) {
				if(visited[i] || visited[arr[i]]) continue;	
				dfs(i);
				list.clear();
			}
			
			int answer = 0;
			for(int i = 1; i <= N; i++) {
				if(!isGroup[i]) answer++; 
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int curr) {
        visited[curr] = true;
        list.add(curr);  // 팀 후보 리스트에 추가

        int next = arr[curr];

        if (visited[next]) {  // 이미 방문한 학생이면 팀에 속하는지 체크
            // 사이클이 존재하는지 확인
            if (list.contains(next)) {
                // next가 팀에 속하는 경우
                for (int i = list.size() - 1; i >= 0; i--) {
                    isGroup[list.get(i)] = true;  // 팀에 속한 학생으로 표시
                    if (list.get(i) == next) break;
                }
            }
            return;
        }

        // 계속 DFS를 진행
        dfs(next);
    }
}