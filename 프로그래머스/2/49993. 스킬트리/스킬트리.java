import java.util.*;

class Solution {
    static HashMap<Character, Integer> map = new HashMap<>();
    public int solution(String skill, String[] skill_trees) {
        // 최종 출력할 정답개수
        int answer = 0;
        
        // 지정된 스킬트리 저장 <스킬명, 스킬순서>
        for(int i = 0; i < skill.length(); i++){
            map.put(skill.charAt(i), i);
        }
        
        // 주어진 전체 스킬트리 탐색시작
        for(String skills : skill_trees){
            // 현재 탐색해야하는 스킬의 순서(인덱스)
            int idx = 0; 
            boolean isRight = true;
            
            for(int i = 0; i < skills.length(); i++){
                char curr = skills.charAt(i);
                int nowIdx = map.getOrDefault(curr, -1);
                
                
                if(nowIdx != idx) {
                    if(nowIdx == -1) continue;
                    isRight = false;
                    break;
                }
                
                idx++;
            }
            
            if(isRight) answer++;
        }
        return answer;
    }
}