import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String str = br.readLine();
		
		int nowLocation = -1;
		int nextAlpa = 0;
		
		int start = 0;
		int end = -1;
		
		long totalCount = 0;
		
	    while(++nowLocation <= str.length()) {
	    	
	    	// 문장의 끝까지 도달했을 때 Z까지 모두 탐색했다면 수량체크 
	    	if(nowLocation == str.length()) {
	    		if(nextAlpa > 25) totalCount += counting(str, start, end);
	    		continue;
	    	}

	    	int nowAlpa = str.charAt(nowLocation) - 'A';
	    	
	    	// Z를 넘어갈 경우 계속 Z가 이어지면 endpoint만 +1 후 이어서 탐색
	    	// 이어지지 않을 경우 수량확인 및 startpoint, endpoint 초기화
	    	if(nextAlpa > 25) {
	    		if(nowAlpa == 25) {
	    			end++;
	    			continue;
	    		} else {
	    			totalCount += counting(str, start, end);
	    			start = nowLocation;
	    			end = nowLocation - 1;
	    			nextAlpa = 0;
	    		}
	    	}
	    	
	    	// 1) 현재 목표로 하는 알파벳과 동일할 경우 endpoint+1, nextAlpa+1
	    	// 2) 바로 직전의 알파벳과 동일할 경우 endpoint만 +1
	    	// 3) 모두 다를 경우 startpoint와 endpoint 모두 초기화
	    	if(nowAlpa == nextAlpa) {
	    		if(nowAlpa == 0) start = nowLocation;
	    		end++;
	    		nextAlpa++;
	    	} else if(nowAlpa == nextAlpa - 1) {
    			end++;
    		} else {
	    		if(nowAlpa == 0) nextAlpa = 1;
	    		else nextAlpa = 0;

    			start = end = nowLocation;
	    	}
	    }
	    System.out.println(totalCount);
		
	}
	static long counting(String str, int start, int end) {
		long Acnt = 0;
		long Zcnt = 0;
		
		while(str.charAt(start) == 'A') {
			Acnt++;
			start++;
		}
		
		while(str.charAt(end) == 'Z') {
			Zcnt++;
			end--;
		}
		
		return Acnt * Zcnt;
	}
}