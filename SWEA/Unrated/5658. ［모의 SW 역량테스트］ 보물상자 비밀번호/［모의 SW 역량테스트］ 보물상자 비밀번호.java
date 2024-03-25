import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;


/*
 * [구현]
 * 
 *  1. 뚜껑을 한바퀴 돌릴때마다 뒤에 숫자가 하나씩 앞으로 온다. (여기선 앞의 숫자를 뒤로 보냈다. 동일하다)
 *  	=> Queue로 구현
 *  2. 중복된 숫자는 없어야 한다.
 *  	=> Set로 구현
 *  
 *  3. 뚜껑을 돌릴때마다 각 변의 문자열을 set에 저장해 놓는다. 여기서 중복된 String은 알아서 걸러진다. (set는 중복 허용x)
 *  4. 한 변에는 int count = (총 문자 개수를 4로 나눈 몫)만큼 들어있으므로 count만큼만 반복하면 된다.
 *  5. count만큼 반복하여 모든 문자열을 set에 담았다면 하나씩 꺼내면서 10진수로 변환해 숫자 배열에 넣는다.
 *  6. 모두 담아진 숫자배열을 내림차순으로 배열 후 target번째 숫자를 출력한다.
 * */


public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int T = 1; T <= testCase; T++) {
			Set<String> set = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 총 문자 개수
			int target = Integer.parseInt(st.nextToken()); // 반환할 숫자의 크기
			int count = N/4; // 보물상자의 뚜껑 한 변에는 4로 나눈 몫만큼의 숫자가 적혀있다.
			
			// 주어진 문자열을 큐에 넣는다.
			String line = br.readLine();
			Queue<String> q = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				char tmp = line.charAt(i);
				q.add(Character.toString(tmp));
			}
			
			// 뚜껑을 돌리면서 나오는 문자열들을 모두 set에 담는다.
			// 뚜껑의 4개의 변을 모두 넣었다면 큐 맨앞에서 문자 하나 꺼내 뒤로 넣고 다시 반복한다.
			for(int i = 0; i < count; i++) {
				for(int j = 0; j < 4; j++) {
					String str = "";
					for(int k = 0; k < count; k++) {
						String tmp = q.poll();
						str += tmp;
						q.offer(tmp);
					}
					set.add(str);
				}
				String tmp = q.poll();
				q.offer(tmp);
			}
			
			// 만들어진 set을 10진수로 변환하면서 숫자배열에 넣는다.
			int size = set.size();
			Integer[] arr = new Integer[size];
			Iterator<String> iter = set.iterator();
			for(int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(iter.next(), 16);
			}
			
			
			// 내림차순 정렬
			Arrays.sort(arr, Collections.reverseOrder());
			// 출력
			System.out.printf("#%d %d\n",T,arr[target-1]);
		}
	}
}