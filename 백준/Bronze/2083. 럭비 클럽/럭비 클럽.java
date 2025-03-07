import java.io.*;

public class Main {
	static int R, C, maxNum, arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	while(true) {
    		String[] str = br.readLine().split(" ");
    		
    		if(str[0].equals("#")) break;
    		sb.append(str[0]).append(" ");
    		
    		if(adultCheck(num(str[1]), num(str[2]))) sb.append("Senior");
    		else sb.append("Junior");
    		sb.append('\n');
    	}
    	
    	System.out.println(sb);
    }
    
    static boolean adultCheck(int age, int weight) {
    	return age > 17 || weight >= 80;
    }
    
    static int num(String num) {
    	return Integer.parseInt(num);
    }
}