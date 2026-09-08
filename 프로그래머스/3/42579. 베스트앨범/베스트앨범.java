import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int index, streaming;
        
        Node(int index, int streaming){
            this.streaming = streaming;
            this.index = index;
        }
        
        @Override
        public int compareTo(Node o){
            // 1순위: 스트리밍 내림차순
            if(this.streaming != o.streaming){
                return Integer.compare(o.streaming, this.streaming);
            }
            
            // 2순위: 인덱스 오름차순
            return Integer.compare(this.index, o.index);
        }
    }
    
    static class Music implements Comparable<Music>{
        String gen;
        int total;
        
        Music(String gen, int total){
            this.gen = gen;
            this.total = total;
        }
        
        @Override
        public int compareTo(Music o){
            // 총 스트리밍 기준 내림차순
            return Integer.compare(o.total, this.total);
        }
        
    }
    static HashMap <String, PriorityQueue<Node>> map = new HashMap<>();
    static HashMap <String, Integer> countMap = new HashMap<>();
    static int N;
    public int[] solution(String[] genres, int[] plays) {
        N = genres.length;
        
        for(int i = 0; i < N; i++){
            String gen = genres[i];
            int count = plays[i];
            
            // 특정 장르의 총 스트리밍 횟수 누적
            if(countMap.containsKey(gen)){
                countMap.put(gen, countMap.get(gen) + count);
            } else {
                countMap.put(gen, count);
            }
            
            // 특정 장르별 스트리밍 상위 곡 저장
            if(map.containsKey(gen)) {
                map.get(gen).add(new Node(i, count));
            } else {
                PriorityQueue<Node> temp = new PriorityQueue<>();
                temp.add(new Node(i, count));
                
                map.put(gen, temp); 
            }
        }
        
        PriorityQueue<Music> pq = new PriorityQueue<>();
        countMap.forEach((k, v) ->{
            pq.add(new Music(k, v));
        });
        
        ArrayList<Integer> answer = new ArrayList<>();
        while(!pq.isEmpty()){
            Music m = pq.poll();
            
            int count = 0;
            PriorityQueue<Node> curr = map.get(m.gen);
            
            while(!curr.isEmpty() && count < 2){
                Node node = curr.poll();
                answer.add(node.index);
                count++;
            }
        }
        
        return listToArr(answer);
    }
    
    
    static int[] listToArr(ArrayList<Integer> list){
        int[] arr = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }
        
        return arr;
    }
}