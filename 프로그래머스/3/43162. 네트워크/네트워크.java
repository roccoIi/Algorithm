import java.util.*;

class Solution {
    static boolean[] check;
    public int solution(int n, int[][] computers) {
        check = new boolean[n];
        int total = 0;
        
        for(int i = 0; i < n; i++){
            if(!check[i]){
                total += bfs(i, computers);
            }
        }
        
        return total;
    }
    
    public int bfs(int num, int[][] computers){
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        check[num] = true;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int i = 0; i < computers[cur].length; i++){
                if(computers[cur][i] == 1 && !check[i]){
                    q.add(i);
                    check[i] = true;
                }
            }
        }
        return 1;
    }
}