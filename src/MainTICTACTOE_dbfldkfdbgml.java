import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * tsp 문제와 동일하다고 생각하는 것이 대단한 것 같다.
 * 
 * @author leedongjun
 *
 */
public class MainTICTACTOE_dbfldkfdbgml {

	private static char[][] map;
	private static RESULT[] dp;
	private static final int MAX = 19683;

	private static enum RESULT {
		WIN, TIE, LOSE, UNKNOWN
	}

	public static final void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		dp = new RESULT[MAX];
		for (int i = 0; i < MAX; i++) {
			dp[i] = RESULT.UNKNOWN;
		}
		int C = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < C; test_case++) {
			map = new char[3][3];
			for (int i = 0; i < 3; i++) {
				map[i] = br.readLine().toCharArray();
			}

			int phase = countPhase();

			char turn = phase % 2 == 0 ? 'x' : 'o';
			char next = (char) ('o' + 'x' - turn);
			RESULT ret = dfs(turn);
			if (ret == RESULT.WIN) {
				bw.write(turn + "\n");
			} else if (ret == RESULT.LOSE) {
				bw.write(next + "\n");
			} else {
				bw.write("TIE\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int countPhase() {
		int phase = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != '.') {
					phase++;
				}
			}
		}
		return phase;
	}

	private static RESULT dfs(char turn) {
		char next = (char) ('o' + 'x' - turn);
		if (isFinished(next)) {
			return RESULT.LOSE;
		}
		int index = encoding();
		if (dp[index] != RESULT.UNKNOWN) {
			return dp[index];
		}
		RESULT ret = RESULT.UNKNOWN;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == '.') {
					map[i][j] = turn;
					ret = min(ret, parse(dfs(next)));
					map[i][j] = '.';
				}
			}
		}
		if (ret == RESULT.UNKNOWN) {
			return dp[index] = RESULT.TIE;
		} else {
			return dp[index] = ret;
		}
	}

	private static int encoding() {
		int ret = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				ret *= 3;
				if (map[i][j] == 'x') {
					ret += 1;
				}
				if (map[i][j] == 'o') {
					ret += 2;
				}
			}
		}
		return ret;
	}

	private static boolean isFinished(char c) {
		boolean finished = true;
		for (int i = 0; i < 3; i++) {
			finished = true;
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != c) {
					finished = false;
				}
			}
			if (finished) {
				return true;
			}
		}
		finished = true;
		for (int j = 0; j < 3; j++) {
			finished = true;
			for (int i = 0; i < 3; i++) {
				if (map[i][j] != c) {
					finished = false;
				}
			}
			if (finished) {
				return true;
			}
		}
		if (map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[2][2] == c) {
			return true;
		}
		if (map[2][0] == map[1][1] && map[1][1] == map[0][2] && map[0][2] == c) {
			return true;
		}
		return false;
	}

	private static RESULT min(RESULT ret, RESULT parse) {
		return ret.ordinal() < parse.ordinal() ? ret : parse;
	}

	private static RESULT parse(RESULT next) {
		if (next == RESULT.WIN) {
			return RESULT.LOSE;
		} else if (next == RESULT.LOSE) {
			return RESULT.WIN;
		} else {
			return RESULT.TIE;
		}
	}

}
