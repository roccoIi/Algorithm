import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int idx;
		char alpa;
		
		Node(int idx, char alpa){
			this.idx = idx;
			this.alpa = alpa;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        
        // 자료구조 세팅
        boolean[] visited = new boolean[N];
        ArrayList<Character> list = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> {
        	if(s1.alpa == s2.alpa) {
        		return Integer.compare(s2.idx, s1.idx);
        	} else {
        		return Character.compare(s1.alpa, s2.alpa);
        	}
        });
        
        // 종이에 적혀있는 글자 입력
        String str = br.readLine();
        for(int i = 0; i < N; i++) {
        	list.add(str.charAt(i));
        	pq.add(new Node(i, str.charAt(i)));
        }
        
        int nowListIdx = list.size() - 1;
        StringBuilder heeWon = new StringBuilder();
        StringBuilder sangGeun = new StringBuilder();
        for(int i = 0; i < N/2; i++) {
        	// 상근이
        	while (visited[nowListIdx]) {
        		nowListIdx--;
        	}
        	visited[nowListIdx] = true;
        	sangGeun.append(list.get(nowListIdx--));
        	
        	// 희원이
        	while(true) {
        		Node curr = pq.poll();
        		
        		if(!visited[curr.idx]) {
        			heeWon.append(curr.alpa);
        			visited[curr.idx] = true;
        			break;
        		}
        	}
        }
        
        if(isHeeWonWin(heeWon.toString(), sangGeun.toString(), N)) sb.append("DA").append('\n').append(heeWon);
        else sb.append("NE").append('\n').append(heeWon);
        
        System.out.println(sb);
	}
    
    static boolean isHeeWonWin (String heeWon, String sangGeun, int N) {
    	for(int i = 0; i < N/2; i++) {
        	if(heeWon.charAt(i) == sangGeun.charAt(i)) continue;
        	else {
        		return heeWon.charAt(i) < sangGeun.charAt(i);
        	}
        }
    	return false;
    }
}