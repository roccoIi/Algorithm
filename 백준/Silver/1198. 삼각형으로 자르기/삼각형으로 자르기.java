import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Node[] arr = new Node[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[i] = new Node(r, c);
		}
		;
		TreeSet<Double> set = new TreeSet<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				for(int k = j+1; k < N; k++) {
					Node x1 = arr[i];
					Node x2 = arr[j % N];
					Node x3 = arr[k % N];
					double area = (Math.abs((x1.r * (x2.c - x3.c) + x2.r * (x3.c - x1.c) + x3.r * (x1.c - x2.c)))) / 2.0;
					if(!set.contains(area)) set.add(area);
				}
			}
		}

		System.out.println(set.last());
	}
}