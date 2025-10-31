import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split("");
		
		
		System.out.println(mainSolution(str));
	}
	
	static int mainSolution(String[] str) {
		int countA = 0;
		
		for(int i = 0; i < str.length; i++) {
			if(str[i].equals("a")) countA++;
		}
		
		int minChange = Integer.MAX_VALUE;
		for(int i = 0; i < str.length; i++) {
			int countB = 0;
			for(int j = 0; j < countA; j++) {
				if(str[(i+j) % str.length].equals("b")) countB++;
			}
			
			minChange = Math.min(minChange, countB);
		}
		return minChange;
	}
}