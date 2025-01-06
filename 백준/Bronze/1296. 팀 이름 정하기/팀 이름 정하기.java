import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		String name;
		int point;
		
		Node(String name, int point){
			this.name = name;
			this.point = point;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1) Node의 point를 기준으로 내림차순 정렬 (점수가 높은 순)
		// 2) 점수가 동일할 경우 name을 기준으로 오름차순 정렬 (사전 순)
		PriorityQueue<Node> pq = new PriorityQueue<>((s1, s2) -> {
			if(s1.point == s2.point) {
				return s1.name.compareTo(s2.name);
			} else {
				return Integer.compare(s2.point, s1.point);
			}
		});
		
		// 연두의 영어이름 선 계산
		// L: 0, O: 1, V: 2, E: 3
		String name = br.readLine();
		int[] nameCnt = wordCount(name, new int[name.length()]);
		
		// 팀 이름까지 총합하여 점수계산 후 pq에 입력
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String teamName = br.readLine();
			int point = caculatePoint(wordCount(teamName, nameCnt));
			pq.add(new Node(teamName, point));
		}
		
		// 가장 첫번째 팀 이름 출력
		System.out.println(pq.poll().name);
	}
	
	// 파라미터로 들어온 배열 (주로 연두의 영어이름)을 복사 후 팀이름의 문자를 총합하여 반환
	static int[] wordCount(String name, int[] nameCnt) {
		int[] tmpCnt = Arrays.copyOf(nameCnt, 4);
		
		for(int i = 0; i < name.length(); i++) {
			char nameIdx = name.charAt(i);
			if(nameIdx == 'L') tmpCnt[0]++;
			else if(nameIdx == 'O') tmpCnt[1]++;
			else if(nameIdx == 'V') tmpCnt[2]++;
			else if(nameIdx == 'E') tmpCnt[3]++;
		}
		return tmpCnt;
	}
	
	// 해당 문자열의 점수를 계산하여 반환
	static int caculatePoint(int[] nameCnt) {
		int totalPoint = 1;
		for(int i = 0; i < 3; i++) {
			for(int j = i+1; j < 4; j++) {
				totalPoint *= (nameCnt[i] + nameCnt[j]);
			}
		}
		return totalPoint % 100;
	}
}