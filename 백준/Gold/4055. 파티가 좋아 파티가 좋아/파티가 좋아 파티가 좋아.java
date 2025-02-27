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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int testCase = 0;
        while(true) {
        	int N = Integer.parseInt(br.readLine());
        	if(N == 0) break;
        	testCase++;
        	
        	ArrayList<Node> list = new ArrayList<>();
        	for(int i = 0; i < N; i++) {
        		String[] str = br.readLine().split(" ");
        		if(num(str[1]) - num(str[0]) == 0) continue;
        		list.add(new Node(num(str[0]), num(str[1])));
        	}
        	
        	Collections.sort(list, (s1, s2) -> {
				if(s1.end == s2.end) return Integer.compare(s1.start, s2.start);
				else return Integer.compare(s1.end, s2.end);
			});
        	
        	
        	int partyCnt = 0;
        	for(int time = 8; time < 24; time++) {
        		for(int count = 0; count < 2; count++) {
        			for(int i = 0; i < list.size(); i++) {
        				Node curr = list.get(i);
        				if(time >= curr.start && time < curr.end) {
        					partyCnt++;
        					list.remove(i);
        					break;
        				}
        			}
        		}
        		
        		if(list.size() == 0) break;
        	}

        	
        	sb.append("On day ")
        	  .append(testCase)
        	  .append(" Emma can attend as many as ")
        	  .append(partyCnt)
        	  .append(" parties.")
        	  .append('\n');
        }
        System.out.println(sb);
    }
    
    static int num(String text) {
    	return Integer.parseInt(text);
	}
}