import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] arr = new char[5][15];
        // 각 줄별로 주어지는 문자를 받고 그 문자의 길이만큼반 반복하여 한글자씩 저장
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        String str = "";

        // 세로로 돌면서 글자를 하나씩 str에 붙여넣는다. 단, 비어있을 경우에는 건너뛰기 (char형 배열은 공백일경우 '\u1000'으로 표현)
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if(arr[j][i] == '\u0000'){
                    continue;
                } else {
                    str += arr[j][i];
                }
            }
        }
        System.out.println(str);
    }
}
