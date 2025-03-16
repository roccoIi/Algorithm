import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine().toLowerCase();;
        
        int[] arr = new int[26];
        for(int i = 0; i < str.length(); i++) {
        	arr[str.charAt(i) - 'a']++;
        }
        
        char answer = '?';
        int maxNum = -1;
        for(int i = 0; i < arr.length; i++) {
        	if(maxNum < arr[i]) {
        		maxNum = arr[i];
        		answer = (char)('A' + i);
        	} else if(maxNum == arr[i]) {
        		answer = '?';
        	}
        }
        System.out.println(answer);
    }
}