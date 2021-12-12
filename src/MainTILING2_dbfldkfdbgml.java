import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainTILING2_dbfldkfdbgml {

	private static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int[] dp = new int[101];
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3 ; i <= 100 ; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
			dp[i] %= MOD;
		}

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			bw.write(dp[N] + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}