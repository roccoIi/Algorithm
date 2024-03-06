import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    /*
     * [회고]
     *  1. 그림을 먼저 그렸고 그리다보니 DFS, BFS도 아닌 DP의 느낌이 물씬 났다.
     *  2. 문제는 [2차원배열 + 방향]을 어떻게 구현해야할지였다. 그러다 얼마전 chatGPT가 3차원 배열을 보여줬던것이 떠올랐고
     *      3가지밖에 없는 방향을 0,1,2 으로 표현해 구현하면 뭐 어떻게 되지 않을까 생각했다.
     *  3. 각 방향별로 더할 수 있는 경우의 수를 먼저 구해서 각 방향별 점화식을 만들었다.
     *  4. 행이 1일때의 가로, 열이 3일때의 세로는 1가지 경우의 수밖에 없는 것을 그림을 통해 알았고 1로 초기화
     *  5. 대각선으로 진행될땐 해당 위치와 위, 왼쪽이 0이여야한다는 것을 뒤늦게 발견하고 조건을 추가했다.
     *  6. (2,3) 위치가 대각선으로 내려오지 못하면 (3,3)부터 시작하는 아래방향은 전부 0이 된다는것을 발견하고 조건을 추가했다.
     *  7. 실질적으로 경우의수가 변화하는 범위는 (2,4)부터인것을 확인하고 for문 범위를 수정했다.
     */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N+1][N+1]; // 주어진 배열
        int[][][] arr = new int[N+1][N+1][3]; // 각 위치 별 3가지 방향에 대한 경우의 수를 저장할 배열

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                house[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // (2,3) 부분에 대각선으로 내려가기 위해선 3가지 조건을 만족해야한다. 만족한다면 arr[2][3][1]위치에 1
        if(house[2][3]==0 && house[1][3]==0 && house[2][2] ==0)
            arr[2][3][1] = 1; // (2,3) 대각선 존재

        // 가로는 큰 제한없이 1일때 반복을 멈추고 그게 아니라면 끝까지 1행은 1
        for (int i = 3; i <= N; i++) {
            if(house[1][i] != 0) break;
            arr[1][i][0] = 1; // 가로 1이 존재하는
        }

        // 세로는 (2,3)위치가 대각선으로 내려올때만 (3,3)부터 세로방향의 경우의 수가 1이 될 수 있다.
        for (int i = 3; i <= N; i++) {
            if (arr[2][3][1] != 0){
                if (house[i][3] != 0) break;
                arr[i][3][2] = 1; // 세로 1이 존재하는
            } else {
                break;
            }
        }


        // 본격적인 반복은 (2,4)부터 시작된다.
        for (int r = 2; r <= N; r++) {
            for (int c = 4; c <= N; c++) {
                if (house[r][c] == 1) continue; // 0: 가로, 1: 대각선, 2: 세로 // 해당위치 벽(1)이라면 패스
                arr[r][c][0] = arr[r][c - 1][0] + arr[r][c - 1][1]; // 가로의 경우의 수는 가로+대각선
                arr[r][c][2] = arr[r - 1][c][1] + arr[r - 1][c][2]; // 세로의 경우의 수는 세로+대각선
                if (house[r][c] == 0 && house[r - 1][c] == 0 && house[r][c - 1] == 0) // 대각선의 경우의 수는 우선 ㄴ 모양으로 벽(1)이 없어야하고
                    arr[r][c][1] = arr[r - 1][c - 1][0] + arr[r - 1][c - 1][1] + arr[r - 1][c - 1][2]; // 만족한다면 가로+세로+대각선
            }
        }

        // 해당위치로 도착하는 경우의 수는 가로+세로+대각선으로 도착하는 모든 경우의수를 더하며 된다.
        System.out.println(arr[N][N][0] + arr[N][N][1] + arr[N][N][2]);
    }
}