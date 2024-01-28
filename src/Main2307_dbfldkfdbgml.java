import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 로직 자체는 되게 쉬운데 이게 왜 되는지 증명하는 게 어려운 거 같음 
 * 처음에 이 로직을 생각했지만 이게 답이 될 거라고 생각하지는 못함
 * 
 * @author developer
 *
 */
public class Main2307_dbfldkfdbgml {

	private static int N, M;
	private static List<int[]>[] graph;
	private static int[] dist, parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		dist = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new int[] { to, cost });
			graph[to].add(new int[] { from, cost });
		}

		parent = new int[N + 1];

		for(int i = 0 ; i <= N ; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		queue.add(new int[] { 1, 0 });
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[1] > dist[now[0]]) {
				continue;				
			}
			for (int[] next : graph[now[0]]) {
				if (dist[next[0]] > dist[now[0]] + next[1]) {
					dist[next[0]] = dist[now[0]] + next[1];
					queue.add(new int[] { next[0], dist[next[0]] });
					parent[next[0]] = now[0];
				}
			}
		}
		int minimum = dist[N];
		
		int maxPath = Integer.MIN_VALUE;
		for (int to = N; to > 0; to = parent[to]) {
			int from = parent[to];
			maxPath = Math.max(maxPath, candidate(from, to));
		}

		if (maxPath == Integer.MAX_VALUE) {
			bw.write("-1");
		} else {
			bw.write(maxPath - minimum + "");
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static int candidate(int from, int to) {
		for(int i = 0 ; i <= N ; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		queue.add(new int[] { 1, 0 });
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[1] > dist[now[0]]) {
				continue;				
			}

			for (int[] next : graph[now[0]]) {
				if (from == now[0] && to == next[0]) {
					continue;					
				}
				if (dist[next[0]] > dist[now[0]] + next[1]) {
					dist[next[0]] = dist[now[0]] + next[1];
					queue.add(new int[] { next[0], dist[next[0]] });
				}
			}
		}
		return dist[N];
	}

}