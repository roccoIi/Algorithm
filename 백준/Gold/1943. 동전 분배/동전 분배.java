import java.io.*;
import java.util.*;

public class Main {
	static class Coin{
		int value, amount;
		
		Coin(int value, int amount){
			this.value = value;
			this.amount = amount;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        for(int T = 0; T < 3; T++) {
        	int N = Integer.parseInt(br.readLine());
        	
        	// dp배열 초기화 (dp[N번째 동전을 포함해서][M원을 만들 수 있는가?])
        	boolean[] dp = new boolean[100001]; //금액의 총합은 최대 10만을 넘지 않는다.
        	dp[0] = true;
        	
        	int totalCost = 0;
        	Coin[] coins = new Coin[N+1];
        	for(int i = 1; i <= N; i++) {
        		st = new StringTokenizer(br.readLine());
        		int value = Integer.parseInt(st.nextToken());
        		int amount = Integer.parseInt(st.nextToken());

        		coins[i] = new Coin(value, amount);
        		totalCost += value * amount;
        	}
        	
        	// 만약 총 금액의 합이 홀수라면 정확히 반으로 나눌 수 없다.
        	if((totalCost & 1) == 1) {
        		sb.append("0\n");
        		continue;
        	}
        	
        	// 만약 정확히 절반으로 나눠질 수 있다면 1 반환
        	else if(dp[totalCost/2]) {
        		System.out.printf("[%d번] 조기종료됨\n", T);
        		sb.append("1\n");
        		continue;
        	}

        	// 1) 코인배열 순차적으로 탐색
        	for(int i = 1; i <= N; i++) {
        		int value = coins[i].value;
        		int amount = coins[i].amount;
        		
        		// 2) 목표 금액부터 1원까지 순차적으로 탐색을 진행한다.
        		for(int j = totalCost / 2; j >= 0; j--) {
        			
        			// j원이 만들어질 수 없다면 이후는 탐색할 이유가 없다.
        			if(!dp[j]) continue;
        			
        			// 3) 현재금액(j)에 동전의 갯수만큼 더하면서 만들어질 수 있는 금액을 표시한다.
        			for(int k = 1; k <= amount; k++) {
        				int nowCost = j + value * k;
        				
        				// 토탈금액(여기선 절반금액만 만들어져도 된다)을 넘어서면 다음 금액을 진행한다.
        				if(nowCost > totalCost / 2) break;
        				
        				// 그게 아니면 true처리
        				dp[nowCost] = true;
        			}
        		}
        	}
        	
        	if(dp[totalCost/2]) sb.append("1\n");
        	else sb.append("0\n");
        }
        
        System.out.println(sb);
    }
}