import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 
 * @author leedongjun
 *
 * - 그냥 막 풀기
 * - 3으로 나워서 remainCount가 3의 배수가 아닌 경우에는 답이 아예 없는 경우도 있음
 * . 는 흰 칸을 나타냅니다. 입력에 주어지는 게임판에 있는 흰 칸의 수는 50 을 넘지 않습니다.
 * 해보니까 500개 넘는 경우가 많아 보이지 않는다.
 * - 마지막 줄은 솔직히 볼 게 없다. 이미 이전 줄에서 채우겠습니다.
 */
public class MainBOARDCOVER_dbfldkfdbgml {

	private static int remainCount;
	private static int answer;
	private static int[][] map;
	private static int[][][] directions = new int[][][] { { { 0, 0 }, { 0, 1 }, { 1, 1 } }, //
			{ { 0, 0 }, { 0, 1 }, { 1, 0 } }, //
			{ { 0, 0 }, { 1, -1 }, { 1, 0 } }, //
			{ { 0, 0 }, { 1, 1 }, { 1, 0 } } };//
	private static int R, C;
	// 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {

			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			remainCount = 0;
			answer = 0;
			map = new int[R][C];
			for (int i = 0; i < R; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					map[i][j] = line[j] == '#' ? 1 : 0;
					if (map[i][j] == 0) {
						remainCount++;
					}
				}
			}
			
			// 못 채우는 경우도 사실 있음
			if(remainCount % 3 != 0) {
				bw.write(answer + "\n");
				continue;
			}

			FOR: for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							if (isSafe(i, j, k)) {
								dfs(i, j, k);
								clear(i, j, k);
							}
						}
						break FOR;
					}
				}
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int r, int c, int k) {
		check(r, c, k);

		if (remainCount == 0) {
			answer++;
			return;
		}

		FOR: for (int nextI = r; nextI < R; nextI++) {
			for (int nextJ = nextI == r ? c + 1 : 0; nextJ < C; nextJ++) {
				if (map[nextI][nextJ] == 0) {
					for (int nextk = 0; nextk < 4; nextk++) {
						if (isSafe(nextI, nextJ, nextk)) {
							dfs(nextI, nextJ, nextk);
							clear(nextI, nextJ, nextk);
						}
					}
					break FOR;
				}
			}
		}

	}

	private static void clear(int r, int c, int k) {
		for (int d = 0; d < 3; d++) {
			int newRow = r + directions[k][d][0];
			int newCol = c + directions[k][d][1];
			map[newRow][newCol] = 0;
		}
		remainCount += 3;
	}

	private static void check(int r, int c, int k) {
		for (int d = 0; d < 3; d++) {
			int newRow = r + directions[k][d][0];
			int newCol = c + directions[k][d][1];
			map[newRow][newCol] = 1;
		}
		remainCount -= 3;
	}

	private static boolean isSafe(int r, int c, int k) {
		for (int d = 0; d < 3; d++) {
			int newRow = r + directions[k][d][0];
			int newCol = c + directions[k][d][1];
			if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && map[newRow][newCol] == 0) {

			} else {
				return false;
			}
		}
		return true;
	}

}