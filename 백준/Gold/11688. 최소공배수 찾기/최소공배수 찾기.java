import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		long L = Long.parseLong(st.nextToken());		
		
		long lcm = LCM(a, b);
		ArrayList<Long> divisorList = divisor(L);
		for(long num : divisorList) {
			if(LCM(lcm, num) == L) {
				System.out.println(num);
				return;
			}
		}
		
		System.out.println("-1");	
	}
	
	// 파라미터의 약수 구하기
	static ArrayList<Long> divisor(long num) {
		ArrayList<Long> list = new ArrayList<>();
		
		for(long i = 1; i < Math.sqrt(num); i++) {
			if(num % i == 0) {
				list.add(i);
				list.add(num / i);
			}
		}
		
		Collections.sort(list);
		return list;
	}
	
	// 최대공약수(GCD) 구하기 (유클리드호제법)
	static long GCD(long a, long b) {
		return b == 0 ? a : GCD(b, a % b);
	}
	
	// 최소공배수(LCM) 구하기
	static long LCM(long a, long b) {
		return (a * b) / GCD(a, b);
	}
}