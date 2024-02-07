import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int length = Integer.parseInt(br.readLine());
    	int[] arr = new int[length];
    	int maxNum = Integer.MIN_VALUE; // 최댓값 변수
    	int same = 0; // 동일한 값 있다면 +1

    	// 제시되는 배열 입력
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < arr.length; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	if(arr.length == 1) { // 배열의 길이가 1이라면 for문 없이 maxNum = 1;
    		maxNum = 1;
    	}
    	// 배열의 첫번째 인덱스부터 마지막-1 인덱스 까지 탐색
    	for(int i = 0; i < arr.length-1; i++) {
        	int count = 1 + same; // 매 반복마다 사용하게 될 연속되는 값 카운트
    		if(arr[i] < arr[i+1]) {// 0번보다 1번이 크다면 뒤로도 <= 일때만 카운트, 하나라도 아니라면 break
    			for(int j = i; j < arr.length-1; j++) {
        			if(arr[j] <= arr[j+1]) {
        				count++;
        			} else {
        				same = 0;
        				break;
        			}
        		}
    		} else if(arr[i] > arr[i+1]) { // 0번이 1번보다 크다면 뒤로도 >= 일때만 카운트
    			for(int j = i; j < arr.length-1; j++) {
        			if(arr[j] >= arr[j+1]) {
        				count++;
        			} else {
        				same = 0;
        				break;
        			}
        		}
    		} else if(arr[i] == arr[i+1]){
    			same ++; // 처음 비교하는 두 수가 같으면 +1
    		}
    		if(same == arr.length-1) count++; // 모든 숫자가 같으면 위에 count+1을 못먹으니 하나 더 올려준다.
    		if(count > maxNum) maxNum = count;
    	}
		System.out.println(maxNum);
    }
}