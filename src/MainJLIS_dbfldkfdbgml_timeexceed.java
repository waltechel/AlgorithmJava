import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainJLIS_dbfldkfdbgml_timeexceed {

	private static final int MAX_NUM = 200;
	private static ArrayList<Integer>[] graph;
	private static int answer;
	private static int N, M;
	private static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int[] A = new int[MAX_NUM];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 100; j < 100 + M; j++) {
				A[j] = Integer.parseInt(st.nextToken());
			}

			graph = new ArrayList[MAX_NUM];
			for (int i = 0; i < MAX_NUM; i++) {
				graph[i] = new ArrayList<>();
			}

			int[] indegree = new int[MAX_NUM];
			for (int i = 0; i < MAX_NUM; i++) {
				if (i < N) {
					for (int j = i + 1; j < N; j++) {
						if (A[i] < A[j]) {
							graph[i].add(j);
							indegree[j]++;
						}
					}
					for (int j = 100; j < 100 + M; j++) {
						if (A[i] < A[j]) {
							graph[i].add(j);
							indegree[j]++;
						}
					}
				} else if (i >= 100 && i < 100 + M) {
					for (int j = 0; j < N; j++) {
						if (A[i] < A[j]) {
							graph[i].add(j);
							indegree[j]++;
						}
					}
					for (int j = i + 1; j < 100 + M; j++) {
						if (A[i] < A[j]) {
							graph[i].add(j);
							indegree[j]++;
						}
					}
				}
			}

			answer = 0;
			dp = new int[200][N][M];
			for (int i = 0; i < 200; i++) {
				if (i < N) {
					dp[i][i][0] = dfs(i, i, 0);
				}
				if ((100 <= i && i < 100 + M)) {
					dp[i][0][i - 100] = dfs(i, 0, i - 100);
				}
			}
			for (int i = 0; i < 200; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						answer = Math.max(answer, dp[i][j][k]);
					}
				}
			}
			
			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static int dfs(int now, int ca, int cb) {
		if (dp[now][ca][cb] != 0) {
			return dp[now][ca][cb];
		}
		dp[now][ca][cb] = 1;
		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now].get(i);
			if (next < N && ca <= next) {
				dp[now][ca][cb] = Math.max(dp[now][ca][cb], dfs(next, next, cb) + 1);
			}
			if ((100 <= next && next < 100 + M) && cb <= next - 100) {
				dp[now][ca][cb] = Math.max(dp[now][ca][cb], dfs(next, ca, next - 100) + 1);
			}
		}
		return dp[now][ca][cb];
	}

}