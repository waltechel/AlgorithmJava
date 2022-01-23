import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainTSP2_dbfldkfdbgml {

	private static double[][] dp;
	private static double[][] graph;
	private static double answer;
	private static int N;

	public static final void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < C; test_case++) {
			N = Integer.parseInt(br.readLine());
			graph = new double[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					graph[i][j] = Double.parseDouble(st.nextToken());
				}
			}
			dp = new double[N][1 << N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < (1 << N); j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				answer = Math.min(answer, dfs(i, (1 << i)));
			}

			bw.write(String.format("%.10f", answer) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static double dfs(int now, int visited) {
		if (visited == (1 << N) - 1) {
			return dp[now][visited] = 0;
		}

		if (dp[now][visited] != Integer.MAX_VALUE) {
			return dp[now][visited];
		}

		for (int next = 0; next < N; next++) {
			if ((visited & (1 << next)) != 0) {
				continue;
			}
			dp[now][visited] = Math.min(dp[now][visited], dfs(next, visited | (1 << next)) + graph[now][next]);
		}
		return dp[now][visited];
	}

}