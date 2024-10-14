import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        
        int elseCount = 0;
        
        for(int i = 0; i < record.length; i++){
            String[] temp = record[i].split(" ");
            if(temp[0].equals("Enter")){
                map.put(temp[1], temp[2]);
            } else if(temp[0].equals("Leave")) {
                continue;
            } else if(temp[0].equals("Change")) {
                map.put(temp[1], temp[2]);
                elseCount++;
            }
        }
        
        String[] answer = new String[record.length - elseCount];
        int count = 0;
        for(int i = 0; i < record.length; i++){
            
            String[] temp = record[i].split(" ");
            if(temp[0].equals("Enter")){
                answer[count++] = map.get(temp[1]) + "님이 들어왔습니다.";
            } else if(temp[0].equals("Leave")) {
                answer[count++] = map.get(temp[1]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}