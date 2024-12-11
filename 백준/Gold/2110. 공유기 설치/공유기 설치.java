import java.io.*;
import java.util.*;


public class Main {
	static int N, C;
	static List<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));				
        StringTokenizer st = new StringTokenizer(br.readLine());		        
	
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
        	list.add(Integer.parseInt(br.readLine()));
        }
        
        Collections.sort(list);
        
        int left = 1;
        int right = list.get(N-1) - list.get(0);
        int mid = 0;
        while(left <= right) {
        	mid = (left + right) / 2;
        	
        	if(findHouse(mid)) {
        		left = mid + 1; 
        	} else {
        		right = mid - 1;
        	}
        }
        
        System.out.println(right);
        
	}
	
	public static boolean findHouse(int target) {		
		int count = 1;
		int lastLocation = list.get(0);
		
		for(int i = 1; i < N; i++) {
			int nowLocation = list.get(i);
			
			if(nowLocation - lastLocation >= target) {
				count++;
				lastLocation = nowLocation;
			}
		}
		
		
		return count >= C;
	}
}