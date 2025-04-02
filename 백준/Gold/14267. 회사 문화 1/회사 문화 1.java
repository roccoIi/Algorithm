import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer[];
	static ArrayList<Integer> boss[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        // N: 총 직원수, M: 칭찬 횟수
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[N+1];
        
        // 각 보스에게 속해있는 부하직원들을 리스트의 요소로 가진다.
        boss = new ArrayList[N+1];
        initArrayList(boss);
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	int idx = Integer.parseInt(st.nextToken());
        	
        	if(idx == -1) continue;
        	boss[idx].add(i);
        }
        
        // 해당 직원(idx)에게 칭찬점수(score)를 부여한다. 
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int idx = Integer.parseInt(st.nextToken());
        	int score = Integer.parseInt(st.nextToken());
        	
        	answer[idx] += score;
        }
        
        // 부하들에게 칭찬릴레이를 이어간다.
        complimentRelay(1);
        
        // 정답 출력
        for(int i = 1; i <= N; i++) {
        	sb.append(answer[i]).append(" ");
        }
        
        System.out.println(sb);
    }
    
    // 부하들을 한명씩 돌며 칭찬점수를 누적하고, 해당 부하직원으로 dfs를 타고간다.
    static void complimentRelay(int curr) {
    	for(int next : boss[curr]) {
    		answer[next] += answer[curr];
    		complimentRelay(next);
    	}
    }
    
    // 리스트배열 초기화
    static void initArrayList(ArrayList<Integer>[] list) {
    	for(int i = 0; i < list.length; i++) {
    		list[i] = new ArrayList<>();
    	}
    }
}