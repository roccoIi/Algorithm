import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int highest = Integer.MIN_VALUE; // 가장 높은 높이
		int longest = Integer.MIN_VALUE; // 가장 멀리 떨어진 거리
		int shortest = Integer.MAX_VALUE; // 가장 가까운 거리
		int highestlength = Integer.MIN_VALUE; // 가장 높은 기둥
		int lefthigh = 0; // 가장 가까운 기둥 높이
		int righthigh = 0; // 가장 먼 기둥 높이
		int sum = 0; // 총 합
	
		int pillar = Integer.parseInt(br.readLine()); // 기둥 개수
		int[][] arr = new int[pillar][2]; // 기둥 배열
		for(int i = 0; i < pillar; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			// 가장 가까울때 기둥높이, 가장 멀때 기둥높이, 가장 높을때의 거리 구하기 및 배열입력
			if(arr[i][0] < shortest) {
				shortest = arr[i][0];
				lefthigh = arr[i][1];
			}
			if(arr[i][0] > longest) {
				longest = arr[i][0];
				righthigh = arr[i][1];
			}
			if(arr[i][1] > highest) {
				highest = arr[i][1];
				highestlength = arr[i][0];
			}
		}
		
		// 가까운 거리가 가장 높을때의 거리와 같아질때 종료
		while(shortest != highestlength) {
			sum += lefthigh;
			shortest++;
			for(int i = 0; i < pillar; i++) {
				if(arr[i][0] == shortest&& arr[i][1] > lefthigh) { // 현재 거리가 그다음 높은 기둥과 같을 때 높이 변화 및 더하기
					lefthigh = arr[i][1];
				}
			}
		}
		
		// 먼 거리가 가장 높을때의 거리와 같아질때 종료
		while(longest != highestlength) {
			sum += righthigh;
			longest--;
			for(int i = 0; i < pillar; i++) {
				if(arr[i][0] == longest&& arr[i][1] > righthigh) { // 현재 거리가 그다음 높은 기둥과 같을 때 높이 변화 및 더하기
					righthigh = arr[i][1];
				}
			}
		}

		sum += highest; // 가장 높을때 기둥 너비 추가
		
		System.out.println(sum);

	}
}