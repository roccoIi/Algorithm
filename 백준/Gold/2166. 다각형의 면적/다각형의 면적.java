import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];      
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 신발끈공식
        double sum = 0;
        for(int i = 0; i< N; i++) {
        	sum += (double)arr[i][0] * arr[(i+1) % N][1];
        	sum -= (double)arr[i][1] * arr[(i+1) % N][0];
        }
        
        System.out.printf("%.1f", Math.abs(sum / 2));
    }
}