import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [회고]
 * 1. 2개의 요리를 만들 재료를 구하는 함수와 재료가 다 구해졌을때 그걸 요리하는 함수 이렇게 2개가 필요하다고 생각했다.
 * 2. 처음엔 8개의 재료가 주어진다면 4개에 대해서 또 2개의 요리로 나눠야하는 줄 알았지만 총 2개의 요리라는 내용 확인
 * 3. 요리 2개를 팀으로 명명, 팀 2개가 만들어졌을때 요리하는 함수를 만들어서 각 요리에 대한 시너지를 구하고
 * 4. 최소값을 찾는다.
 * 
 * [input]
 * 1
 * 4
 * 0 5 3 8
 * 4 0 4 1
 * 2 5 0 3
 * 7 2 3 0
 * 
 * */


public class Solution {
	static int N, target, count, minNum;
	static int[][] arr;
	static boolean[] visit;
	static boolean[] teamcheck;
	static int[] team1;
	static int[] team2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine()); // 테스트케이스
		
		for(int t = 1; t <= testCase; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N][N]; // 전체 재료들의 시너지를 받을 배열
			team1 = new int[N/2]; // A요리
			team2 = new int[N/2]; // B요리
			teamcheck = new boolean[N]; // 각 요리 만들때 필요한 boolean
			minNum = Integer.MAX_VALUE;
			
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			makeTeam(0,0);
			
			System.out.printf("#%d %d\n", t, minNum);
			
			
			
			
			
		}
	}
	
	static void makeTeam(int index, int num) {
		//기저
		// A요리(team1)에 대한 배열만 먼저 만들고 teamcheck에 체크되지 않은 애들을 모아서 B요리로 만든다.
		if(num >= team1.length) { // A요리의 재료들이 다 채워지면 기저조건 발생
			int value = 0;
			int idx = 0;
			for(int i =0; i < teamcheck.length; i++) {
				if(!teamcheck[i]) team2[idx++] = i;
			}
			
			value = Math.abs(cook(team1) - cook(team2));
			minNum = Math.min(minNum, value);
			
			return;
		}
		
		if(index >= teamcheck.length) return;
		
		
		//재귀
		teamcheck[index] = true; // 재료 확인하고
		team1[num] = index; // 재료 인덱스 넣고
		makeTeam(index+1, num+1); // 재귀
		
		teamcheck[index] = false;
		makeTeam(index+1, num);
		
	}
	
	// 각 요리별 맛 시너지 구하기
	static int cook(int[] team) {
		int sumTeam = 0;
		
		// 요리가 2개뿐이기때문에 for문 2개로 구현
		for(int r = 0; r < team.length-1; r++) {
			for(int c = r+1; c < team.length; c++) {
				// 1재료-2재료 ,  2재료-1재료는 단순히 r과 c의 위치만 바꿔서 구한다.
				sumTeam += arr[team[r]][team[c]] + arr[team[c]][team[r]];
				
			}
		}
		return sumTeam;
	}
	
	
	
}