import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int start, end;
		
		Node(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
	static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        // 1순위) 시작시간 오름차순, 2순위) 종료시간 오름차순
        PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> {
        	if(s1.start == s2.start) {
        		return Integer.compare(s1.end, s2.end);
        	} else {
        		return Integer.compare(s1.start, s2.start);
        	}
        });
        
        // 입력값 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	pq.add(new Node(start, end));
        }
        
        // 강의실별 끝나는시간 모아놓은 우선순위큐(오름차순정렬)
        PriorityQueue<Integer> answer = new PriorityQueue<>();
        answer.add(pq.poll().end);
        
        // 지금 강의의 시작시간이 가장 일찍 끝나는 강의실 종료시간보다 늦으면, 강의실 추가
        // 시작시간이 가장 일찍 끝나는 강의실 종료시간보다 빠르면, 강의 종료시간 업데이트
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	
        	if(curr.start >= answer.peek()) answer.poll();
        	answer.add(curr.end);
        }
        
        // 출력
        System.out.println(answer.size());
    }
}