import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 가로
        int M = Integer.parseInt(st.nextToken()); // 세로
        int range = (N+M)*2; // 전체 지도(사각형) 둘레
        int[] map = new int[range]; // 지도생성
        int sum = 0; // 출력할 최종 값
        int dong = 0; // 동근이의 위치를 얻기 위한 초기화
        List<Integer> doub = new ArrayList<>(); //중복 상점들의 리스트


        int time = Integer.parseInt(br.readLine());

        for(int i = 1; i <= time + 1; i++) { // time + 1 은 동근이의 위치
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st2.nextToken());
            int length = Integer.parseInt(st2.nextToken());
            // 각 방향별 배열 위치 할당.
            if(direction == 1) {
                if(map[length] != 0) // 만일 상점이 중복될 경우
                    doub.add(length);// 이전에 입력한 상점의 위치를 List에 넣어놓는다.
                map[length] = i;

            } else if(direction == 2) {
                if(map[range - M - length] != 0)
                    doub.add(range - M - length);
                map[range - M - length] = i;

            } else if(direction == 3) {
                if(map[range - length] != 0)
                    doub.add(range - length);
                map[range - length] = i;

            } else if(direction == 4) {
                if(map[N + length] != 0)
                    doub.add(N + length);
                map[N + length] = i;
            }
        }

        // 동근이 위치 찾기
        for(int i = 0; i < map.length; i++) {
            if(map[i] == time+1) {
                dong = i;
                break;
            }
        }
        // 1. 동근이부터 상점까지의 거리의 절댓값 => path
        // 2. 동근이가 기점(0.0)을 거쳐 상점까지의 거리 => range - path
        // 둘 중 더 작은 수를 sum에 더하여 최종 출력한다.
        int path = 0;
        for(int i = 1; i <= time; i++) {
            for(int j = 0; j < map.length; j++) {
                if(map[j] == i) {
                    path = Math.abs(dong - j);
                    sum += Math.min(path, range - path);
                    break;
                }
            }
        }

        // 지금까지 중복되었던 상점들의 거리도 더한다.
        for(int i = 0; i < doub.size(); i++) {
            path = Math.abs(dong - doub.get(i));
            sum += Math.min(path, range - path);
        }
        System.out.println(sum);
    }
}