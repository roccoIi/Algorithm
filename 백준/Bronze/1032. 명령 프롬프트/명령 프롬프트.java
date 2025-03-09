import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(br.readLine());
    	String[] str = br.readLine().split("");
    	
    	boolean[] visited = new boolean[str.length];
    	for(int i = 0; i < N-1; i++) {
    		String[] temp = br.readLine().split("");
    		for(int j = 0; j < str.length; j++) {
    			if(visited[j]) continue;
    			
    			if(!str[j].equals(temp[j])) {
    				visited[j] = true;
    			}
    		}
    	}
    	
    	for(int i = 0; i < str.length; i++) {
    		if(visited[i]) sb.append("?");
    		else sb.append(str[i]);
    	}
    	
    	System.out.println(sb);
    }
}