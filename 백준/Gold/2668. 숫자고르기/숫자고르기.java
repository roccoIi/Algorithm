import java.io.*;
import java.util.*;

public class Main {
	static int N, maxNum, arr[];
	static boolean visited[], answer[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        maxNum = -1;
        
        arr = new int[N+1];
        answer = new boolean[N+1];
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        
        
        int totalCnt = 0;
        // 1부터 N(100)까지 순환하며 사이클이 밣생하는지 확인
        for(int i = 1; i <= N; i++) {
        	
        	// 이미 방문했다면 pass
        	if(answer[i]) continue;
        	
        	// index와 숫자가 같을경우 pass
        	if(arr[i] == i) {
        		answer[i] = true;
        		totalCnt++;
        		continue;
        	}
        	
        	ArrayList<Integer> list = findRoot(i);
        	if(list == null) continue;
        	
        	totalCnt += list.size();
        	
        	for(int num : list) {
        		answer[num] = true;
        	}
        }
        
        sb.append(totalCnt);
        for(int i = 1; i <= N; i++) {
        	if(answer[i]) sb.append('\n').append(i);
        }
        
        System.out.println(sb);
    }
    
    static ArrayList<Integer> findRoot(int root) {
    	ArrayList<Integer> list = new ArrayList<>();
    	
    	Queue<Integer> q = new ArrayDeque<>();
    	visited = new boolean[N+1];
    	visited[root] = true;
    	list.add(root);
    	q.add(root);
    	
    	
    	while(true) {
    		int curr = arr[q.poll()];
    		
    		if(visited[curr]) {
    			if(curr == root) {
    				return list;
    			} else {
    				return null;
    			}
    		} else {
    			visited[curr] = true;
    			q.add(curr);
    			list.add(curr);
    		}
    	}
    	
    }
}