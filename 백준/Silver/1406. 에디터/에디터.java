import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Stack<Character> mainStack = new Stack<>(); // 입력받는 문자 넣을 스택
        Stack<Character> tmp = new Stack<>(); // 왼쪽으로 이동한 문자 보관할 스택

        String str = br.readLine();
        
        // 입력받은 문자 길이만큼 스택에 넣는다.
        for (int i = 0; i < str.length(); i++) {
            mainStack.add(str.charAt(i));
        }

        int num = Integer.parseInt(br.readLine());
        
        // 각 문자 별로 mainStack과 tmp를 이동한다.
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            char Alphabet = st.nextToken().charAt(0);
            if(Alphabet == 'P'){
                mainStack.add(st.nextToken().charAt(0));
            } else if (Alphabet == 'L') {
                if(!mainStack.isEmpty()) tmp.add(mainStack.pop());
            } else if (Alphabet == 'D') {
                if(!tmp.isEmpty()) mainStack.add(tmp.pop());
            } else if (Alphabet == 'B'){
                if(!mainStack.isEmpty()) mainStack.pop();
            }
        }
        // mainStack에 모인 문자들을 tmp한곳에 모은다.
        while(!mainStack.isEmpty()){
            tmp.add(mainStack.pop());
        }
        // tmp 에 모인 문자를 차례로 출력한다.
        while(!tmp.isEmpty()){
            sb.append(tmp.pop());
        }

        System.out.println(sb);
    }
}