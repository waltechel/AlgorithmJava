import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainLAN_dbfldkfdbgml_kruskal {

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

			ArrayList<int[]> A = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A.add(new int[] { Integer.parseInt(st.nextToken()), 0 });
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A.get(i)[1] = Integer.parseInt(st.nextToken());
			}

			parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(find(a) != find(b)) {
					union(a, b);					
				}
			}

			ArrayList<Edge> edgeList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (find(i) != find(j)) {
						edgeList.add(new Edge(i, j, dist(A.get(i), A.get(j))));						
					}
				}
			}
			Collections.sort(edgeList, (Edge a, Edge b) -> Double.compare(a.cost, b.cost));

			double answer = 0.0d;
			for (Edge e : edgeList) {
				int a = e.a;
				int b = e.b;
				double cost = Math.sqrt(e.cost);
				if (find(a) != find(b)) {
					union(a, b);
					answer += cost;
				}
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static double dist(int[] a, int[] b) {
		return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
	}

	private static class Edge {
		int a;
		int b;
		double cost;

		public Edge(int a, int b, double cost) {
			super();
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

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