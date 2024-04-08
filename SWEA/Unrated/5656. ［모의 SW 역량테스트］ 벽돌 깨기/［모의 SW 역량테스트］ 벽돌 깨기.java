import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/* [회고]
* 1. 사실 가장 걱정되는건 최대 12줄의 중복순열을 모두 구하면 12! 이 나오는데 이게 과연 시간초과가 안나올까? 였다.
* 2. 함수가 복잡해서 중간중간 자잘한 오류들을 잡는데 시간이 오래걸렸다.
*   1) 한방향으로 계속 진행해야하는데 델타배열을 안더해줬다던가 (nr += dir[0][d])
*   2) 괜히 리스트에 removefirst로 없애보려다가 오류가 난다던가 (get(i)로 변경)
*   3) 불필요한 조건을 bfs내에 넣어서 반복이 진행되지 않는다던가
*
* [메소드]
* 1. drop: 순열을 구하고 그 순열에 맞춰 돌을 떨어뜨릴 준비를 한다.
* 2. bfs: 본격적인 게임이다. 위에서 돌을 떨어뜨려 해당되는 돌을을 모두 0으로 만들고 그 수를 센다.
* 3. findTop: 각 열별로 가장 위에 있는 0이 아닌 수가 어디있는지 파악한다. 그 좌표를 파악해야 시작점 node로 만들 수 있다.
* 4. copyMap: N번 반복을 진행해보고 다음 순열을 진행할땐 새 지도가 필요하다. 지도를 복사할때마다 사용할 메소드다.
* 5. clear: 돌들을 아래로 모아준다. 각 열별로 리스트에 숫자를 넣고 다 0으로 만들어준다음에 아래서부터 차례대로 채워준다.
* 6. check: 전통의 경계조건 함수다.
*/

public class Solution {
    static int N, R, C, maxBeads, totalBeads;
    static int[] line;
    static boolean[] visit;

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] dir = { { 0, 0, 1, -1 },
                            { -1, 1, 0, 0 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int T = 1; T <= tc; T++) {
            // 기본적으로 제공되는 인자들을 받고 기본적으로 배열생성 및 출력하는 곳
            sb.append("#").append(T);
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            line = new int[N];
            visit = new boolean[N];
            maxBeads = Integer.MIN_VALUE;
            totalBeads = 0;

            int[][] arr = new int[R][C];

            for(int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < C; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                    if(arr[r][c] != 0) totalBeads++;
                }
            }

            drop(0, arr);
            sb.append(" ").append(totalBeads - maxBeads).append("\n");
        } // testCase 종료
        System.out.println(sb);
    }// main 종료

    static void drop(int idx, int[][] arr) {
        if (idx == N) {

            int sum = 0;
            int[][] tmp = copyMap(arr);

            for(int i = 0; i < N; i++) {
                Node node = findTop(line[i], tmp); // 시작점 찾는데
                if(node.r == 99 && node.c == 99) continue; // 해당 비어있으면 패쓰
                sum += bfs(node, tmp); // 시작점 들고 bfs탄다.
                clear(tmp); // 지도 정리
            }

            maxBeads = Math.max(maxBeads, sum); // 최댓값 갱신
            return;
        }

        for (int i = 0; i < C; i++) { // 중복순열 생성
            line[idx] = i;
            drop(idx + 1, arr);
        }
    } // drop 종료

    static int bfs(Node node, int[][] map) {
        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(node);

        while(!q.isEmpty()) {
            Node beads = q.poll();
            int size = map[beads.r][beads.c];
            
            if(size >= 1) { // 해당 위치가 1이라면 카운트는 올라가지만 1이상일때만 while문으로 들어간다. 0이면 얄짤없다.
                cnt++;
                map[beads.r][beads.c] = 0;
                if(size == 1) continue;
            } else if(size == 0) {
                continue;
            }


            for(int d = 0; d < 4; d++) {
                int nr = beads.r + dir[0][d];
                int nc = beads.c + dir[1][d];
                int times = 1;
                while(check(nr, nc) && times != size) { // 경계조건을 벗어나지 않거나 해당 위치 사이즈까지 도달하지 않았으면 반복
                    q.offer(new Node(nr, nc));
                    times++;
                    nr += dir[0][d];
                    nc += dir[1][d];
                }
            }
        }
        return cnt;
    }// delete 종료

    static Node findTop(int c, int[][] map) {
        // 아래서부터 올라오면서 0을 발견했을 때 그 아래 위치를 반환한다.
        // 만약 끝까지 올라갔다면 모두 0이니깐 99, 99 반환 (이건 나중에 패쓰하는데 쓰인다)
        // 끝까지 올라갔는데 0이 없으면 맨 위를 반환한다.
        int r = R-1;
        while(r >= 0) {
            if(map[r][c] == 0) {
                if(r == R-1) return new Node(99, 99);
                return new Node(r+1, c);
            }
            r--;
        }
        return new Node(r+1, c);
    } // findTop 종료

    //그냥 반복문 돌면서 지도 복사한다.
    static int[][] copyMap(int[][] map){
        int[][] tmp = new int[R][C];
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                tmp[r][c] = map[r][c];
            }
        }
        return tmp;
    } // copyMap 종료

    // 해당 열을 아래서부터 읽으면서 0이 아닌것만 리스트에 모으고 다 0으로 만든다
    // 다시 올라가면서 아래서부터 차례로 올려놓는다.
    static void clear(int[][] map) {
        for(int c = 0; c < C; c++) {
            List<Integer> tmp = new ArrayList<>();
            for(int r = R-1; r >=0; r--) {
                if(map[r][c] != 0) {
                    tmp.add(map[r][c]);
                    map[r][c] = 0;
                }
            }
            for(int r = 0; r < tmp.size(); r++) {
                map[R-1-r][c] = tmp.get(r);
            }
        }
    }
    
    // 역사와 전통의 방문체크 함수
    static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    } // check 종료
}