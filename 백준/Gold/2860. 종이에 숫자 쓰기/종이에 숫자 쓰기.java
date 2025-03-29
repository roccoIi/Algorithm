import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        double cardCount = 1e9;
        double target = (Double.parseDouble(br.readLine()) * cardCount);
        int[] cards = new int[6];
        
        // 최대공약
        double gcd = gcd(target, cardCount);
        
        // count갯수로 target 숫자를 완성해야한다.
        cardCount /= gcd;
        target /= gcd;
        
        // basic: target숫자를 만들기 위한 평균값(소숫점을 버린다)
        // finalNum: 평균값을 count만큼 곱했으므로 target보다 무조건 작은 숫자가 완성된다.
        int basic = (int)(target / cardCount);
        int finalNum = (int)(basic * cardCount);
        
        // 평균값으로 모두 채운 후에 시작한다.
        cards[basic] = (int)cardCount;
        
        // finalNum이 target숫자와 같아질때 종료
        // 1씩 늘려가며 숫자를 맞춘다.
        while(finalNum != target) {
        	finalNum ++;
        	cards[basic]--;
        	cards[basic + 1]++;
        }
        
        for(int i = 1; i <= 5; i++) {
        	sb.append(cards[i]).append(" ");
        }
        
        System.out.println(sb);
    }
    
  
    static double gcd(double a, double b) {
    	return b == 0 ? a : gcd(b, a % b);
    }
}