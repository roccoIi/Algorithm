import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [회고]
 * 	1. 처음 막혔던 이유는 가장 최적의 높이를 찾아서 구하려고 했다. 
 *  2. 그러나 블럭을 쌓을때와 캘때의 시간도 다르고 인벤토리도 고려해야해서 코드짜기가 어려웠다.
 *  3. 최대 높이 256칸에 최대 맵의 넓이 500x500 인 것을 확인하고 최대 걸리는 시간을 확인했을때 약 6400만이 나왔다.
 *  4. 약 10억이 1초이기때문에 완전탐색을 진행해도 시간초과는 뜨지 않을것이라고 판단.
 *  5. 256칸부터 내려오면서 모든 경우의수를 탐색했다.
 * 
 */
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; StringBuilder sb;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int inven = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int hightestLand = 0;
        int time = 0;
        int minTime = Integer.MAX_VALUE;
        
        // 입력값 지도 받기
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		arr[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
    	for(int i = 256; i >= 0; i--) {
        	int tempinven = inven; // 원본 데이터 보호를 위해 임시 인벤토리 생성
        	time = 0; // 256번의 반복동안 시간 초기화
        	for(int r = 0; r < N; r++) {
        		for(int c = 0; c < M; c++) {
        			int differ = arr[r][c] - i; // 현재 기준 높이(i) 와 해당칸의 높이 차 구하고
        			if(differ < 0) { // 음수일때(블럭을 쌓아햐함), 양수일때(블럭클 캐야함) 구분
        				time += Math.abs(differ); // 1초 소요
        				tempinven -= Math.abs(differ); // 블럭을 쌓을땐 인벤토리에서 블럭을 소비하고
        			} else if(differ > 0) {
        				time += Math.abs(differ) * 2; // 2초 소요
        				tempinven += Math.abs(differ); // 캐낸블럭 인벤토리에 넣는다.
        			}
        		}
        	}
			if(tempinven < 0) continue; // 인벤토리 대출 땡겼으면 최소값 갱신않고 continue
        	if(time < minTime) {
        		minTime = time; // 최소값 갱신하고
        		hightestLand = i; // 그때의 블럭 높이 저장
        	}
        }
       System.out.println(minTime + " " + hightestLand);
    }
}