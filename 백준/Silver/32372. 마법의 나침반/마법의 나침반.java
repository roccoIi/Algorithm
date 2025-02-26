import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
    static int[][] records; // [M][3]: Xi, Yi, Ki 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        records = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            records[i][0] = Integer.parseInt(st.nextToken()); // Xi
            records[i][1] = Integer.parseInt(st.nextToken()); // Yi
            records[i][2] = Integer.parseInt(st.nextToken()); // Ki
        }
        
        int[] answer = findTreasure();
        System.out.println(answer[0] + " " + answer[1]);
    }
    
    static int[] findTreasure() {
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (isValidTreasure(x, y)) {
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{-1, -1}; // 유일성이 보장되므로 여기 도달 불가
    }
    
    static boolean isValidTreasure(int x, int y) {
        for (int i = 0; i < M; i++) {
            int xi = records[i][0];
            int yi = records[i][1];
            int ki = records[i][2];
            
            // 나침반 위치와 보물 위치가 같으면 안 됨
            if (x == xi && y == yi) return false;
            
            // 방향 조건 확인
            if (!checkDirection(x, y, xi, yi, ki)) return false;
        }
        return true;
    }
    
    static boolean checkDirection(int x, int y, int xi, int yi, int ki) {
        switch (ki) {
            case 1: return x < xi && y == yi;
            case 2: return x < xi && y > yi;
            case 3: return x == xi && y > yi;
            case 4: return x > xi && y > yi;
            case 5: return x > xi && y == yi;
            case 6: return x > xi && y < yi;
            case 7: return x == xi && y < yi;
            case 8: return x < xi && y < yi;
            default: return false;
        }
    }
}