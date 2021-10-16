import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainTIMETRIP_dbfldkfdbgml_bad {

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			int N, M;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			ArrayList<int[]> graph = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph.add(new int[] { from, to, cost });
			}

			long[] distMin = new long[N];
			long[] distMax = new long[N];
			for (int i = 0; i < N; i++) {
				distMax[i] = Integer.MIN_VALUE;
				distMin[i] = Integer.MAX_VALUE;
			}
			distMax[0] = 0;
			distMin[0] = 0;

			for (int i = 0; i < N - 1; i++) {
				for (int[] edge : graph) {
					int from = edge[0];
					int to = edge[1];
					int cost = edge[2];
					if (distMin[to] > distMin[from] + cost) {
						distMin[to] = distMin[from] + cost;
					}
					if (distMax[to] < distMax[from] + cost) {
						distMax[to] = distMax[from] + cost;
					}
				}
			}

			// 조사단이 웜홀을 통해 안드로메다 은하에 갈 방법이 없을 경우에는 UNREACHABLE을 출력합니다.
			if (distMin[1] == Integer.MAX_VALUE && distMax[1] == Integer.MIN_VALUE) {
				bw.write("UNREACHABLE\n");
			} else {
				for (int[] edge : graph) {
					int from = edge[0];
					int to = edge[1];
					int cost = edge[2];
					if (distMin[to] > distMin[from] + cost) {
						distMin[to] = distMin[from] + cost;
						if(to == 1) {
							distMin[to] = Integer.MIN_VALUE;
						}
					}
					if (distMax[to] < distMax[from] + cost) {
						distMax[to] = distMax[from] + cost;
						if(to == 1) {
							distMax[to] = Integer.MAX_VALUE; 
						}
					}
				}
				bw.write(distMin[1] == Integer.MIN_VALUE ? "INFINITY" : distMin[1] + " " + (distMax[1] == Integer.MAX_VALUE ? "INFINITY" : distMax[1]) + "\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();

	}

}
