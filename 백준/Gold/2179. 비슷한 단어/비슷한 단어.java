import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 입력된 순서를 기억하기 위한 map
		TreeMap<String, Integer> indexMap = new TreeMap<>();
		String[] words = new String[N];
		for(int i = 0; i < N; i++) {
			words[i] = br.readLine();
			indexMap.put(words[i], i);
		}
		
		// 단어들 오름차순으로 정렬
		Arrays.sort(words);
		
		HashMap<String, List<String>>prefixMap = new HashMap<>();
		ArrayList<String> answerOptions = new ArrayList<>();
		String nowPrefix = null;
		int maxCount = -1;
		for(int i = 0; i < words.length - 1; i++) {
			String curr = words[i];
			String next = words[i+1];
			String prefix = getPrefix(curr, next);
			
			if(prefix == "" || prefix.length() < maxCount) continue;
			
			// 지금보다 길이가 더 긴 접두사를 발견했을 경우 정답 후보리스트 초기화 진행
			if(prefix.length() > maxCount) answerOptions = new ArrayList<>();
			
			if(nowPrefix == null || !nowPrefix.equals(prefix)) {
				// prefixMap 초기화 및 단어 추가
				prefixMap.putIfAbsent(prefix, new ArrayList<>());
				List<String> list = prefixMap.get(prefix);
				list.add(curr);
				list.add(next);
				
				// 접두사 최대길이 갱신
				maxCount = prefix.length();
				
				// 현재 탐색중인 접두사 갱신
				nowPrefix = prefix;	
			} else {
				// prefixMap 단어 추가
				List<String> list = prefixMap.get(prefix);
				list.add(next);
			}
			
			// 중복되는 후보라면 넘긴다
			if(answerOptions.size() > 0 && answerOptions.get(answerOptions.size()-1).equals(prefix)) continue;
			
			// 정답 후보리스트에 접두사 추가
			answerOptions.add(prefix);
		}
		
		String[] answer = new String[2];
		for(String prefix : answerOptions) {
			String[] tempAnswer = new String[2];
			List<String> list = prefixMap.get(prefix);
			
			if(tempAnswer[0] == null) tempAnswer[0] = list.get(0);
			
			for(int i = 1; i < list.size(); i++) {
				if(indexMap.get(list.get(i)) < indexMap.get(tempAnswer[0])) {
					tempAnswer[1] = tempAnswer[0];
					tempAnswer[0] = list.get(i);
				} else {
					if(tempAnswer[1] == null || indexMap.get(list.get(i)) < indexMap.get(tempAnswer[1]))
						tempAnswer[1] = list.get(i);
				}
			}
			
			if(answer[0] == null || indexMap.get(tempAnswer[0]) < indexMap.get(answer[0])) {
				for(int i = 0; i < 2; i++) answer[i] = tempAnswer[i];
			}
		}
		
		for(String word : answer) {
			System.out.println(word);
		}
	}
	
	static String getPrefix(String curr, String next) {
		int num = Math.min(curr.length(), next.length());
		for(int i = 0; i < num; i++) {
			if(curr.charAt(i) != next.charAt(i)) {
				return curr.substring(0, i);
			}
		}
		return curr.substring(0, num);
	}
}