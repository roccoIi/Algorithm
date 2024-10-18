import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((s1, s2) -> s2 - s1);
        long answer = 0;
        
        for(int num : works){
            pq.add(num);
        }
        
        for(int i = 0; i < n; i++){
            if(!pq.isEmpty()){
                int num = pq.poll();
                if(num > 0) pq.add(num - 1);
            } 
        }
        
        while(!pq.isEmpty()){
           int num = pq.poll();
            answer += ((long) num * num); 
        }
        
        return answer;
    }
}