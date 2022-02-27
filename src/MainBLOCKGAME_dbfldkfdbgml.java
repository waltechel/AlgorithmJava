import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * 
 * @author leedongjun
 *
 */
public class MainBLOCKGAME_dbfldkfdbgml {

	private static char[][] map;
	private static int [][][] BOARDS = new int[][][] { 
		// 두 칸도 겹치기 세 칸으로 한다.
		{{0, 0}, {0, 1}, {0, 0}},
		{{0, 0}, {1, 0}, {0, 0}}, 
		{{0, 0}, {0, 1}, {1, 1}}, 
		{{0, 0}, {0, 1}, {1, 0}}, 
		{{1, 0}, {0, 1}, {1, 1}}, 
		{{0, 0}, {1, 0}, {1, 1}}
	};
	private static final int MAX = 32 * 32 * 32 * 32 * 32;
	private static short [] dp = new short[MAX];
	
	public static final void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int i = 0 ; i < MAX; i++) {
			dp[i] = -1;
		}
		
		int C = Integer.parseInt(br.readLine().trim());
		for (int test = 0; test < C; test++) {
			map = new char[5][5];
			for (int i = 0; i < 5; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			int answer = dfs(0);
			if(answer == 1) {
				bw.write("WINNING\n");
			}else {
				bw.write("LOSING\n");
			}
		
		}

		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * 1 리턴 이긴다
	 * 0 리턴 진다
	 * @param phase
	 * @return 승패
	 */
	private static short dfs(int phase) {
		// 끝나면 내가 이긴 것이다.
		if(isFinished(phase)) {
			return 0;
		}
		int index = encoding();
		if(dp[index] != -1) {
			return dp[index];
		}
		
		// 지는 것
		short ret = 0;
		for(int i = 0 ; i < 5 ; i++) {
			for(int j = 0 ; j < 5 ; j++) {
				for(int k = 0 ; k < BOARDS.length ; k++) {
					if(isOkay(i, j, k)) {
						check(i, j, k);
						ret = (short) Math.max(ret, 1 - dfs(phase + 1));
						rollback(i, j, k);
					}
				}
			}
		}
		return dp[index] = ret;
	}

	private static int encoding() {
		int ret = 0;
		int index = 0;
		for(int i = 0 ; i < 5 ; i++) {
			for(int j = 0 ; j < 5 ; j++) {
				if(map[i][j] != '.') {
					ret += (1 << index);					
				}
				index++;
			}
		}
		return ret;
	}

	private static void rollback(int r, int c, int k) {
		for(int d = 0; d < 3 ; d++) {
			int nextR = r + BOARDS[k][d][0];
			int nextC = c + BOARDS[k][d][1];
			map[nextR][nextC] = '.';				
		}
	}

	private static void check(int r, int c, int k) {
		for(int d = 0; d < 3 ; d++) {
			int nextR = r + BOARDS[k][d][0];
			int nextC = c + BOARDS[k][d][1];
			map[nextR][nextC] = '#';				
		}
	}

	private static boolean isOkay(int r, int c, int k) {
		for(int d = 0; d < 3 ; d++) {
			int nextR = r + BOARDS[k][d][0];
			int nextC = c + BOARDS[k][d][1];
			if(nextR >= 0 && nextR < 5 && nextC >= 0 && nextC < 5 && map[nextR][nextC] == '.') {
				
			}else {
				return false;
			}
		}
		return true;
	}

	/** 
	 * 둘 곳이 있으면 return false;
	 * 둘 곳이 없으면 true
	 * @param phase
	 * @return
	 */
	private static boolean isFinished(int phase) {
		for(int r = 0; r < 5 ; r++) {
			for(int c = 0 ; c < 5 ; c++) {
				for(int b = 0 ; b < BOARDS.length; b++) {
					if(isOkay(r, c, b)) {
						return false;						
					}
				}
			}
		}
		return true;
	}
}
