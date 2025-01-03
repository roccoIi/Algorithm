import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 계란개수 입력 및 자료구조 초기화
		N = Integer.parseInt(br.readLine());
		int[][] eggs = new int[2][N];
		answer = Integer.MIN_VALUE;
		
		// 계란들 입력받기
		// 0행 : 내구도 (durability)
		// 1행 : 무게 (weight)
		for(int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			eggs[0][i] = Integer.parseInt(str[0]); // 내구도
			eggs[1][i] = Integer.parseInt(str[1]); // 무게
		}
		
		dfs(0, 0, eggs);
		System.out.println(answer);
	}
	
	static void dfs(int idx, int count, int[][] eggs) {
		if(idx >= N || count == N-1) {
			answer = answer < count ? count : answer;
			return;
		}
		
		// 현재 집은 달걀이 깨져있다면 다음 달걀 집으러 진
		if(eggs[0][idx] <= 0) dfs(idx +1, count, eggs);
		else {
			// 현재 달걀이 온전하다면 다른 달걀을 하나씩 두들겨본다.
			for(int i = 0; i < N; i++) {
				if(eggs[0][i] <= 0 || eggs[0][idx] <= 0 || i == idx) continue;
	
				// 양쪽 달걀에 데미지 누적
				eggs[0][idx] -= eggs[1][i];
				eggs[0][i] -= eggs[1][idx];	
				
				// 깨진 달걀이 있다면 개수 카운트
				dfs(idx + 1, count + (eggs[0][i] <= 0 ? 1 : 0) + (eggs[0][idx] <= 0 ? 1 : 0), eggs);
				
				// 양쪽 달걀에 들어간 데미지 복원
				eggs[0][idx] += eggs[1][i];
				eggs[0][i] += eggs[1][idx];
			}
		}
	}
}