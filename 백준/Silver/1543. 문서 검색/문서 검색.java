import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = 0;
		char[] str = br.readLine().toCharArray();
		char[] target = br.readLine().toCharArray();
		
		str : for(int i = 0; i <= str.length - target.length; i++) {
			for(int j = 0; j < target.length; j++) {
				if(str[i+j] != target[j]) {
					continue str;
				}
			}
			i += target.length-1;
			count++;
		}
		System.out.println(count);
	}
}