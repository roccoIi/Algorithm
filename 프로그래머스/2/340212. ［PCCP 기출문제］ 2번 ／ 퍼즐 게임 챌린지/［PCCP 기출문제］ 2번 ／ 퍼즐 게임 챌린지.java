import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int left = 1;
        int right = Integer.MAX_VALUE;
        int answer = 0;
        while(left <= right){
            int level = left + (right - left) / 2;
            
            if(isPossible(level, diffs, times, limit)){
                right = level - 1;
                answer = level;
            } else {
                left = level + 1;
            }
        }
        return answer;
    }
    
    static boolean isPossible(int level, int[] diffs, int[] times, long limit){
        long currTime = 0l;
        
        for(int i = 0; i < diffs.length; i++){
            if(diffs[i] > level){
                int num = diffs[i] - level;
                currTime += (times[i] + times[i-1]) * num;
            }
            currTime += times[i];
            if(currTime > limit) return false;
        }
        return true;
    }
}