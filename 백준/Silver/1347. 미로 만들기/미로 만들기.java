import java.io.*;
import java.util.StringTokenizer;
/*
*  1. 전체 배열의 크기를 모르고 내 위치도 모르기때문에 주어진 리스트가 모두 직진이라는 가정 하에 최대 배열의 크기를 구한다.
*  2. 여기서 내 위치를 중간으로 만들어야 사방으로 진행해도 경계를 벗어나지 않는다. (내 위치 (N,N) / 배열의 크기 (N*2+1) X (N*2+1)
*  3. 좌측으로 회전(L)을 + / 우측으로 회전(R)은 - 로 가정하여 진행방향을 결정할 델타배열의 index 로 사용한다.
*  4. 인덱스가 마이너스로 나오면 배열의 범위를 벗어나기 때문에 (4 + (index % 4)) % 4 로 계산하여 인덱스를 확인한다.
*  5. 진행하면서 최소 x,y값과 최대 x,y값을 저장하여 미로의 실제 크기를 파악하고 내 위치를 이동시키며 .을 찍는다.
*  6. 전체 범위 중 최대,최소 범위만큼만 출력하면서 .이 찍힌 위치는 . 출력을 , 아닌 곳은 #을 출력한다.
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int index = 0;

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        char [][] arr = new char[N*2+1][N*2+1];
        int meX = N; int meY = N; // 현재 내 위치 좌표 값
        int minX = N; int minY = N; // 좌표 최소값
        int maxX = N; int maxY = N; // 좌표 최대값
        arr[meX][meY] = '.'; // 내 위치 . 찍고 확인

        for (int i = 0; i < N; i++) { //R이면 인덱스- , L이면 인덱스 +
            char c = str.charAt(i);
            if(c == 'R'){
                index--;
            } else if(c == 'L'){
                index++;
            } else if(c == 'F'){ //  진행해야한다면 인덱스 계산후 진행방향을 잡고 진행, 최대/최소값을 갱신힌다.
                meX += dc[(4 + (index % 4)) % 4]; meY += dr[(4 + (index % 4)) % 4];
                minX = Math.min(meX, minX); minY = Math.min(meY, minY);
                maxX = Math.max(meX, maxX); maxY = Math.max(meY, maxY);
                arr[meX][meY] = '.';
            }
        }

        for (int x = minX; x <= maxX; x++) { // 최대 최소값만큼 돌면서 .이 찍혀있지 않으면 #을 출력한다.
            for (int y = minY; y <= maxY; y++) {
                if(arr[x][y] == '.'){
                    System.out.print('.');
                } else {
                    System.out.print('#');
                }
            }
            System.out.println();
        }
    }
}