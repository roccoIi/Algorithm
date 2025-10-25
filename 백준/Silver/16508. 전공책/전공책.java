import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String words = br.readLine();
		int N = Integer.parseInt(br.readLine());
		
		String[][] books = new String[N][2];
		for(int i = 0; i < N; i++) {
			books[i] = br.readLine().split(" ");
		}
		
		System.out.println(mainSolution(words, N, books));
	}
	
	static int mainSolution(String words, int N, String[][] books) {
		int[] targetCount = new int[26];
		int minCost = -1;
		
		countWords(targetCount, words);
		
		for(int i = 1; i < (1 << N); i++) {
			int[] wordCount = new int[26];
			int total = 0;
			
			for(int j = 0; j < N; j++) {
				if((i & (1<<j)) == 0) continue;
				
				total += Integer.parseInt(books[j][0]);
				countWords(wordCount, books[j][1]);
			}
			
			if(isPossible(targetCount, wordCount)) {
				if(minCost == -1) minCost = 987654321;
				minCost = minCost > total ? total : minCost;
			}
		}
		return minCost;
	}
	
	static void countWords(int[] count, String book) {		
		for(int i = 0; i < book.length(); i++) {
			count[book.charAt(i) - 'A']++;
		}
	}
	
	static boolean isPossible(int[] targetCount, int[] bookCount) {
		for(int i = 0; i < 26; i++) {
			if(targetCount[i] > bookCount[i]) return false;
		}
		return true;
	}
}