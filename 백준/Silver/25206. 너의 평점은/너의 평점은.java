import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int subjectCnt = 0;
        double totalGrade = 0;
        for(int i = 0; i < 20; i++) {
        	st = new StringTokenizer(br.readLine());
        	String subjectName = st.nextToken();
        	Double point = Double.parseDouble(st.nextToken());
        	String grade = st.nextToken();
        	
        	if(grade.equals("P")) continue;
        	subjectCnt += point;
        	
        	//A = 4 || B = 3 || C = 2 || D = 1 || F = 0
        	
        	Double tmpGrade = 4.0 - (grade.charAt(0) - 'A');
        	if(tmpGrade <= 0) tmpGrade = 0.0;
        	else {
        		if(grade.charAt(1) == '+') tmpGrade += 0.5;
        	}
        	        	
        	totalGrade += tmpGrade * point;
        }
        System.out.println(totalGrade / subjectCnt);
    } 
}