import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainROUTING_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ArrayList<E>[] graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				double cost = Double.parseDouble(st.nextToken());
				graph[from].add(new E(to, cost));
				graph[to].add(new E(from, cost));
			}
			double[] dist = new double[N + 1];
			for (int i = 0; i < N; i++) {
				dist[i] = Double.MAX_VALUE;
			}
			PriorityQueue<E> queue = new PriorityQueue<>((E a, E b) -> (Double.compare(a.cost, b.cost)));
			dist[0] = 1.0d;
			queue.add(new E(0, dist[0]));
			while (!queue.isEmpty()) {
				E temp = queue.poll();
				int now = temp.p;
				if (dist[now] < temp.cost) {
					continue;
				}
				for (int i = 0; i < graph[now].size(); i++) {
					int next = graph[now].get(i).p;
					double cost = graph[now].get(i).cost;
					if (dist[next] > dist[now] * cost) {
						dist[next] = dist[now] * cost;
						queue.add(new E(next, dist[next]));
					}
				}
			}
			double answer = dist[N - 1];
			System.out.printf("%.10f\n", answer);
			
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static class E {
		int p;
		double cost;

		public E(int p, double cost) {
			super();
			this.p = p;
			this.cost = cost;
		}

	}

}