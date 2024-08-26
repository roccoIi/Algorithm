import java.util.*;
import java.io.*;


public class Main {
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static class fireBall{
		int value, direction, speed;
		Node node;
		
		fireBall(Node node, int value, int speed, int direction){
			this.node = node;
			this.value = value;
			this.speed = speed;
			this.direction = direction;
		}
	}
	static int N, M, K;
	static Queue<fireBall>[][] list;
	static Queue<fireBall> q = new LinkedList<>();
	static Queue<Node> nodeQ = new LinkedList<>();
	static int[][] dir = {{-1, -1, 0, 1, 1, 1, 0, -1}, {0, 1, 1, 1, 0, -1, -1, -1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 리스트 배열 초기화
		list = new Queue[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				list[r][c] = new LinkedList<>();
			}
		}
		
		// 입력값 받기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			q.add(new fireBall(new Node(r, c), value, speed, direction));
		}
		
		for(int i = 0; i < K; i++) {
			moveBall();
			copyBall();
		}
		
		int ans = 0;
		while(!q.isEmpty()) {
			ans += q.poll().value;
		}
		
		System.out.println(ans);
		
	}
	
	// 파이어볼 이동
	static void moveBall() {
		int r, c, nr, nc = 0;
		while(!q.isEmpty()) {
			fireBall fireball = q.poll();
			r = (fireball.node.r + (dir[0][fireball.direction] * fireball.speed)) % N;
			c = (fireball.node.c + (dir[1][fireball.direction] * fireball.speed)) % N;
			nr = (r < 0 ? r + N : r) % N;
			nc = (c < 0 ? c + N : c) % N;
			// 만약 해당 좌표에 다른 파이어볼이 들어있지 않다면 추가하고 해당 좌표를 기억한다.
			if(list[nr][nc].isEmpty()) {
				list[nr][nc].add(new fireBall(new Node(nr, nc), fireball.value, fireball.speed, fireball.direction));
				nodeQ.add(new Node(nr, nc));
			
			// 만일 해당 좌표에 다른 파이어볼이 있다면 해당 좌표는 기억하고 있으니 추가만 해놓는다. 
			} else {
				list[nr][nc].add(new fireBall(new Node(nr, nc), fireball.value, fireball.speed, fireball.direction));
			}	
		}
	}
	
	static void copyBall() {
		while(!nodeQ.isEmpty()) {
			Node node = nodeQ.poll();
			
			// 만약 해당 좌표에 들어있는 파이어볼이 하나면 다음 큐로 넘긴다.
			if(list[node.r][node.c].size() == 1) {
				fireBall ball = list[node.r][node.c].poll();
				q.add(new fireBall(new Node(node.r, node.c), ball.value, ball.speed, ball.direction));
				
			// 만약 두개 이상이라면 합친다.
			} else {
				int totalValue = 0;
				int totalSpeed = 0;
				int totalDirection = 0;
				int size = 0;
				while(!list[node.r][node.c].isEmpty()) {
					fireBall ball = list[node.r][node.c].poll();
					totalValue += ball.value;
					totalSpeed += ball.speed;
					totalDirection += ball.direction % 2;
					size++;
				}
				
				// 파이어볼의 질량이 0이라면 삭제
				if(totalValue / 5 == 0) continue;

				// 들어있는 파이어볼의 방향이 모두 홀수, 짝수 라면 해당 방향 계산값은 0이거나(모두 홀수), size와 동일한 값일 것이다.(모두 짝수)
				for(int i = 0; i < 4; i++) {
					q.add(new fireBall(new Node(node.r, node.c), totalValue / 5, totalSpeed / size, 
							totalDirection == 0 || totalDirection == size ? (i * 2)  : (i * 2) + 1));
				}
			}
		}
	}
	
}