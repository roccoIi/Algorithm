import java.io.*;
import java.util.*;

class Solution {
    static boolean visited[];
    static Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        
        visited = new boolean[numbers.length()];       
        
        dfs(numbers, 0, "");
        
        for(int num : set){
            if(isPrime(num)) answer++;
        }
        
        return answer;
    }
    
    
    public void dfs(String number, int idx, String sample){
        if(idx > number.length()){
            return;
        }
        
        for(int i = 0; i < number.length(); i++){
            if(visited[i]) continue;
            
            visited[i] = true;
            set.add(Integer.parseInt(sample + number.charAt(i)));
            dfs(number, idx+1, sample + number.charAt(i));
            visited[i] = false;
        }
    }
    
    static boolean isPrime(int number){
        if(number < 2) return false;
        
        for(int i = 2; i <= (int)Math.sqrt(number); i++){
            if(number % i == 0) return false;
        }
        
        return true;
    }
}