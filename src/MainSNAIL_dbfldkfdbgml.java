import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainSNAIL_dbfldkfdbgml {

	private static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// M일 안에 달팽이가 N의 높이를 올라갈 확률
		double dp[][] = new double[1001][2001];
		dp[0][0] = 1;
		for (int i = 0; i <= 1000; i++) {
			for (int j = 0; j <= 2000; j++) {
				if (i + 1 <= 1000 && j + 1 <= 2000) {
					dp[i + 1][j + 1] += dp[i][j] * 0.25;
				}
				if (i + 1 <= 1000 && j + 2 <= 2000) {
					dp[i + 1][j + 2] += dp[i][j] * 0.75;
				}
			}
		}
		for (int i = 0; i <= 1000; i++) {
			for (int j = 1999; j >= 0; j--) {
				dp[i][j] += dp[i][j + 1];
			}
		}

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // 장마 기간의 길이
			System.out.printf("%.10f\n", dp[M][N]);

		}
		bw.flush();
		br.close();
		bw.close();

	}

}