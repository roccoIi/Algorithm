import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // m : 가로줄, n: 세로줄, puddles: 웅덩이 좌표
        
        // 지도를 제작하고 전체를 1로 초기화
        int[][] map = new int[n+1][m+1];
        for(int r = 1; r <=n; r++){
            for(int c = 1; c <= m; c++){
                map[r][c] = 1;
            }
        }

        // 웅덩이 좌표는 0으로 초기화 한다.
        for(int i = 0; i < puddles.length; i++){
            int r = puddles[i][1];
            int c = puddles[i][0];
            map[r][c] = 0;
        }
        
        // 좌측과 상단을 제외하고 & 웅덩이를 제외하고 누적합을 구한다.       
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= m; c++){
                if(map[r][c] == 0 || (r == 1 && c == 1)) continue;
                int up = n == 1 ? 1 : map[r-1][c];
                int left = m == 1 ? 1 : map[r][c-1];
                
                map[r][c] = (up + left) % 1000000007;
            }
        }
        
//         for(int r = 0; r <= n; r++){
//             for(int c = 0; c <= m; c++){
//                 System.out.print(map[r][c] + " ");
//             }
//             System.out.println();
//         }
        

        // 1,000,000,007로 나눈 값을 반환한다.
        return map[n][m];
    }
}