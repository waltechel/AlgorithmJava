import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 플로이드 알고리즘으로는 어떻게 풀 수 있을까?
 * 플로이드를 돌리고 간선 간의 최댓값이 정답이 된다.
 * 
 * 이분탐색과 BFS를 활용할 수 있을 거 같은데 나는 안된다 ㅠㅠ
 * 
 * @author leedongjun
 *
 */
public class MainARCTIC_dbfldkfdbgml2 {

	private static Point[] list;
	private static int N;

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
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph[i][j] = distance(list[i], list[j]);
					visited[i][j] = true;
					visited[j][i] = true;
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
					}
				}
			}

			double answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}
					answer = Math.min(answer, graph[i][j]);
				}
			}

			bw.write(String.format("%.2f", ((double) Math.round(answer * 100)) / 100) + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static double distance(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
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