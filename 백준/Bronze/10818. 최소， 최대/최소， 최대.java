import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int howMany = scanner.nextInt();
        int[] numbers = new int[howMany];

        for (int i = 0; i < howMany; i++) {
            numbers[i] = scanner.nextInt();
        }

        int maxNumber = numbers[0];
        int minNumber = numbers[0];

        for (int i = 1; i < howMany; i++) {
            if (numbers[i] > maxNumber) {
                maxNumber = numbers[i];
            }
            if (numbers[i] < minNumber) {
                minNumber = numbers[i];
            }
        }
        System.out.print(minNumber + " " + maxNumber);
    }
}