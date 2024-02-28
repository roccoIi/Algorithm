import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	static int nowX, nowY, H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; StringBuilder sb;
        
        int testCase = Integer.parseInt(br.readLine());
        for(int t = 1; t <= testCase; t++) {
        	sb = new StringBuilder();
        	st = new StringTokenizer(br.readLine());
        	H = Integer.parseInt(st.nextToken()); // 세로 (y좌표)
        	W = Integer.parseInt(st.nextToken()); // 가로 (x좌표)
        	
        	char[][] map = new char[H][W];     
        	// 지도입력하면서 현재 위치 확인 nowX, nowY에 저장
        	for(int r = 0; r < H; r++) {
            	String str = br.readLine();
        		for(int c = 0; c < W; c++) {
        			map[r][c] = str.charAt(c);
        			if(map[r][c] == '<' || map[r][c] ==  '>' ||  map[r][c] == 'v' ||  map[r][c] == '^') {
        				nowX = r;
        				nowY = c;
        			}
        		}
        	}
        	
        	int num = Integer.parseInt(br.readLine());
        	String str = br.readLine();
        	
        	// 사용자 입력을 String으로 받은 후 하나씩 읽으면서 행동
        	for(int i = 0; i < num; i++) {
        		if(str.charAt(i) == 'U') {
        			up(nowX, nowY, map);
        		} else if(str.charAt(i) == 'D') {
        			down(nowX, nowY, map);
        		} else if(str.charAt(i) == 'L') {
        			left(nowX, nowY, map);
        		} else if(str.charAt(i) == 'R') {
        			right(nowX, nowY, map);
        		} else if(str.charAt(i) == 'S') {
        			shoot(nowX, nowY, map);
        		}
        	}
        	
        	// 결과 저장 및 출력
        	sb.append("#").append(t).append(" ");
        	for(int r = 0; r < H; r++) {
        		for(int c = 0; c < W; c++) {
        			sb.append(map[r][c]);
        		}
        		if(r == H-1) break;
        		sb.append("\n");
        	}     	
        	System.out.println(sb); 
        }
    }
    
    //경계조건 검사
    static boolean check(int x, int y) {
    	return x>=0 && y>=0 && x < H && y < W;
    }
    
    // [상] 경계조건을 만족하고 평지라면 전차를 돌리고 이동한다. 평지가 아니라면 전차만 돌린다.
    static void up(int x, int y, char[][] map) {
    	if(check(x-1, y) && map[x-1][y] == '.') { // 경계조건을 만족하고 평지라면
    		nowX--; // 내 전차의 위치를 해당 방향으로 1칸 전진하고
    		map[x][y] = '.'; // 내가 있던 위치를 평지로 변환
    		map[nowX][y] = '^'; // 바라보는 방향을 바꾼다.
    	} else { // 평지가 아니라면
    		map[x][y] = '^'; // 방향만 바꾼다.
    		return;
    	}
    }
    
    // [하] 동일
    static void down(int x, int y, char[][] map) {
    	if(check(x+1, y) && map[x+1][y] == '.') {
    		nowX++;
    		map[x][y] = '.';
    		map[nowX][y] = 'v';
    	} else {
    		map[x][y] = 'v';
    		return;
    	}
    }
    
    // [우] 동일
    static void right(int x, int y, char[][] map) {
    	if(check(x, y + 1) && map[x][y + 1] == '.') {
    		nowY++;
    		map[x][y] = '.';
    		map[x][nowY] = '>';
    	} else {
    		map[x][y] = '>';
    		return;
    	}
    }
    
    // [좌] 동일
    static void left(int x, int y, char[][] map) {
    	if(check(x, y - 1) && map[x][y - 1] == '.') {
    		nowY--;
    		map[x][y] = '.';
    		map[x][nowY] = '<';
    	} else {
    		map[x][y] = '<';
    		return;
    	}
    }
    
    // [포탄발사]
    static void shoot(int x, int y, char[][]map) {
    	int num = 1;
    	
    	//현재 바라보는 위치에 따라 행동
    	if(map[x][y] == '>') { 
    		while(true) {
    			if(!check(x, y+num)) { // 경계조건을 벗어난다면 return
    				return;
    			} else if(map[x][y + num] == '*') { // 진행방향에 벽돌이 있다면 부숴서 평지로 만든 후 return
    				map[x][y+num] = '.';
    				return;
    			} else if(map[x][y + num] == '#') {// 진행방향에 강철벽이 있다면 return
    				return;
    			}
    			num++;  // 아무런 조건도 만족하지 못했다면 한칸 더 넘어가서 탐색
    		}
    	} else if(map[x][y] == 'v') {
    		while(true) {
    			if(!check(x+num, y)) {
    				return;
    			} else if(map[x+num][y] == '*') {
    				map[x+num][y] = '.';
    				return;
    			} else if(map[x+num][y] == '#') {
    				return;
    			}
    			num++;    			
    		}
    	} else if(map[x][y] == '<') {
    		while(true) {
    			if(!check(x, y-num)) {
    				return;
    			} else if(map[x][y - num] == '*') {
    				map[x][y-num] = '.';
    				return;
    			} else if(map[x][y - num] == '#') {
    				return;
    			}
    			num++;    			
    		}
    	} else {
    		while(true) {
    			if(!check(x-num, y)) {
    				return;
    			} else if(map[x - num][y] == '*') {
    				map[x - num][y] = '.';
    				return;
    			} else if(map[x - num][y] == '#') {
    				return;
    			}
    			num++;    			
    		}
    	}
    }
}