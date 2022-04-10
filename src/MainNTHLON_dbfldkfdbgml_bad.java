import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author leedongjun
 *
 */
public class MainNTHLON_dbfldkfdbgml_bad {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {

			ArrayList<int[]> edgeList = new ArrayList<>();
			int M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				edgeList.add(new int[] { a, a - b });
			}

			// Queue<int[]> queue = new LinkedList<>();
			PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			int[] dist = new int[401];
			for (int i = 0; i <= 400; i++) {
				dist[i] = Integer.MAX_VALUE;
			}

			dist[200] = 0;
			queue.add(new int[] { 200, dist[200] });
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				if (now[1] > dist[now[0]]) {
					continue;
				}
				if (now[1] != 0 && now[0] == 200) {
					break;
				}
				for (int[] edge : edgeList) {
					int time = edge[0];
					int diff = edge[1];
					int next = now[0] + diff;
					if (next >= 0 && next <= 400 && ((next == 200 && dist[next] == 0) || dist[next] > dist[now[0]] + time)) {
						dist[next] = dist[now[0]] + time;
						queue.add(new int[] { next, dist[next] });
					}
				}
			}

			bw.write(dist[200] == 0 ? "IMPOSSIBLE" : dist[200] + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

}