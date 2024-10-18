import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        PriorityQueue<Integer> rq = new PriorityQueue<>((s1, s2) -> s2 - s1);
        
        for(String str : operations){
            String[] temp = str.split(" ");
            
            switch(temp[0]) {
                case "I":
                    q.add(Integer.parseInt(temp[1]));
                    rq.add(Integer.parseInt(temp[1]));
                    break;
                case "D":
                    if(q.size() > 0){
                       if(Integer.parseInt(temp[1]) > 0){
                           int num = rq.poll();
                           q.remove(num);
                        } else {
                           int num = q.poll();
                           rq.remove(num);
                        }     
                    }              
                    break;
            }
        }
    
        int[] answer = new int[2];
        answer[0] = rq.size() > 0 ? rq.poll() : 0;
        answer[1] = q.size() > 0 ? q.poll() : 0;
        
        return answer;
    }
}