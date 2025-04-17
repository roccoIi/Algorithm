import java.io.*;
import java.util.*;

public class Main {
	static int T, N, M;
	static ArrayList<Integer> sumA, sumB;
	static HashMap<Integer, Integer> cashA, cashB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        
        N = Integer.parseInt(br.readLine());
        int[] arrA = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arrA[i] = Integer.parseInt(st.nextToken());
        }
        
        M = Integer.parseInt(br.readLine());
        int[] arrB = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
        	arrB[i] = Integer.parseInt(st.nextToken());
        }
        
        ArrayList<Integer> sumA = new ArrayList<>();
        ArrayList<Integer> sumB = new ArrayList<>();
        HashMap<Integer, Long> cashA = new HashMap<>();
        HashMap<Integer, Long> cashB = new HashMap<>();
        
        getSum(arrA, sumA, cashA);
        getSum(arrB, sumB, cashB);
        
        Collections.sort(sumA);
        Collections.sort(sumB);
        
        long answer = 0L;
        for(int num : sumA) {
        	if(!cashB.containsKey(T-num)) continue;
        	
        	answer += cashA.get(num) * cashB.get(T-num);
        }
        
        System.out.print(answer);
    }
    
    static void getSum(int[] arr, ArrayList<Integer> list, HashMap<Integer, Long> map){
    	
    	for(int i = 0; i < arr.length; i++) {
    		int sum = 0;
    		for(int j = i; j < arr.length; j++) {
    			sum += arr[j];
    			if(map.containsKey(sum)) map.put(sum, map.get(sum) + 1);
    			else {
    				list.add(sum);
    				map.put(sum, 1L);
    			}
    		}
    	}
    }
}