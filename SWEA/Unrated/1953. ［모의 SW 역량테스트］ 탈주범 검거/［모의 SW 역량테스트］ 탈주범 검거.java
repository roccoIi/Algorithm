import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int time, nc, nr, N, M;
    static int[][] arr; static int[][] copyArr; // 탈주경로를 위한 배열과 지나간 경로 배열
    static int[] dr = {-1, 0, 0, 1}; static int[] dc = {0, -1, 1, 0}; //델타배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = 0; // 총 위치 수
            N = Integer.parseInt(st.nextToken()); // 가로
            M = Integer.parseInt(st.nextToken()); // 세로
            int R = Integer.parseInt(st.nextToken());  // 현재위치 (가로)
            int C = Integer.parseInt(st.nextToken());  // 현재위치 (세로)
            time = Integer.parseInt(st.nextToken());
            arr = new int[N][M];  // 탈주경로 지도 배열
            copyArr = new int[N][M];  // 지나간 경로 배열

            //지도 작성
            for(int r = 0; r < N; r++){
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            //경로 찾는 중...
            howMany(R, C, 1);

            // 지나간 경로 경우의 수에서 지나간 발자국은 전부 카운트
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if(copyArr[r][c] != 0) count++;
                }
            }
            // 출력
            System.out.printf("#%d %d\n", t, count);
        }
    }
    public static void howMany(int rPoint, int cPoint, int realtime){
        if(realtime > time) return;
        copyArr[rPoint][cPoint]++; // 무사히 도착했다면 발자국 +1

        for (int d = 0; d < 4; d++) { // 사방탐색
            int temp = arr[rPoint][cPoint];
            nr = rPoint + dr[d];
            nc = cPoint + dc[d];

            while(nr >= 0 && nr < N && nc >=0 && nc < M && // 경계값을 지나지 않으면서
                    arr[nr][nc] !=0 && check(rPoint, cPoint, nr, nc)){ // 지도에 0이 아니고 파이프가 갈 수 있는 장소면 루프 시작
                arr[rPoint][cPoint] = 0; // 지나온길 표시하기 위한 빵조각
                howMany(rPoint + dr[d], cPoint + dc[d], realtime + 1); // 재귀
            }
            arr[rPoint][cPoint] = temp; // 빵조각 주워담기
        }
    }

    public static boolean check(int rPoint, int cPoint, int nr, int nc) {
        // 각 파이프 종류 별 갈 수 있는 방향 전부 작성
        switch (arr[rPoint][cPoint]) {
            case 1:
                if (nr < rPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 5 || arr[nr][nc] == 6) return true;
                } else if (nr > rPoint) {
                    if(arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 4 || arr[nr][nc] == 7) return true;
                } else if (nc > cPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 6 || arr[nr][nc] == 7) return true;
                } else if (nc < cPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 4 || arr[nr][nc] == 5) return true;
                } return  false;
            case 2:
                if(nr > rPoint){
                    if(arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 4 || arr[nr][nc] == 7) return true;
                } else if (nr < rPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 5 || arr[nr][nc] == 6) return true;
                } return false;
            case 3:
                if(nc > cPoint){
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 6 || arr[nr][nc] == 7) return true;
                } else if (nc < cPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 4 || arr[nr][nc] == 5) return true;
                } return false;
            case 4:
                if(nc > cPoint){
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 6 || arr[nr][nc] == 7) return true;
                } else if (nr < rPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 5 || arr[nr][nc] == 6) return true;
                } return false;
            case 5:
                if(nc > cPoint){
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 6 || arr[nr][nc] == 7) return true;
                } else if (nr > rPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 4 || arr[nr][nc] == 7) return true;
                } return false;
            case 6:
                if(nc < cPoint){
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 4 || arr[nr][nc] == 5) return true;
                } else if (nr > rPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 4 || arr[nr][nc] == 7) return true;
                } return false;
            case 7:
                if(nc < cPoint){
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 3 || arr[nr][nc] == 4 || arr[nr][nc] == 5) return true;
                } else if (nr < rPoint) {
                    if (arr[nr][nc] == 1 || arr[nr][nc] == 2 || arr[nr][nc] == 5 || arr[nr][nc] == 6) return true;
                } return false;
        }
        return false;
    }
}