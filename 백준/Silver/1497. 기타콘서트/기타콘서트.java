import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[][] songs = new String[N][2];
		for(int i = 0; i < N; i++) {
			songs[i] = br.readLine().split(" ");
		}
		
		System.out.println(mainSolution(N, M, songs));
	}
	
	static int mainSolution(int N, int M, String[][] songs) {
		
		String[] guitarList = new String[N];
		long[] songList = new long[N];
		for(int i = 0; i < songs.length; i++) {
			guitarList[i] = songs[i][0];
			
			for(int j = 0; j < M; j++) {
				if(songs[i][1].charAt(j) == 'Y') songList[i] |= (1L<<j);
			}
		}
		
		int maxSong = 0;
		int minGuitar = (1 << 31) - 1;
		
		// 사용할 기타의 경우의 수 (1<<N - 1) 가지
		for(int i = 1; i < (1 << N); i++) {
			int usedGuitar = 0;
			long playedSong = 0;
			
			for(int j = 0; j < N; j++) {
				if((i & (1<<j)) == 0) continue;
				
				usedGuitar++;
				playedSong |= songList[j];
			}
			
			if(playedSong == 0) continue;
			
			if(Long.bitCount(playedSong) > maxSong) {
				minGuitar = usedGuitar;
				maxSong = Long.bitCount(playedSong);
			} else if (Long.bitCount(playedSong) == maxSong) {
				if(usedGuitar < minGuitar) {
					minGuitar = usedGuitar;
				}
			}
		}	
		return minGuitar == (1 << 31) - 1 ? -1 : minGuitar;
	}
}