import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 
 * @author leedongjun
 *
 * 
 */
public class MainTSP1_dbfldkfdbgml {

	private static double[][] graph;
	private static int visited;
	private static int N;
	private static double answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {
			N = Integer.parseInt(br.readLine());
			graph = new double[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					graph[i][j] = Double.parseDouble(st.nextToken());
				}
			}

			answer = Integer.MAX_VALUE;
			visited = 0;
			for (int i = 0; i < N; i++) {
				dfs(i, 0);
				visited &= ~(1 << i);
			}

			bw.write(String.format("%.10f", answer) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int now, double candi) {

		visited |= (1 << now);
		if (visited == (1 << N) - 1) {
			answer = Math.min(answer, candi);
			return;
		}

		for (int next = 0; next < N; next++) {
			if ((visited & (1 << next)) == 0) {
				dfs(next, candi + graph[now][next]);
				visited &= ~(1 << next);
			}
		}

	}
}
