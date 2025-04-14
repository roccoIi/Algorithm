import java.io.*;
import java.util.*;

public class Main {
	static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        
        ArrayList<Integer> list = getPrime(N);

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;
        while(left <= right && right < list.size()) {
        	if(sum < N) {
        		sum += list.get(right++);
        	} else {
        		if(sum == N) count++;
        		sum -= list.get(left++);
        	}
        }
        
        System.out.println(count);
    }  
    
    static ArrayList<Integer> getPrime(int N){
    	ArrayList<Integer> list = new ArrayList<>();
    	boolean[] visited = new boolean[N+1];
    	visited[1] = true;
    	
    	for(int i = 2; i*i <= N; i++) {
    		if(!visited[i]) {
    			for(int j = i * 2; j <= N; j += i) {
    				visited[j] = true;
    			}
    		}
    	}
    	
    	for(int i = 2; i <= N; i++) {
    		if(!visited[i]) list.add(i);
    	}
    	
    	list.add(0);
    	return list;
    }
}