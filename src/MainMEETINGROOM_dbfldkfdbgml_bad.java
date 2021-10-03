import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainMEETINGROOM_dbfldkfdbgml_bad {

	private static ArrayList<Integer>[] graph;
	private static int[] visited;
	private static boolean contradiction;

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			ArrayList<int[]> list = new ArrayList<>();
			for (int i = 0; i < N * 2; i += 2) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list.add(new int[] { i, a, b });
				list.add(new int[] { i + 1, c, d });
			}

			graph = new ArrayList[2 * N];
			for (int i = 0; i < 2 * N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < list.size(); i += 2) {
				graph[i].add(i + 1);
				graph[i + 1].add(i);
				for (int j = i + 2; j < list.size(); j++) {
					if (isRelated(list.get(i), list.get(j))) {
						graph[i].add(j);
						graph[j].add(i);
					}
					if (isRelated(list.get(i + 1), list.get(j))) {
						graph[i + 1].add(j);
						graph[j].add(i + 1);
					}
				}
			}

			visited = new int[2 * N];
			contradiction = false;
			for (int i = 0; i < 2 * N; i++) {
				// 1은 한다. 2는 안한다.
				// 1을 누르면 바로 옆의 것은 반드시 하면 안된다(1 -> 2)
				// 2를 누르면 바로 옆의 것은 반드시 해야 한다.(2 -> 1)
				if (visited[i] == 0) {
					dfs(-1, i, 2);
				}
			}

			if (contradiction) {
				bw.write("IMPOSSIBLE\n");
			} else {
				bw.write("POSSIBLE\n");
				for (int i = 0; i < 2 * N; i++) {
					if (visited[i] == 1) {
						bw.write(list.get(i)[1] + " " + list.get(i)[2] + "\n");
					}
				}
			}
		}
		bw.flush();
		bw.close();
		br.close();

	}

	private static void dfs(int prev, int now, int flag) {
		visited[now] = flag;
		if (contradiction) {
			return;
		}
		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now].get(i);
			// 지금 이 회의도 하고 시간 겹치는 다른 회의도 할 수는 없다
			if (flag == 1 && visited[next] == flag) {
				contradiction = true;
				return;
			}
			// 지금 이 회의를 안하는데, 다른 회의도 안할 수는 없다.
			if (flag == 2 && visited[next] == 2 && now % 2 == 1 && next == now - 1) {
				contradiction = true;
				return;
			}
			if (flag == 2 && visited[next] == 2 && now % 2 == 0 && next == now + 1) {
				contradiction = true;
				return;
			}
			// 지금 이 회의를 반드시 해야 한다면, 시간이 겹치거나 그 팀의 다른 회의는 안해야 한다.
			if (flag == 1 && visited[next] == 0) {
				dfs(now, next, 3 - flag);
			}
			// 지금 이 회의를 안해야 한다면, 그 팀의 다른 회의는 반드시 해야 한다.
			if (flag == 2 && visited[next] == 0 && now % 2 == 1 && next == now - 1) {
				dfs(now, next, 3 - flag);
			}
			if (flag == 2 && visited[next] == 0 && now % 2 == 0 && next == now + 1) {
				dfs(now, next, 3 - flag);
			}

		}
	}

	private static boolean isRelated(int[] item1, int[] item2) {
		int t1 = item1[1];
		int t2 = item1[2];
		int t3 = item2[1];
		int t4 = item2[2];

		return !(t4 <= t1 || t2 <= t3);

	}

}