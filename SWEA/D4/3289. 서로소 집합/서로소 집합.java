import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; StringBuilder sb = new StringBuilder();;
		
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= testCase; T++) {
			sb.append("#").append(T).append(" ");
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int test = Integer.parseInt(st.nextToken());
			arr = new int[node+1];
			
			// makeSet 진행
			for(int i = 1; i <=node; i++) {
				arr[i] = i;
			}
			
			// 주어지는 연산들을 입력받는다.
			for(int i = 0; i < test; i++) {
				st = new StringTokenizer(br.readLine());
				int flag = Integer.parseInt(st.nextToken()); // 1인지 0인지 확인
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				if(flag == 0) { // 합연산이다. union 진행
					union(A,B);
				} else { // 대표를 찾는다.
					if(findSet(A) == findSet(B)) { // 주어진 두 숫자의 대표가 같다면 같은 집합에 들어있다.
						sb.append(1);
					} else{
						sb.append(0);
					}
				}
				
			}
			
			sb.append("\n");
		}	
		System.out.println(sb);
		
	}
	// 대표찾기
	static int findSet(int x) {
		
		if(x != arr[x]) {
			arr[x] = findSet(arr[x]);
		}
		
		return arr[x];
	}
	
	// 합연산
	static void union(int x, int y) {
		arr[findSet(y)] = findSet(x);
	}

}