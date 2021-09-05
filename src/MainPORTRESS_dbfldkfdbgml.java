import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainPORTRESS_dbfldkfdbgml {

	/**
	 * 1. 요새 간의 관계가 트리의 관계인 것은 종만북을 보고 이해함
	 * 2. 종만북에서는 트리 정점의 최대 거리를 논리로 따져 내지만,
	 * 그냥 lca를 활용해서 모든 정점에서 모든 정점까지의 거리 중 최댓값을 구하기로 한다(N <= 100)
	 * 3. lca를 이용한 트리 위에서의 정점의 거리
	 * : dist(A, B) = dist(A, LCA) + dist(B, LCA)
	 * 4. 오답이 나오는 이유를 모르겠어요. 
	 */

	private static ArrayList<int[]> list;
	private static ArrayList<Integer>[] graph;
	private static int[] depth;
	private static int[][] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine().trim());
			list = new ArrayList<>();
			// 근원 원(모판)을 하나 더 깐다
			list.add(new int[] { 0, 0, 0, 50000 });
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				list.add(new int[] { i, x, y, r });
			}

			graph = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 1; i <= N; i++) {
				// 어차피 1번이 루트가 된다.
				int parentIndex = 0;
				for (int j = 1; j <= N; j++) {
					if(i == j) {
						continue;
					}
					if (isChildOfBad(list.get(i), list.get(j)) && isChildOfBad(list.get(j), list.get(parentIndex))) {
						parentIndex = j;
					}
				}
				if (i != parentIndex) {
					graph[i].add(parentIndex);
					graph[parentIndex].add(i);
				}
			}

			depth = new int[N + 1];
			parent = new int[N + 1][7];
			dfs(1, 0, 1);

			for (int k = 1; k < 7; k++) {
				for (int i = 1; i <= N; i++) {
					parent[i][k] = parent[parent[i][k - 1]][k - 1];
				}
			}

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i == j) {
						continue;
					}
					int lca = lca(i, j);
					answer = Math.max(answer, (depth[i] - depth[lca]) + (depth[j] - depth[lca]));
				}
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int lca(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		for (int i = 6; i >= 0; i--) {
			if (depth[parent[a][i]] >= depth[b]) {
				a = parent[a][i];
			}
		}
		if (a == b) {
			return a;
		}
		for (int i = 6; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		return parent[a][0];
	}

	private static void dfs(int now, int prev, int d) {
		depth[now] = d;
		parent[now][0] = prev;
		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now].get(i);
			if (prev == next) {
				continue;
			}
			dfs(next, now, d + 1);
		}
	}

	private static boolean isChildOfBad(int[] p1, int[] p2) {
		if ((p1[1] + p1[3] < p2[1] + p2[3]) && (p1[2] + p1[3] < p2[2] + p2[3]) && (p1[1] - p1[3] > p2[1] - p2[3]) && (p1[2] - p1[3] > p2[2] - p2[3])) {
			return true;
		}
		return false;
	}

}