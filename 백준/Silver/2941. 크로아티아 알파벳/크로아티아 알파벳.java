import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "aa" + br.readLine();
        int count = -2;

        for (int i = 0; i < str.length(); i++) {
            count++;
            if (str.charAt(i) == '=') {  // 1. =로 끝날 때
                if (str.charAt(i - 1) == 'z') { // z=
                    if (str.charAt(i - 2) == 'd') { // dz=
                        count-=2;
                    } else {
                        count--;
                    }
                } else if (str.charAt(i-1) == 'c') { // c=
                    count--;
                } else if (str.charAt(i-1) == 's') { // s=
                    count--;
                }
            } else if (str.charAt(i) == '-') {  // 2. -로 끝날 때
                if (str.charAt(i-1) == 'c') { // c-
                    count--;
                } else if (str.charAt(i - 1) == 'd') { // d-
                    count--;
                }
            } else if (str.charAt(i) == 'j') { // 3. j로 끝날 때
                if (str.charAt(i - 1) == 'l') { // lj
                    count--;
                } else if (str.charAt(i - 1) == 'n') { // nj
                    count--;
                }
            }
        }
        System.out.println(count);
    }
}