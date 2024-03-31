import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainTPATH_dbfldkfdbgml {

	private static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int N, M;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int answer = Integer.MAX_VALUE;
			ArrayList<int[]> graph = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph.add(new int[] { from, to, cost });
			}

			Collections.sort(graph, (int[] a, int[] b) -> Integer.compare(from[2], to[2]));

			// 지금 이 작은 것부터 했을 때 얼마나 큰 거까지 넣어야 전부 연결이 되나.
			FOR: for (int i = 0; i < M; i++) {
				parent = new int[N];
				for (int j = 0; j < N; j++) {
					parent[j] = j;
				}
				int min = graph.get(i)[2];
				int max = graph.get(i)[2];
				for (int j = i; j < graph.size(); j++) {
					int[] edge = graph.get(j);
					int from = edge[0];
					int to = edge[1];
					int cost = edge[2];
					if (find(from) != find(to)) {
						union(from, to);
						max = cost;
					}
					if (find(0) == find(N - 1)) {
						int candi = max - min;
						answer = Math.min(answer, candi);
						break;
					}
					// 끝까지 봤는데도 이제는 답을 찾을 수 없는 경우
					if (j == graph.size() - 1 && find(0) != find(N - 1)) {
						break FOR;
					}
				}

			}

			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA < rootB) {
			parent[rootB] = rootA;
		} else {
			parent[rootA] = rootB;
		}
	}

	private static int find(int now) {
		if (parent[now] == now) {
			return now;
		} else {
			return parent[now] = find(parent[now]);
		}
	}

}