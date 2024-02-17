import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    *  <풀이방식>
    *   1. 전체 종이조각의 가로세로 길이만 받는다. (따로 배열 생성 X)
    *   2. 입력 들어오는 가로절취, 세로 절취를 Queue 로 받는다. 이때, 추후 출력은 내림차순으로 진행될 예정이다.
    *   3. 전체 가로 길이에서부터 가까운 절취선을 내림차순으로 받으면서 잘린부분의 길이를 구하고 이 길이들의 최댓값을 저장한다.
    *   4. 세로방향도 마찬가지로 구하면서 잘린 부분의 가로, 세로 길이가 가장 클 때의 길이를 구한다.
    *   5. 최댓값끼리 곱해서 최대 넓이를 구한다.
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxWidth = Integer.MIN_VALUE; // 잘린 종이조각의 최대가로, 세로 길이
        int maxHeight = Integer.MIN_VALUE;
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        // 기본 우선순위큐는 오름차순이다. 이것을 내림차순으로 변환해준다.
        Queue<Integer> wQ = new PriorityQueue<>((a,b) -> { return b-a;});
        Queue<Integer> hQ = new PriorityQueue<>((a,b) -> { return b-a;});

        // 가로, 세로별로 절취선을 넣어준다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                wQ.offer(Integer.parseInt(st.nextToken()));
            } else {
                hQ.offer(Integer.parseInt(st.nextToken()));
            }
        }

        int tmp;
        // 가로 진행
        while (!wQ.isEmpty()) {
            tmp = width - wQ.poll(); // 잘린 조각의 길이 = 전체 길이 - 절취선 길이
            if(tmp > maxWidth) maxWidth = tmp; // 만약 잘린 조각의 길이가 최댓값이라면 저장
            width -= tmp; // 전체 길이에서 잘린 조각의 길이를 빼서 남은 조각의 길이로 남겨놓는다.
        }
        if(width > maxWidth) maxWidth = width; // 모두 다 자른 후 남은 조각의 길이가 크다면 최댓값 갱신

        // 세로 진행
        while (!hQ.isEmpty()) {
            tmp = height - hQ.poll();
            if(tmp > maxHeight) maxHeight = tmp;
            height -= tmp;
        }
        if(height > maxHeight) maxHeight = height;

        System.out.println(maxWidth * maxHeight);

    }
}