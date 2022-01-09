import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 생각거리
 * 1. 문제의 정답이 int 범위 안에 있다고 해도 다른 값이 int 를 넘을 수 있다.(좋지는 않은 문제)
 * 2. 값을 먼저 받은 다음에 큰 부분에서 값이 오는 것만 따로 계산해도 되는 문제
 * @author leedongjun
 *
 */
public class MainTRIPATHCNT_dbfldkfdbgml {
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] A = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= i; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] dp = new int[N + 1][N + 1];
			dp[1][1] = 1;

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= i; j++) {
					if (A[i - 1][j - 1] > A[i - 1][j]) {
						A[i][j] += A[i - 1][j - 1];
						dp[i][j] += dp[i - 1][j - 1];
					} else if (A[i - 1][j - 1] < A[i - 1][j]) {
						A[i][j] += A[i - 1][j];
						dp[i][j] += dp[i - 1][j];
					} else {
						A[i][j] += A[i - 1][j - 1];
						dp[i][j] += dp[i - 1][j - 1];
						dp[i][j] += dp[i - 1][j];
					}
				}
			}

			int max_value = 0;
			int max_dp = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (max_value < A[i][j]) {
						max_value = A[i][j];
						max_dp = dp[i][j];
					} else if (max_value == A[i][j]) {
						max_value = A[i][j];
						max_dp += dp[i][j];
					}
				}
			}

			bw.write(max_dp + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}