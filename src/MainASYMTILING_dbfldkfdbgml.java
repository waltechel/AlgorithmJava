import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainASYMTILING_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		long[] A = new long[101];
		final int MOD = 1_000_000_007;
		A[1] = 1;
		A[2] = 2;
		for (int i = 3; i <= 100; i++) {
			A[i] = A[i - 1] + A[i - 2];
			A[i] %= MOD;
		}

		long[] dp = new long[101];
		for (int i = 3; i <= 100; i++) {
			if (i % 2 == 1) {
				dp[i] = A[i] - A[(i - 1) / 2];
			} else {
				dp[i] = A[i] - A[(i - 2) / 2] - A[i / 2];
			}
			while (dp[i] <= 0) {
				dp[i] += MOD;
			}
			dp[i] %= MOD;
		}

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			bw.write(dp[n] + "\n");
		}
		bw.flush();
		br.close();
		bw.close();

	}

}