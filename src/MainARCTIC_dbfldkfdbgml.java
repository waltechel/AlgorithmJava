import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 크루스칼 알고리즘을 활용한 MST가 정답이 될 수 있다.
 * 크루스칼 알고리즘을 활용해 최소 스패닝 트리를 만들고, 그 안에서의 가장 작은 간선의 길이가 정답이 된다.
 * 
 * 플로이드 알고리즘으로는 어떻게 풀 수 있을까?
 * 
 * 이분탐색과 BFS를 활용할 수 있을 거 같은데 나는 안된다 ㅠㅠ
 * 
 * @author leedongjun
 *
 */
public class MainARCTIC_dbfldkfdbgml {

	private static Point[] list;
	private static int N;
	private static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			N = Integer.parseInt(br.readLine());
			list = new Point[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				list[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			}

			// 최소 무전기의 출력을 하기 위해 소숫점 밑 셋째 자리에서 반올림해 출력합니다.
			double[][] graph = new double[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}
					graph[i][j] = distance(list[i], list[j]);
				}
			}

			ArrayList<Edge> edgeList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					edgeList.add(new Edge(i, j, graph[i][j]));
				}
			}

			double answer = Integer.MIN_VALUE;
			Collections.sort(edgeList, (e1, e2) -> Double.compare(e1.dist, e2.dist));

			parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			for (Edge e : edgeList) {
				int a = e.a;
				int b = e.b;
				if (find(a) != find(b)) {
					union(a, b);
					answer = Math.max(answer, e.dist);
				}
			}

			bw.write(String.format("%.2f", ((double) Math.round(answer * 100)) / 100) + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
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

	private static int find(int a) {
		if (a == parent[a]) {
			return a;
		} else {
			return parent[a] = find(parent[a]);
		}
	}

	private static double distance(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}

	private static class Edge {
		int a, b;
		double dist;

		public Edge(int a, int b, double dist) {
			super();
			this.a = a;
			this.b = b;
			this.dist = dist;
		}

	}

	private static class Point {
		double x, y;

		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}