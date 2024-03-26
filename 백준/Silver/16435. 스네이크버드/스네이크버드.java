import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int l = Integer.parseInt(str[1]);
		
		int[] arr = new int[n];
		
		str = br.readLine().split(" ");

		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		Arrays.sort(arr);
		
		for(int i=0;i<n;i++) {
			if(arr[i]<=l) {
				l++;
			}
		}
		System.out.println(l);
	}
}