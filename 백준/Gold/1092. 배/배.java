import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 총 크레인의 수
		int N = Integer.parseInt(br.readLine());
		
		// 각 크레인들의 무게제한
		List<Integer> cranes = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cranes.add(Integer.parseInt(st.nextToken()));
		}
		
		// 총 박스의 수
		int M = Integer.parseInt(br.readLine());
		
		// 총 박스들의 무게
		List<Integer> boxes = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			boxes.add(Integer.parseInt(st.nextToken()));
		}
		
		// 전체 내림차순 정렬
		cranes.sort(Collections.reverseOrder());
		boxes.sort(Collections.reverseOrder());
		
		// 가장 큰 무게 제한을 가진 크레인도 들지 못하는 박스가 있을경우 -1을 출력하고 종료
		if(cranes.get(0) < boxes.get(0)) {
			System.out.println(-1);
			return;
		}
		
		
		int hours = 0;
		while(!boxes.isEmpty()) {
			int boxIdx = 0;
			int craneIdx = 0;
			
			// 현재의 크레인 idx가 전체 크레인 수를 넘어가지 않을때 까지만 진행
			while(craneIdx < N) {
				
				// 만약 현재 idx와 box리스트의 사이즈가 같다면 넘어가기 위해 종료
				// (1. 끝까지 가도 조건에 맞지 않아 boxIdx가 넘어버렸을 경우,
				//  2. 현재의 box리스트가 비어있을 경우)
				if(boxIdx >= boxes.size()) {
					break;
				}else if(cranes.get(craneIdx) >= boxes.get(boxIdx)) {
					boxes.remove(boxIdx);
					craneIdx++;
				} else {
					boxIdx++;
				}
			}
			
			hours++;
		}
		System.out.println(hours);
	}
}