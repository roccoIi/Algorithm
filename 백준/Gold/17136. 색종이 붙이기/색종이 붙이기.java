import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int[] paper;
    static int minPaper = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[10][10];
        paper = new int[6];
        Arrays.fill(paper, 5);

        for (int r = 0; r < 10; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 10; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        play();
        
        // 최대 색종이는 25장, 그보다 많으면 하나도 안쓰였다는 의미이다.
        if(minPaper >= 26){
            System.out.println(-1);
        } else {
            System.out.println(minPaper);
        }


    }

    static void play(){

        int[] start = find();
        
        // (-1, -1) 은 더이상 탐색할 부분이 존재하지 않는다는 의미
        if(start[0] == -1 && start[1] == -1){
            int sum = 0;
            for (int i = 1; i <= 5; i++) {
                sum += paper[i];
            }
            // 총 25개 색종이 중에서 남은갯수를 빼면 사용한 색종이의 수
            minPaper = Math.min(minPaper, 25-sum);
            return;
        }

        // 5x5 부터 1x1 까지 탐색하면서 붙일 수 있을 때 붙인다.
        for (int paperSize = 5; paperSize > 0; paperSize--) {
            if(paper[paperSize] > 0 && canPaper(start[0], start[1], paperSize)){
                addPaper(start[0], start[1], paperSize);
                paper[paperSize]--;
                play();
                addPaper(start[0], start[1], paperSize );
                paper[paperSize]++;
            }
        }

    }
    // 영역 찾기
    static int[] find(){
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if(arr[r][c] == 1) return new int[]{r, c};
            }
        }
        return new int[]{-1, -1};
    }
    
    // 종이 붙이기
    static void addPaper(int r, int c, int size){
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                arr[i][j] *= -1;
            }
        }
    }
    
    // 붙일 수 있는 영역인가
    static boolean canPaper(int r, int c, int size){
        for (int i = r; i < r + size; i++) {
            for(int j = c; j < c + size; j++){
                if(!check(i, j) || arr[i][j] != 1) return false;
            }
        }
        return true;
    }
    
    // 범위 확인
    static boolean check(int r, int c){
        return r >= 0 && c >= 0 && r < 10 && c < 10;
    }

}