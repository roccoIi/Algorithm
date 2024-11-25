import java.util.*;
import java.io.*;


public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		for(int T = 0; T < testCase; T++) {
			int N = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				if(st.nextToken().equals("I")) {
					int num = Integer.parseInt(st.nextToken());
					map.put(num, map.getOrDefault(num, 0) + 1);
				} else {
					if(map.isEmpty()) continue;
					int num;
					
					if(Integer.parseInt(st.nextToken()) < 0) {
						num = map.firstKey();
					} else {
						num = map.lastKey();
					}
					
					if(map.get(num) == 1) {
						map.remove(num);
					} else {
						map.put(num, map.get(num) - 1);
					}
				}
			}
			if(map.isEmpty()) sb.append("EMPTY\n");
			else sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
		}
		System.out.println(sb);
	}
}