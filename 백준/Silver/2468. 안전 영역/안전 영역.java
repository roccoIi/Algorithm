import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
 
public class Main {
    static boolean[][] table;
    static int n;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
         
            n = Integer.parseInt(br.readLine().split(" ")[0]);
            table = new boolean[n][n];
            int[][] arr = new int[n][n];
             
             
            for(int i=0;i<n;i++) {
                String[] str = br.readLine().split(" ");
                for(int j=0;j<n;j++) {
                    arr[i][j]=Integer.parseInt(str[j]);
                }
            }
            int max=0;
            for(int time = 0; time<=100;time++) {
                int cnt=0;
                for(int i=0;i<n;i++) {
                    for(int j=0;j<n;j++) {
                        if(arr[i][j]<=time) {
                            table[i][j]=true;
                        } else {
                            table[i][j]=false;
                        }
                    }
                }
                 
                for(int i=0;i<n;i++) {
                    for(int j=0;j<n;j++) {
                        if(!table[i][j]) {
                            move(i,j);
                            cnt++;
                        }
                    }
                }
                 
                if(cnt!=n*n) {
                    max=Math.max(cnt, max);
                }
            }
             
             
            System.out.println(max);
        
    }
     
    static void move(int i, int j) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{i,j});
        while(!queue.isEmpty()) {
            Integer[] current = queue.poll();
            int r = current[0];
            int c = current[1];
             
            if(!(0<=r && r<n && 0<=c && c<n) || table[r][c]) {
                continue;
            }
             
            table[r][c]=true;
             
            queue.add(new Integer[]{r + 1, c});
            queue.add(new Integer[]{r - 1, c});
            queue.add(new Integer[]{r, c + 1});
            queue.add(new Integer[]{r, c - 1});
        }
    }
}