import java.util.*;

class Solution {
    static int answer;
    public int solution(int n, int[][] q, int[] ans) {
        
        dfs(n, 0, 0, ans, q, new HashSet<>());
        return answer;
    }
    
    static void dfs(int n, int curr, int count, int[] ans, int[][] q, HashSet<Integer> set){
        
        if(count == 5){
            if(isSame(q, ans, set)) answer++;
            return;
        }
        
        for(int i = curr+1; i <= n; i++){
            set.add(i);
            dfs(n, i, count+1, ans, q, set);
            set.remove(i);
        }
    }
    
    static boolean isSame(int[][] q, int[] ans, HashSet<Integer> set){
        for(int r = 0; r < q.length; r++){
            int count = 0;
            
            for(int c = 0; c < 5; c++){
                if(set.contains(q[r][c])) count++;    
            }
            
            if(count != ans[r]) return false;
        }
        return true;
    }
}