import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] price = new int[2]; // 0: 영식(Y), 1: 민식(M)
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	int time = Integer.parseInt(st.nextToken());
        	price[0] += (time / 30) + 1;
        	price[1] += (time / 60) + 1;
        }
        
        int yong = price[0] * 10;
        int min = price[1] * 15;
        
        if(yong < min) sb.append("Y ").append(yong);
        else if(yong > min) sb.append("M ").append(min);
        else sb.append("Y M ").append(yong);
        
        System.out.println(sb);
    } 
}