import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainDRUNKEN_dbfldkfdbgml {

	/*
	 * 자기 자신으로 가는 것은 0으로 깔아야 한다.
	 */
	
	private static final int MAX = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int V, E;
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		int[][] between = new int[V + 1][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= V; i++) {
			between[i][0] = i;
			between[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] graph = new int[V + 1][V + 1];
		int[][] dp = new int[V + 1][V + 1];
		for (int i = 0; i <= V; i++) {
			for (int j = 0; j <= V; j++) {
				if (i == j) {
					continue;
				}
				graph[i][j] = MAX;
				dp[i][j] = MAX;
			}
		}
		boolean[][] checked = new boolean[V + 1][V + 1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from][to] = cost;
			graph[to][from] = cost;
			dp[from][to] = graph[from][to];
			dp[to][from] = graph[to][from];
			checked[from][to] = true;
			checked[to][from] = true;
		}

		Arrays.sort(between, 1, between.length, (int[] a, int[] b) -> Integer.compare(from[1], to[1]));

		for (int l = 1; l <= V; l++) {
			int k = between[l][0];
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (checked[i][k] && checked[k][j]) {
						checked[i][j] = true;
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
						dp[i][j] = Math.min(dp[i][j], graph[i][k] + graph[k][j] + between[l][1]);
					}
				}
			}
		}

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			bw.write(dp[from][to] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

}
