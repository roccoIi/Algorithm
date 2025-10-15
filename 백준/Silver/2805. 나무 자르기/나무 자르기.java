import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] heights = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		
		System.out.println(mainSolution(N, M, heights));
	}
	
	static int mainSolution(int N, int M, int[] heights) {
		int maxTree = 0;
		
		for(int height : heights) {
			maxTree = maxTree < height ? height : maxTree;
		}
		
		return binarySearch(0, maxTree, M, heights);
	}
	
	static int binarySearch(int left, int right, int M, int[] heights) {
		int answer = -1;
		int mid;
		
		while(left <= right) {
			mid = left + (right - left) / 2;

			if(cutTree(heights, mid) >= M) {
				left = mid + 1;
				answer = mid;
			} else {
				right = mid - 1;
			}
		}
		
		return answer;
	}
	
	static long cutTree(int[] heights, int h) {
		long sum = 0;
		
		for(int height : heights) {
			if(height < h) continue;
			sum += (height - h);
		}
		
		return sum;
	}
}