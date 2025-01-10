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
	static int R, C, N, map[][];
	static int[][] dir = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static ArrayList<Node> list;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C]; // 미네랄: 1, 빈칸: 0
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				if(str.charAt(c) == 'x') map[r][c] = 1;
			}
		}
		
		// 총 막대기 던지는 횟수
		N = Integer.parseInt(br.readLine());
		
		// 막대기 높이 배열 입력
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 막대기 던지기 시작
		for(int i = 0; i < N; i++) {
			int target = findMineral(R - arr[i], (i & 1));

			// 만일 해당 높이에 미네랄이 없었을 경우 다음 높이로 진행
			if(target == -1) continue;
			
			map[R - arr[i]][target] = 0;
			
			// 해당 미네랄과 이어진 클러스터가 무너지는지 아닌지 확인
			for(int d = 0; d < 4; d++) {
				int nr = R - arr[i] + dir[0][d];
				int nc = target + dir[1][d];
				
				if(!checkBoundary(nr, nc) || map[nr][nc] == 0) continue;
				
				if(isFalling(new Node(nr, nc))) { // 무너짐
					int num = minimumFalling();
					
					// 만약 이동해야하는 거리가 0이라면 중지한다. (단, 그럴 경우는 없을 것)
					if(num == 0) break;
					
					// (중요) r값이 큰 순서부터 아래로 내려가야, 기존에 업데이트된 항목을 덮어쓰는 일이 없다.
					Collections.sort(list, (s1, s2) -> Integer.compare(s2.r, s1.r));
					
					// 공중에 떠있는 미네랄 덩어리들은 num값만큼 아래로 내린다. (중력 작용)
					for(int j = 0; j < list.size(); j++) {
						Node curr = list.get(j);
						map[curr.r][curr.c] = 0;
						map[curr.r + num][curr.c] = 1;
					}
					
					// 두개 이상의 클러스터가 떨어지는 경우는 없으므로 즉시 반복문을 종료한다.
					break;
				}
			}
		}
		
		// 출력할 값은 StringBuilder에 저장
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 1) sb.append('x');
				else sb.append('.');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	
	// 막대기를 던졌을 때 가장 빠르게 마주치는 c의 좌표값
	static int findMineral(int height, int dir) {
		if(dir == 0) {
			for(int c = 0; c < C; c++) { // 홀수번째 (왼쪽에서 던짐)
				if(map[height][c] == 1) return c;
			}
		} else {
			for(int c = C - 1; c >= 0; c--) { // 짝수번째 (오른쪽에서 던짐)
				if(map[height][c] == 1) return c;
			}
		}
		
		// 미네랄을 마주치지 못했다면 -1 반환
		return -1;
	}
	
	
	// 해당 위치의 클러스터들이 바닥과 닿아있는지 (공중에 떠있지 않은지) 확인한다.
	static boolean isFalling(Node start) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		visited[start.r][start.c] = true;;
		q.add(start);

		// 미네랄 덩어리 리스트 초기화 후 미네랄 좌표 입력
		// 만약 현재 진행중인 미네랑 덩어리가 공중에 떠있을 경우 해당 list는 공중에 떠있는 미네랄들의 좌표로 쓰인다.
		list = new ArrayList<>();
		list.add(start);
		
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = curr.r + dir[0][d];
				int nc = curr.c + dir[1][d];
				
				// 경계를 벗어나거나, 방문했거나, 미네랄이 아니면 넘어간다.
				if(!checkBoundary(nr, nc) || visited[nr][nc]|| map[nr][nc] == 0) continue;
				visited[nr][nc] = true;
				
				// 바닥으로 이어졌다는 것은 해당 클러스터가 무너지지 않는다는 뜻 => 즉시 종료
				if(nr == R - 1) return false;
				
				q.add(new Node(nr, nc));
				list.add(new Node(nr, nc));
			}
		}
		
		// 바닥을 찾지 못하고 끝났다면 해당 클러스터는 무너진다는 뜻이다.
		return true;
	}
	
	
	// 각 c좌표값별로 가장 하단에 위치한 r값들을 아래로 한칸씩 내려가면서
	// 다른 미네랄을 만나거나 경계값에 도달하는 가장 최소값을 찾는다.
	static int minimumFalling () {
		
		// 현재 떨어지려고 하는 클러스터를 지도에 표시한다.
		int[][] tmpMap = new int[R][C];
		for(Node curr : list) {
			tmpMap[curr.r][curr.c] = 1; 
		}
		
		// 떨어지면서 x를 만났을 때, 그 x가 떨어지는 클러스터의 x인지 다른 x인지를 확인한다.
		int answer = R;
		for(Node curr : list) {
			int tmp = 0;
			int nr = curr.r;
			
			while(nr < R) {
				nr += dir[0][1];
				
				if(nr >= R || (map[nr][curr.c] == 1 && tmpMap[nr][curr.c] != 1)) {
						break;
				}
				tmp++;
			}
			answer = answer > tmp ? tmp : answer;
		}
		return answer;
	}
	
	// 새로 생성한 r, c값이 경계를 벗어나는지 확인
	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}