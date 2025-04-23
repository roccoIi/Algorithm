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


// 1) 매개변수로 level을 받아서 이분탐색 진행(true/false)

// 해당 레벨을 통해서 전체 문제를 풀때 제한시간을 벗어나는지 false/true 반환하는 함수