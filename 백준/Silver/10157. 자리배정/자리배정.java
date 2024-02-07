import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	// 입력값 받아오기 및 etc..
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int C = Integer.parseInt(st.nextToken());
    	int R = Integer.parseInt(st.nextToken());
    	int target = Integer.parseInt(br.readLine());
    	
    	// 주어진 배열 생성
    	int[][] arr = new int[R][C];
    	
    	// 델타배열
    	int[] dr = {1, 0, -1, 0}; // 기존에는 상 -> 우 -> 하 -> 좌 에서 
    	int[] dc = {0, 1, 0, -1}; // 상하반전이기에 델타배열도 하->우->상->좌로 변경
    	int r = 0; // 주어진 문제를 상하반전 시켜놓고 시작 (내려가면서 커지는 배열 특성상)
    	int c = 0;
    	int d = 0; // 델타배열 이동을 위한 변수
    	int cnt = 1; // 배열에 숫자 집어넣기 위한 변수
    	int nr, nc; // 이동위치 설정을 위한 변수
    	while(true) {
    		arr[r][c] = cnt++;
    		
    		// 배열에 넣는 숫자가 총 범위를 넘어선다면 while 종료
    		if(cnt == R*C + 1) {
    			break;
    		}
    		
    		// 앞으로 움직이려는 위치
    		nr = r + dr[d];
    		nc = c + dc[d];
    		
    		// 움직이려는 위치가 경계를 넘어서거나 이미 값이 있다면 경로 변경
    		if(nr < 0 || nr >= R || nc < 0 || nc >= C || arr[nr][nc] != 0) {
    			d = (d+1) % 4;
    		}
    		
    		// 이동
    		r += dr[d];
    		c += dc[d];
    	}
    	
    	// 타겟숫자를 찾을때까지 전체탐색, 찾으면 해당좌표 저장 및 깃발 돌리기
    	  	boolean flag = true;
    	end:for(int i = 0; i < R; i++) {
    		for(int j = 0; j < C; j++) {
    			if(arr[i][j] == target) {
    				flag = false;
    				r = i+1;
    				c = j+1;
    				break end;
    			}
    		}
    	}
    	if(flag) { // 깃발이 안돌아갔다면 타겟을 찾지 못했으니 0 출력
    		System.out.println(0);
    	} else {
        	System.out.printf("%d %d", c, r);
    	}
    }
}