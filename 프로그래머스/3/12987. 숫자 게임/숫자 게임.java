import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        Deque<Integer> dq = new ArrayDeque<>();
        int point = 0;
        
        // A는 내림차순으로 정렬하여 큰 수부터 뽑아낸다.
        for(int i : A){
            q.add(i);
        }
        
        // B는 오름차순으로 정렬하여 덱에 넣는다.
        Arrays.sort(B);
        for(int i : B){
            dq.add(i);
        }
        
        // 큐가 빌때까지 반복진행
        while(!q.isEmpty()){
            int aNum = q.poll();
            
            // 만약 덱 가장 큰 수로 이길수 있을 경우에만 대결한다.
            if(dq.peekLast() > aNum){
                dq.pollLast();
                point++;
            // 만약 덱의 가장 큰 수로도 이길 수 없다면 가장 작은 수를 제거한다.
            } else {
                dq.pollFirst();
            }
        }
        return point;
    }
}