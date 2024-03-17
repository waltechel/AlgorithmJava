import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main16404_dbfldkfdbgml {

	/**
	 * 전에 자동차 공장을 한 소스가 있어서 거기랑 유사하게 입력만 옮겨서 풂
	 */

	private static long[] tree, lazy;
	private static int N, M;
	private static List<Integer>[] graph;
	private static int[] orderin, orderout;
	private static int order = 1;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		st = new StringTokenizer(br.readLine());
		for (int now = 1; now <= N; now++) {
			int from = Integer.parseInt(st.nextToken());
			if (from != -1) {
				graph[now].add(from);
				graph[from].add(now);
			}
		}

		orderin = new int[N + 1];
		orderout = new int[N + 1];
		dfs(1);
		// System.out.println(Arrays.toString(orderin));
		// System.out.println(Arrays.toString(orderout));

		int size = 1;
		while (size <= N) {
			size *= 2;
		}
		int start = size;
		size *= 2;
		tree = new long[size];
		lazy = new long[size];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("1")) {
				int a = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				update_range(1, start, size - 1, start + orderin[a] - 1, start + orderout[a] - 1, x);

			} else {
				int a = Integer.parseInt(st.nextToken());
				bw.write(query(1, start, size - 1, start + orderin[a] - 1) + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static long query(int node, int start, int end, int a) {
		update_lazy(node, start, end);
		if (a < start || end < a) {
			return 0l;
		}
		if (start == end) {
			return tree[node];
		}
		return query(node * 2, start, (start + end) / 2, a) + query(node * 2 + 1, (start + end) / 2 + 1, end, a);
	}

	private static void update_range(int node, int start, int end, int from, int to, int x) {
		update_lazy(node, start, end);
		if (end < from || to < start) {
			return;
		}
		if (from <= start && end <= to) {
			tree[node] += x;
			if (start != end) {
				lazy[node * 2] += x;
				lazy[node * 2 + 1] += x;
			}
			return;
		}
		update_range(node * 2, start, (start + end) / 2, from, to, x);
		update_range(node * 2 + 1, (start + end) / 2 + 1, end, from, to, x);
	}

	private static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node];
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	private static void update(int i, long l) {
		tree[i] = l;
		i /= 2;
		while (i > 0) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
			i /= 2;
		}
	}

	private static int dfs(int now) {
		orderin[now] = order++;
		int ret = orderin[now];
		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now].get(i);
			if (orderin[next] == 0) {
				ret = Math.max(ret, dfs(next));
			}
		}
		return orderout[now] = ret;
	}

}