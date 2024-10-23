import java.util.*;

class Solution {
    static class Node{
        int start, end;
        
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public int solution(int[][] routes) {
        PriorityQueue<Node> q = new PriorityQueue<>((s1, s2) -> s1.end - s2.end);
        
        for(int i = 0; i < routes.length; i++){
            q.add(new Node(routes[i][0], routes[i][1]));
        }
        
        int recentNum = q.poll().end;
        int answer = 1;
        
        while(!q.isEmpty()){
            Node node = q.poll();
            
            if(node.start > recentNum){
                answer++;
                recentNum = node.end;
            }
        }
        
        return answer;
    }
}