import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int beforeNum = Integer.parseInt(st.nextToken());
		int firstGroupMinNum = beforeNum;
		int curr = beforeNum;
		int upCount = 0;
		int secondGroupMaxNum = Integer.MAX_VALUE;
		for(int i = 1; i < N; i++) {
			beforeNum = curr;
			curr = Integer.parseInt(st.nextToken());
			
			if(upCount == 0) {
				if(beforeNum > curr) {
					upCount++;
					secondGroupMaxNum = curr;
				}
			} else {
				if(beforeNum < curr) {
					secondGroupMaxNum = curr;
				} else {
					upCount++;
				}
			}
			
			if(upCount >= 2) break;
			
		}
		if(upCount == 0) System.out.println(1);
		else if(upCount == 1 && secondGroupMaxNum < firstGroupMinNum) System.out.println(2);
		else System.out.println(3);
	}
}