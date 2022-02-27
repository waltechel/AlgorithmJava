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
public class MainRESTORE_dbfldkfdbgml {

	private static int M;
	private static int[][] dp;
	private static int[][] overlap;
	private static ArrayList<String> list;

	public static final void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < C; test_case++) {
			int N = Integer.parseInt(br.readLine());
			String[] A = new String[N];
			for (int i = 0; i < N; i++) {
				A[i] = br.readLine().trim();
			}
			boolean[] unnecessary = new boolean[N];
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (A[i].contains(A[j])) {
						unnecessary[j] = true;
					}
				}
			}

			// list를 만들기
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (unnecessary[i]) {
					continue;
				}
				list.add(A[i]);
			}
			M = list.size();

			// overlap 만들기
			overlap = new int[M][M];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					overlap[i][j] = parse(list.get(i), list.get(j));
				}
			}

			// answer
			int answerLength = list.stream().map(s -> s.length()).reduce(0, (a, b) -> a + b);
			int candiLength = 0;
			dp = new int[1 << M][M];
			for (int i = 0; i < (1 << M); i++) {
				for (int j = 0; j < M; j++) {
					dp[i][j] = -1;
				}
			}
			for (int i = 0; i < M; i++) {
				candiLength = Math.max(candiLength, dfs(1 << i, i));
			}

			// 여기까지가 TSP 문제
			// bw.write(answer - candi + "\n");

			// 되추적하기
			String answerLine = "";
			for (int i = 0; i < M; i++) {
				String candiLine = list.get(i) + dfs2(1 << i, i);
				if (candiLine.length() == answerLength - candiLength) {
					answerLine = candiLine;
					break;
				}
			}
			bw.write(answerLine + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static String dfs2(int state, int now) {
		if (state == (1 << M) - 1) {
			return "";
		}
		String ret = "";
		for (int i = 0; i < M; i++) {
			if ((state & (1 << i)) != 0) {
				continue;
			}
			if (dfs(state, now) == overlap[now][i] + dfs(state | (1 << i), i)) {
				return list.get(i).substring(overlap[now][i]) + dfs2(state | (1 << i), i);
			}
		}

		return ret;
	}

	private static int dfs(int state, int now) {
		if (state == (1 << M) - 1) {
			return 0;
		}
		if (dp[state][now] != -1) {
			return dp[state][now];
		}
		int ret = 0;
		for (int i = 0; i < M; i++) {
			if ((state & (1 << i)) != 0) {
				continue;
			}
			ret = Math.max(ret, overlap[now][i] + dfs(state | (1 << i), i));
		}

		return dp[state][now] = ret;
	}

	private static int parse(String s1, String s2) {
		int ret = Math.min(s1.length(), s2.length());
		for (int i = ret; i >= 1; i--) {
			String substr1 = s1.substring(s1.length() - i);
			String substr2 = s2.substring(0, i);
			if (substr1.equals(substr2)) {
				return i;
			}
		}
		return 0;
	}

}
