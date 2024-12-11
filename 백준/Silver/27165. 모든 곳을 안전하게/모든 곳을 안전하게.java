import java.io.*;
import java.util.*;

/**
 * 총 4가지로 나뉜다.
 * 1. 1이 2개를 초과할경우
 *    => 뭔짓을 하더라도 (안전한상태)를 만들 수 없다.
 * 2. 1이 2개 일 경우
 * 	  => 1이 위치한 그 자리가 딱 주사위만큼 차이날 경우에만 (안전한상태)가 가능하다.
 * 3. 1이 1개 일 경우
 *    => 1) 해당 위치의 1을 다른 곳으로 옮긴다.
 *       2) dice를 뺀 위치가 3개 이상일때 1개 땡겨온다.   
 * 4. 1이 0개 일 경우
 *    => 3개 이상인 아무 위치에서 다른 위치로 옮긴다.      
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));				
		StringBuilder sb = new StringBuilder();
        StringTokenizer st;		        
        boolean flag = false;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int oneCount = 0;
        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());  
            if(arr[i] == 1) {
            	oneCount++;
            	list.add(i);
            }
            
        }

        int dice = Integer.parseInt(br.readLine());
       
 /*(1)*/if(oneCount > 2) sb.append("NO");
 /*(2)*/else if(oneCount == 2) {
        	if(list.get(0) + dice == list.get(1)) {
        		sb = build(list.get(0), list.get(1));
        	} else sb.append("NO");
        }
 /*(3)*/else if(oneCount == 1) {
        	if(list.get(0) + dice <= n && arr[list.get(0) + dice] != 0) {
        		sb = build(list.get(0), list.get(0) + dice);
        	} else if(list.get(0) - dice >= 0 && arr[list.get(0) - dice] > 2){
        		sb = build(list.get(0) - dice, list.get(0));
        	} else sb.append("NO");
        }
 /*(4)*/else {
 			// 기본으로는 NO를 가지고 가고 문제 있을때만 YES출력한다.
	 		// for문 안에 넣어놓으면 NO가 여러번 출력될 문제가 있음
	 		sb.append("NO");
	 		
        	for(int i = 0; i + dice <= n; i++) {
        		if(arr[i] > 2 && arr[i + dice] != 0) {
        			sb = build(i, i + dice);
                    break;
        		} 
        	}
        }

        System.out.println(sb);			
	}
	
	public static StringBuilder build(int start, int end) {
		StringBuilder sb = new StringBuilder();
		return sb.append("YES\n").append(start).append(" ").append(end);
	}
}