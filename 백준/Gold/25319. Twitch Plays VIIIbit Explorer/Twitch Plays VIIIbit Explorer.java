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
	static int R, C, S, totalAlpabetNum[], idNum[];
	static Queue<Node> position[];
	static StringBuilder order = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		totalAlpabetNum = new int[26];
		position = new ArrayDeque[26];
		
		for(int i = 0; i < 26; i++) {
			position[i] = new ArrayDeque<>();
		}
		
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				int index = changeNum(str.charAt(c));
				totalAlpabetNum[index]++;
				position[index].add(new Node(r, c));
			}
		}

		// 아이디에 들어가는 각 철자의 개수 저장하는 배열 idNum
		idNum = new int[26];
		String id = br.readLine();
		HashSet<Character> set = new HashSet<>();
		for(int i = 0; i < id.length(); i++) {
			char idChar = id.charAt(i);
			idNum[changeNum(idChar)]++;
			set.add(idChar);
		}
		
		// 초기 좌표값은 가장 왼쪽 위 (0, 0) 이다.
		int[] location = {0, 0};
		int upgrade = 0;
		while(isEnough(set)) {			
			for(int i = 0; i < id.length(); i++) {
				Node next = position[changeNum(id.charAt(i))].poll();
				totalAlpabetNum[changeNum(id.charAt(i))]--;
				move(location[0], location[1], next.r, next.c);
				order.append('P');
				location[0] = next.r;
				location[1] = next.c;
			}
			upgrade++;
		}
		
		move(location[0], location[1], R-1, C-1);
		
		// 정답 출력
		StringBuilder answer = new StringBuilder();
		answer.append(upgrade).append(" ").append(order.toString().length()).append('\n').append(order);
		System.out.println(answer);
	}
	
	// (r, c) -> (nr, nc) 로 이동할때 커멘드 입력
	static void move(int r, int c, int nr, int nc) {
		if(r > nr) {
			for(int i = 0; i < r - nr; i++) {
				order.append('U');
			}
		}
		
		if(r < nr) {
			for(int i = 0; i < nr - r; i++) {
				order.append('D');
			}
		}
		
		if(c > nc) {
			for(int i = 0; i < c - nc; i++) {
				order.append('L');
			}
		}
		
		if(c < nc) {
			for(int i = 0; i < nc - c; i++) {
				order.append('R');
			}
		}
	}
	
	// 현재 지도에 ID를 만들 수 있을만큼 알파벳이 충분히 있는가
	static boolean isEnough(HashSet<Character> set) {
		for(char id : set) {
			if(totalAlpabetNum[changeNum(id)] < idNum[changeNum(id)]) return false;
		}
		return true;
	}
	
	// 범위체크
	static boolean boundaryCheck(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
	
	// char -> int 변환
	static int changeNum(char alpabet) {
		return alpabet - 'a';
	}
}