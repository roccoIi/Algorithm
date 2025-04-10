import java.io.*;
import java.util.*;

public class Main {
	static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N*2; i+=2) {
        	for(int j = 0; j < i/2; j++) {
        		sb.append(" ");
    		}
        	
        	for(int j = 0; j < 2*N-i; j++) {
        		sb.append("*");
        	}
        	
        	sb.append('\n');
        }
        
        System.out.println(sb);
    }
    
}