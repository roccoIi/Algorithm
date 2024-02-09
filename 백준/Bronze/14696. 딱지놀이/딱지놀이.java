import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st1.nextToken());
            int M = Integer.parseInt(st2.nextToken());
            int[] arr = new int[N+1];
            int[] arr2 = new int[M+1];
            String result = "";

            arr[0] = 0; arr2[0] = 0;

            for(int i = 1; i < arr.length; i++){
                arr[i] = Integer.parseInt(st1.nextToken());
            }

            for(int i = 1; i < arr2.length; i++){
                arr2[i] = Integer.parseInt(st2.nextToken());
            }

            Arrays.sort(arr);
            Arrays.sort(arr2);

            while (true) {
                if(arr[N] > arr2[M]){
                    result = "A";
                    break;
                } else if(arr[N] < arr2[M]){
                    result = "B";
                    break;
                }

                if (--N < 0 || --M < 0) break;
            }

            if (result.isEmpty()) {
                System.out.println("D");
            } else {
                System.out.println(result);
            }
        }
    }
}