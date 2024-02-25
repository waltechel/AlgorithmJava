import java.util.*;
import java.io.*;

public class Main1800_dbfldkfdbgml {

	private static int N, P, K;
	private static List<int[]>[] graph;
	private static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dist = new int[N + 1];

		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<int[]>();
		}

		int end = Integer.MIN_VALUE;
		int start = 0;
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			end = Math.max(end, cost);
			graph[from].add(new int[] { to, cost });
			graph[to].add(new int[] { from, cost });
		}

		int answer = Integer.MIN_VALUE;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (dijkstra(mid)) {
				answer = mid;
				end = mid - 1;
			} else
				start = mid + 1;
		}
		if (answer == Integer.MIN_VALUE) {
			bw.write("-1");
		} else {
			bw.write(answer + "");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean dijkstra(int middle) {
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;
		queue.add(new int[] { 1, dist[1] });
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (dist[now[0]] < now[1]) {
				continue;
			}
			for (int i = 0; i < graph[now[0]].size(); i++) {
				int[] temp = graph[now[0]].get(i);
				int next = temp[0];
				int cost = temp[1];
				int nextNodeCount = now[1];
				if (cost > middle) {
					nextNodeCount++;
				}
				if (nextNodeCount < dist[next]) {
					dist[next] = nextNodeCount;
					queue.add(new int[] { next, nextNodeCount });
				}
			}
		}
		return dist[N] <= K;
	}
}