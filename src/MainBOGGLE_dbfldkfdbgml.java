import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 
 * @author leedongjun
 *
 *         뒤에서부터 문제풀이
 */
public class MainBOGGLE_dbfldkfdbgml {

	private static int[][] directions = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 정답은 50 * 10 * 10 * 5 * 5 * 8
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {//입력의 첫 줄에는 테스트 케이스의 수 C(C <= 50)가 주어집니다. 

			char[][] map = new char[5][5];
			for (int i = 0; i < 5; i++) {
				map[i] = br.readLine().toCharArray();
			}
			int N = Integer.parseInt(br.readLine());
			for (int a = 0; a < N; a++) { // 그 다음 줄에는 우리가 알고 있는 단어의 수 N(1 <= N <= 10)이 주어집니다. 
				String line = br.readLine();
				boolean[][][] dp = new boolean[line.length()][5][5];
				boolean answer = false;
				for (int i = 0; i < line.length(); i++) { // 각 단어는 알파벳 대문자 1글자 이상 10글자 이하로 구성됩니다.
					char now = line.charAt(i);
					for (int r = 0; r < 5; r++) { // 첫 줄에는 각 5줄에 5글자
						for (int c = 0; c < 5; c++) {
							if (map[r][c] == now) {
								for (int d = 0; d < directions.length; d++) { // 상하좌우, 혹은 대각선으로 인접한 칸
									int newRow = r + directions[d][0];
									int newCol = c + directions[d][1];
									if (i == 0 || (newRow >= 0 && newRow < 5 && newCol >= 0 && newCol < 5 && dp[i - 1][newRow][newCol])) {
										dp[i][r][c] = true;
										if (i == line.length() - 1) {
											answer = true;
										}
										break;
									}
								}
							}
						}
					}
				}

				bw.write(line + " " + (answer ? "YES" : "NO") + "\n");
			}

		}

		bw.flush();
		br.close();
		bw.close();
	}

}