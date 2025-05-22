import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        
        long minSquare = (long)Math.pow(r1, 2);
        long maxSquare = (long)Math.pow(r2, 2);
        
        long dots = 0;
        for(int r = 0; r < r2; r++){
            long minCircle = (long) Math.ceil(Math.sqrt(minSquare - Math.pow(r, 2)));
            long maxCircle = (long) Math.floor(Math.sqrt(maxSquare - Math.pow(r, 2)));
            
            if(minCircle == 0) minCircle++;
            dots += (maxCircle - minCircle + 1);
        }
        long answer = dots * 4;
        return answer;
    }
}