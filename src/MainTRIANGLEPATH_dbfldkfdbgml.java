import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainTRIANGLEPATH_dbfldkfdbgml {

	private static String pattern, line;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= i; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] += Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
				}
			}
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					answer = Math.max(answer, dp[i][j]);
				}
			}
			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}