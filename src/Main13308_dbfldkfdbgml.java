import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 다익스트라 algorithm
 */
public class Main13308_dbfldkfdbgml {

	private static int[] gasStation;
	private static List<int[]>[] graph;
	private static long[][] dist;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N, M;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		gasStation = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			gasStation[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			graph[from].add(new int[] { to, distance });
			graph[to].add(new int[] { from, distance });
		}

		dist = new long[N + 1][2501]; // 몇 번째 점에 얼마만큼의 주유소 비용으로 갈 수 있는지 체크
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= 2500; j++) {
				dist[i][j] = Long.MAX_VALUE / 10;
			}
		}

		dist[1][gasStation[1]] = 0; // 1번 정점에 1번 주유소 비용으로 얼마만큼 갈 수 있다
		PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1[2], o2[2]));
		queue.add(new long[] { 1, gasStation[1], dist[1][gasStation[1]] });

		while (!queue.isEmpty()) {
			long[] temp = queue.poll();
			int now = (int) temp[0];
			int gasPrice = (int) temp[1];
			if (dist[now][gasPrice] < temp[2]) {
				continue;
			}
			for (int[] next : graph[now]) {
				// 이거를 한 번만 넣어야 함
				int minGasPrice = Math.min(gasPrice, gasStation[now]);
				if (dist[next[0]][minGasPrice] > dist[now][gasPrice] + next[1] * minGasPrice) {
					dist[next[0]][minGasPrice] = dist[now][gasPrice] + next[1] * minGasPrice;
					queue.add(new long[] { next[0], minGasPrice, dist[next[0]][minGasPrice] });
				}
			}
		}
		
//		System.out.println("=========검증쿼리=========");
//		System.out.println(dist[3][5]);
//		System.out.println(dist[2][5]);
//		System.out.println(dist[1][2]);
//		System.out.println(dist[3][2]);
//		System.out.println(dist[4][2]);
//		System.out.println("=========검증쿼리=========");

		long answer = Long.MAX_VALUE;
		for (long candi : dist[N]) {
			answer = Math.min(candi, answer);
		}
		bw.write(answer + "");

		bw.flush();
		br.close();
		bw.close();
	}

}