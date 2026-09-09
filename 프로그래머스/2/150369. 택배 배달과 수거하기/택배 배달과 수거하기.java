class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int deliverCnt = 0;
        int pickupCnt = 0;
        long answer = 0l;

        for(int i = n - 1; i >= 0; i--){
            deliverCnt += deliveries[i];
            pickupCnt += pickups[i];

            while(deliverCnt > 0 || pickupCnt > 0){
                deliverCnt -= cap;
                pickupCnt -= cap;
                answer += (i + 1) * 2L;
            }
        }
        return answer;
    }
}