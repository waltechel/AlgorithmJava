import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainNUMB3RS_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());

			double[][] possiblity = new double[N][N];
			ArrayList<int[]> graph = new ArrayList<>();
			for (int from = 0; from < N; from++) {
				st = new StringTokenizer(br.readLine());
				for (int to = 0; to < N; to++) {
					int k = Integer.parseInt(st.nextToken());
					possiblity[from][to] = k;
					if (k == 1) {
						graph.add(new int[] { from, to });
					}
				}
			}
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += possiblity[i][j];
				}
				for (int j = 0; j < N; j++) {
					possiblity[i][j] /= sum;
				}
			}

			// 벨만 포드 비스무리하게 풀었다
			double dp[][] = new double[D + 1][N];
			dp[0][P] = 1;
			for (int i = 0; i < D; i++) {
				for (int[] e : graph) {
					int from = e[0];
					int to = e[1];
					dp[i + 1][to] += dp[i][from] * possiblity[from][to];
				}
			}

			int t = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < t; i++) {
				int q = Integer.parseInt(st.nextToken());
				bw.write(String.format("%.10f", dp[D][q]) + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();

	}

}
