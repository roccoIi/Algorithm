import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
    public static void main(String[] args) throws IOException, FileNotFoundException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int testCase = Integer.parseInt(br.readLine());
    	
    	for(int t = 1; t <= testCase; t++) {
    		String str = br.readLine();
    		
    		int count = 0;
    		char flag = '0';
    		for(int i = 0; i < str.length(); i++) {
    			if(str.charAt(i) == flag) {
    				continue;
    			} else {
    				if(flag == '0') {
    					flag = '1';
    				} else {
    					flag = '0';
    				}
    				count++;
    			}
    		}
    		
    		System.out.printf("#%d %d\n", t, count);
    	}
        
    }

}