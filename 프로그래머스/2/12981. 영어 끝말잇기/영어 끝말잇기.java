import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int[] index = new int[n];
        HashSet<String> set = new HashSet<>();
        
        int personIdx = 0;
        char c = '\u0000';
        for(int i = 0; i < words.length; i++){
            if(!isEqual(c, words[i]) || 
               set.contains(words[i]))return new int[]{personIdx+1, index[personIdx] + 1};
            
            set.add(words[i]);
            
            // 끝말잇기 마지막 문자 갱신
            int tempIdx = words[i].length();
            c = words[i].charAt(tempIdx - 1);
            
            // 해당 사람에게 +1 부여
            index[personIdx]++;
            personIdx = (personIdx + 1) % n;
        }
       

        return answer;
    }
    
    static boolean isEqual(char c, String str){
        return c == '\u0000' || c == str.charAt(0);
    }
    
    
}