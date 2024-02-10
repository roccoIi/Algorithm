import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
    static int[] dr = {-1, 0, 1, 0}; static int[] dc = {0, -1, 0, 1}; // 사방탐색 델타배열 생성
    static int[][] arr;
    static int nr, nc, count, maxNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++){
            int N = Integer.parseInt(br.readLine());
            arr = new int[N][N]; // 전체 배열 입력
            maxNum = Integer.MIN_VALUE; // 최댓값
            int preMaxNum = 0; // 이전 최댓값을 저장해 놓기 위한 변수
            int answer = Integer.MAX_VALUE;; // 최댓값일때의 좌표값
            int nowAnswer = 0; // 최댓값이 같을 경우 이전 촤표값을 저장하기 위한 변수

            // 배열 생성
            for(int r = 0; r < N; r++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++){
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 전체 배열 탐색
            for(int r = 0; r < N; r++){
                for(int c = 0; c < N; c++){
                    count = 1;
                    int a = counting(r, c); //count메소드를 사용하여 최댓값 확인
                    if(a > preMaxNum){ // 이전 최댓값보다 크게 나온다면 갱신
                        preMaxNum = maxNum;
                        answer = arr[r][c];
                    } else if (a == preMaxNum) { // 이전 최댓값과 같다면 좌표값을 비교해 좌표값이 작을 경우에만 갱신
                        nowAnswer = arr[r][c];
                        if(nowAnswer < answer) {
                            preMaxNum = maxNum;
                            answer = nowAnswer;
                        }
                    }
                }
            }
            System.out.printf("#%d %d %d\n", t, answer, maxNum);
        }
    }

    public static int counting(int r, int c){

        for(int d = 0; d < 4; d++){ // 사방탐색 진행
            nr = r + dr[d];
            nc = c + dc[d];
            if (nr >= 0 && nr < arr.length && nc >= 0 && nc < arr.length &&
                    arr[nr][nc] == arr[r][c] + 1) { // 이동할 지역이 1 클경우에만 이동
                count++; // 몇개가 연속인지 카운팅 +1
                if(count > maxNum) { // 지금까지의 최댓값보다 카운팅이 크다면 갱신
                    maxNum = count;
                }
                counting(r + dr[d], c + dc[d]); // 계속해서 이동
            }
        }
        return count;
    }
}