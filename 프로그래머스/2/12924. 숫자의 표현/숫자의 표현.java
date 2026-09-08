class Solution {
    public int solution(int n) {
        int answer = 0;
        int left = 0; 
        int sum = 0;
        
        for(int right = 1; right <= n; right++){
            sum += right;
            
            while(sum > n){
                sum -= left++;
            }
            
            if(sum == n) answer++;
        }
    
        return answer;
    }
}