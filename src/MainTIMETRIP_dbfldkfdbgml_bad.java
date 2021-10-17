import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainTIMETRIP_dbfldkfdbgml_bad {

	private static final int MAX = 1000000;
	private static final int MIN = -1000000;

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			int N, M;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int[][] graph1 = new int[N][N];
			int[][] graph2 = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph1[i][j] = MAX;
					graph2[i][j] = MIN;
				}
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph1[from][to] = Math.min(graph1[from][to], cost);
				graph2[from][to] = Math.max(graph2[from][to], cost);
				visited[from][to] = true;
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (visited[i][k] && visited[k][j]) {
							visited[i][j] = true;
							if (graph1[i][j] > graph1[i][k] + graph1[k][j]) {
								graph1[i][j] = graph1[i][k] + graph1[k][j];
							}
							if (graph2[i][j] < graph2[i][k] + graph2[k][j]) {
								graph2[i][j] = graph2[i][k] + graph2[k][j];
							}
						}
					}
				}
			}

			if (!visited[0][1]) {
				bw.write("UNREACHABLE\n");
			} else {
				boolean flag1 = false;
				boolean flag2 = false;
				for (int i = 0; i < N; i++) {
					if (graph1[i][i] != MAX && graph1[i][i] < 0 && visited[i][1] && visited[0][i]) {
						flag1 = true;
					}
					if (graph2[i][i] != MIN && graph2[i][i] > 0 && visited[i][1] && visited[0][i]) {
						flag2 = true;
					}
				}
				bw.write((flag1 ? "INFINITY " : graph1[0][1] + " ") + (flag2 ? "INFINITY" : graph2[0][1]) + "\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();

	}

}
