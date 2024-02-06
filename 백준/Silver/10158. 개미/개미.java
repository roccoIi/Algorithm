import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		// N, M = 전체 좌표 // x, y = 현재 개미의 위치
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st2.nextToken());
		int y = Integer.parseInt(st2.nextToken());
		
		// 개미가 이동한 총 시간
		int time = Integer.parseInt(br.readLine());
		
		// 개미의 현재 위치 + 이동시간 한 좌표를 전체 좌표로 나눴을때
		// 몫이 짝수라면 한번 0경계를 찍었다는 이야기이므로 나머지가 현재좌표
		// 몫이 홀수라면 한번 전체좌표의 경계를 찍었다는 이야기이므로 전체좌표 - 나머지가 현재좌표
		
		int answerX;
		int answerY;
		
		if(((x+time) / N) % 2 == 0) {
			answerX = (x+time) % N;
		} else {
			answerX = N - ((x+time) % N);
		}
		
		if(((y+time) / M) % 2 == 0) {
			answerY = (y+time) % M;
		} else {
			answerY = M - ((y+time) % M);
		}
		
		//정답 출력
		System.out.printf("%d %d", answerX, answerY);

	}
}