import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[];
	static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        
        int maxNum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	maxNum += arr[i];
        }
        
        visited = new boolean[N+1][maxNum + 1];
        
        findWeight(0, 0);
        
        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < T; i++) {
        	int num = Integer.parseInt(st.nextToken());
        	
        	if(num > maxNum || !visited[N][num]) sb.append("N").append(" ");
        	else sb.append("Y").append(" ");
        }
        
        System.out.println(sb);
    }
    
    static void findWeight(int idx, int weight) {
    	// 이미 방문했다면 종료
    	if(visited[idx][weight]) return;
    	visited[idx][weight] = true;
    	
    	// 끝까지 도달했다면 종료
    	if(idx == N) return;
    	
    	findWeight(idx + 1, weight); // 1. 현재 무게 유지한채로 방문체크
    	findWeight(idx + 1, weight + arr[idx + 1]); // 2. 다음 무게 더해서 방문체크
    	findWeight(idx + 1, Math.abs(weight - arr[idx + 1])); // 3. 다음 무게 뺀채로 방문체크 
    }
}