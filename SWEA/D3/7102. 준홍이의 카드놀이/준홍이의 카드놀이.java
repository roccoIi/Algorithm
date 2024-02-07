import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		int testCase = Integer.parseInt(br.readLine());
		for(int t = 1; t <= testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] arr = new int[N+M+2];
			int maxNum = Integer.MIN_VALUE;
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M ;j++) {
					arr[i+j]++;
				}
			}
			
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] > maxNum) maxNum = arr[i];
			}
			
			System.out.print("#" + t);
            for(int i = 0; i <= N+M; i++) {
                if(arr[i] == maxNum) {
                    System.out.print(" " + i);
                }
            }
            System.out.println();
		}
	}

}