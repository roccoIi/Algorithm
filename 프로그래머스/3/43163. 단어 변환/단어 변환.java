import java.util.*;

class Solution {
    static boolean[] visited;
    static class Node{
        String word;
        int step;
        
        public Node(String word, int step){
            this.word = word;
            this.step = step;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];     
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));
        
        while(!q.isEmpty()){
            Node now = q.poll();
            
            if(now.word.equals(target)) return now.step;
            
            for(int i = 0; i < words.length; i++){                
                if(visited[i]) continue;
                
                // 일치하는 글자수 카운팅 (1개만 변화해야한다)
                int count = 0;
                for(int j = 0; j < begin.length(); j++){
                    if(now.word.charAt(j) != words[i].charAt(j)) count++;
                }
                
                // 단 1개만 변화한 단어일때 방문체크하고 큐에 넣는다.
                if(count == 1){
                    visited[i] = true;
                    q.add(new Node(words[i], now.step + 1));
                }      
            }
        }
        return 0;
    }
}