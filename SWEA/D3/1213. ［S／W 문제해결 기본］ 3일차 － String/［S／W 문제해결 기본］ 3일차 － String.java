import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int T = 1; T <= 10; T++) {
			int t = Integer.parseInt(br.readLine());
			// 제시되는 문장 char배열로 담기
			char[] target = br.readLine().toCharArray();
			char[] str = br.readLine().toCharArray();
			int count = 0;
			
			// 고지식한 알고리즘을 통해 하나씩 비교
			str:for(int i = 0; i <= str.length - target.length; i++) {
				target:for(int j = 0; j < target.length; j++) {
					if(str[i+j] != target[j]) 
						continue str;
				}
				count++; //target과 일치하면 count 증가시키고 계속 진행
			}
			
			System.out.printf("#%d %d\n", T, count);
		}
	}
}