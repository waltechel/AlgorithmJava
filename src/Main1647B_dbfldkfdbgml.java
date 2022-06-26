import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 *         It is also clear that if there are 3 such cells,
 *         then there will be two intersecting rectangles.
 *         Therefore, you just need to check if there is a
 *         2×2 square in which there are exactly 3 colored
 *         cells.
 */
public class Main1647B_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int N, M;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			String[] map = new String[N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine();
			}

			boolean flag = false;
			for (int i = 0; i < N - 1; i++) {
				for (int j = 0; j < M - 1; j++) {
					int cnt = 0;
					cnt += Integer.parseInt(map[i].charAt(j) + "");
					cnt += Integer.parseInt(map[i].charAt(j + 1) + "");
					cnt += Integer.parseInt(map[i + 1].charAt(j) + "");
					cnt += Integer.parseInt(map[i + 1].charAt(j + 1) + "");
					if (cnt == 3) {
						flag = true;
					}
				}
			}

			bw.write(flag ? "NO\n" : "YES\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
}