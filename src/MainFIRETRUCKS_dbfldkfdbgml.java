import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainFIRETRUCKS_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int V, E, N, M;
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ArrayList<int[]>[] graph = new ArrayList[V + 1];
			for (int i = 0; i <= V; i++) {
				graph[i] = new ArrayList<int[]>();
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph[from].add(new int[] { to, cost });
				graph[to].add(new int[] { from, cost });
			}

			PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> (Integer.compare(from[1], to[1])));
			ArrayList<Integer> endPoint = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				endPoint.add(Integer.parseInt(st.nextToken()));
			}
			int[] dist = new int[V + 1];
			for (int i = 0; i <= V; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int p = Integer.parseInt(st.nextToken());
				dist[p] = 0;
				queue.add(new int[] { p, dist[p] });
			}

			while (!queue.isEmpty()) {
				int[] temp = queue.poll();
				int now = temp[0];
				if (temp[1] > dist[now]) {
					continue;
				}
				for (int i = 0; i < graph[now].size(); i++) {
					int next = graph[now].get(i)[0];
					int cost = graph[now].get(i)[1];
					if (dist[next] > dist[now] + cost) {
						dist[next] = dist[now] + cost;
						queue.add(new int[] { next, dist[next] });
					}
				}
			}

			long answer = 0;
			for (Integer e : endPoint) {
				answer += dist[e];
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

}