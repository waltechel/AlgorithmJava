import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainGENIUS_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {
			st = new StringTokenizer(br.readLine());

			int N, K, M;
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int[] L = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				L[i] = Integer.parseInt(st.nextToken());
			}

			double[][] T = new double[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					T[i][j] = Double.parseDouble(st.nextToken());
				}
			}

			// 공간최적화 필요
			// dp 배열[K][N][2] : K분대일 때 N번 노래가 마지막인가 아닌가
			// 10분 안에는 대충 한번 순환된다.
			final int MOD = 10;
			double[][][] dp = new double[MOD][N][2];
			for (int i = 0; i < L[0] - 1; i++) {
				dp[i % MOD][0][0] = 1;
			}
			dp[(L[0] - 1) % MOD][0][1] = 1;
			for (int i = 0; i <= K; i++) {
				for (int from = 0; from < N; from++) {
					if (dp[i % MOD][from][1] > 0) {
						for (int to = 0; to < N; to++) {
							for (int nextLength = 1; nextLength < L[to]; nextLength++) {
								if (i + nextLength <= K) {
									dp[(i + nextLength) % MOD][to][0] += dp[i % MOD][from][1] * T[from][to];
								}
							}
							if (i + L[to] <= K) {
								dp[(i + L[to]) % MOD][to][1] += dp[i % MOD][from][1] * T[from][to];
							}
						}
					}
					dp[(i + MOD - 1) % MOD][from][0] = 0;
					dp[(i + MOD - 1) % MOD][from][1] = 0;
					
				}
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int index = Integer.parseInt(st.nextToken());
				bw.write(String.format("%.10f", (dp[K % MOD][index][0] + dp[K % MOD][index][1])) + " ");
			}
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
