import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		System.out.printf("%.1f", Double.parseDouble(str[0]) * Double.parseDouble(str[1]) / 2);
	}
}