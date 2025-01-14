import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] stores = new int[N];
		for(int i = 0; i < N; i++) {
			stores[i] = Integer.parseInt(st.nextToken());
		}
		
		int status = 0;
		int milkCount = 0;
		for(int i = 0; i < N; i++) {
			if(stores[i] == status) {
				status = (status + 1) % 3;
				milkCount++;
			}
		}
		System.out.println(milkCount);
	}
}