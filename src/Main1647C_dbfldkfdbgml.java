import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 */
public class Main1647C_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N, M;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			String[] map = new String[N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine();
			}

			ArrayList<int[]> answer = new ArrayList<>();
			if (map[0].charAt(0) == '1') {
				bw.write("-1\n");
				continue;
			}

			for (int j = M - 1; j >= 0; j--) {
				for (int i = N - 1; i >= 0; i--) {
					if (map[i].charAt(j) == '1') {
						if (i != 0) {
							answer.add(new int[] { i, j + 1, i + 1, j + 1 });
						} else {
							answer.add(new int[] { i + 1, j, i + 1, j + 1 });
						}
					}
				}
			}

			bw.write(answer.size() + "\n");
			for (int[] element : answer) {
				bw.write(element[0] + " " + element[1] + " " + element[2] + " "
						+ element[3] + "\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}
}