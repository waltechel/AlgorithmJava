import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * dp 배열의 공간 최적화를 수행한다.
 * @author developer
 *
 */
public class MainSUCHI_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken()) / 100;

			int[] costs = new int[n];
			int[] values = new int[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				costs[i] = Integer.parseInt(st.nextToken()) / 100;
				values[i] = Integer.parseInt(st.nextToken());
			}

			// dp 배열의 공간 최적화를 수행한다.
			long answer = 0;
			long[] dp = new long[500];
			for (int i = 0; i <= m; i++) {
				for (int j = 0; j < n; j++) {
					if (i + costs[j] <= m && dp[(i + costs[j]) % 500] < dp[i % 500] + values[j]) {
						dp[(i + costs[j]) % 500] = dp[i % 500] + values[j];
					}
				}
				answer = Math.max(answer, dp[i % 500]);
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
