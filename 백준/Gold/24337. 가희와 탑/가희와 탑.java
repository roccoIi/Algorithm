import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int gahui = Integer.parseInt(st.nextToken());
        int danbi = Integer.parseInt(st.nextToken());        
        
        // 보이는 빌딩의 수가 총 건물 수 보다 많으면 -1
        if(gahui + danbi > N + 1) {
        	System.out.println("-1");
        	return;
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        // [순서]
        // 1) 가희가 볼 수 있는 빌딩 수 -1까지 넣는다.
        // 2) 둘중 큰수를 넣는다.
        // 3) 단비가 볼 수 있는 빌딩수까지 내림차순으로 넣는다.
        // 4) 그 사이를 1로 채운다.
        
        // 1) 
        for(int i = 1; i < gahui; i++) {
        	list.add(i);
        }
        
        // 2) 
        list.add(Math.max(gahui, danbi));
        
        // 3)
        for(int i = danbi-1; i > 0; i--) {
        	list.add(i);
        }
        
        // 4)
        for(int i = 0; i <= N - gahui - danbi; i++) {
        	list.add(1, 1);
        }
        
        // 출력
        for(int num : list) {
        	sb.append(num).append(" ");
    	}
        
        System.out.println(sb);
	}
}