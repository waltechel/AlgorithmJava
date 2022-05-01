import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 401의 경우 200으로 놓고 문제를 푼다.
 * @author leedongjun
 *
 */
public class MainNTHLON_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<int[]> graph = new ArrayList<>();
			int[] dist = new int[402];
			Arrays.fill(dist, Integer.MAX_VALUE);

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int to = a - b;
				int cost = a;
				graph.add(new int[] { to, cost });
			}
//			PriorityQueue<int[]> queue = new PriorityQueue<>((int[] o1, int[] o2) -> Integer.compare(o1[1], o2[1]));
			 Queue<int[]> queue = new LinkedList<>();
			dist[200] = 0;
			queue.add(new int[] { 200, dist[200] });
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				if (dist[now[0]] < now[1]) {
					continue;
				}
				if(now[0] == 401) {
					break;
				}
				for (int i = 0; i < graph.size(); i++) {
					int[] temp = graph.get(i);
					int next = now[0] + temp[0];
					int cost = temp[1];
					if (next >= 0 && next <= 400) {
						if (next == 200) {
							next = 401;
						}
						if (dist[next] > dist[now[0]] + cost) {
							dist[next] = dist[now[0]] + cost;
							queue.add(new int[] { next, dist[next] });
						}
					}
				}
			}
			if (dist[401] != Integer.MAX_VALUE) {
				bw.write(dist[401] + "\n");
			} else {
				bw.write("IMPOSSIBLE\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

}