import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainPROMISES_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int V, M, N;
			V = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			ArrayList<int[]>[] graph = new ArrayList[V];
			for (int i = 0; i < V; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph[from].add(new int[] { to, cost });
				graph[to].add(new int[] { from, cost });
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				int[] dist = new int[V];
				for (int j = 0; j < V; j++) {
					dist[j] = Integer.MAX_VALUE;
				}
				dist[from] = 0;
				PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1], b[1]));
				queue.add(new int[] { from, dist[from] });
				while (!queue.isEmpty()) {
					int[] now = queue.poll();
					if (dist[now[0]] < now[1]) {
						continue;
					}
					for (int j = 0; j < graph[now[0]].size(); j++) {
						int next = graph[now[0]].get(j)[0];
						int cost = graph[now[0]].get(j)[1];
						if (dist[next] > dist[now[0]] + cost) {
							dist[next] = dist[now[0]] + cost;
							queue.add(new int[] { next, dist[next] });
						}
					}
				}

				if (dist[to] > diff) {
					graph[from].add(new int[] { to, diff });
					graph[to].add(new int[] { from, diff });
				}else {
					answer++;
				}
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}