import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] student = new int[30];

        for (int i = 0; i < 30; i++) {
            student[i] = i+1;
        }
        for (int i = 0; i < 28; i++) {
            int a = scanner.nextInt();
            if (a == student[a - 1]) {
                student[a-1] = 0;
            }
        }
        for (int i = 0; i < 30; i++) {
            if (student[i] != 0) {
                System.out.println(student[i]);
            }
        }
    }
}