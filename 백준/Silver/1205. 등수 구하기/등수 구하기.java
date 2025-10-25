import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int score = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] scores = null;
		
		if(N != 0) {
			scores = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();

		}
		System.out.println(mainSolution(N, score, P, scores));
	}
	
	static int mainSolution(int N, int score, int P, int[] scores) {
		int rank = 1;
		
		if(N == P && score <= scores[N-1]) return -1;
		else {
			for(int i = 0; i < N; i++) {
				if(scores[i] <= score) return rank;
				else rank++;
			}
		}
		
		return rank;
	}
}