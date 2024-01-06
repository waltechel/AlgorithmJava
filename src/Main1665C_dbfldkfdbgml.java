import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 
 * 이 문제는 틀리게 풀었음
 * 부모를 기준으로 봐야지 뎁스를 기준으로 보면 안됨
 * 
 * @author leedongjun
 */
public class Main1665C_dbfldkfdbgml {

	private static ArrayList<Integer>[] graph;
	private static int[] depth;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			graph = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int now = 2; now <= N; now++) {
				int parent = Integer.parseInt(st.nextToken());
				graph[now].add(parent);
				graph[parent].add(now);
			}

			depth = new int[N + 1];
			dfs(1, 0, 1);

			int sum = 0;

			ArrayList<Integer> depthList = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				if (depth[i] != 0) {
					depthList.add(depth[i]);
					sum += depth[i];
				}
			}
			Collections.sort(depthList, Comparator.reverseOrder());
			depth = new int[depthList.size()];
			for (int i = 0; i < depthList.size(); i++) {
				depth[i] = depthList.get(i);
			}

			boolean[] checked = new boolean[depth.length];

			int answer = 0;
			while (sum != 0) {
				for (int i = 0; i < checked.length; i++) {
					if (checked[i] && depth[i] > 0) {
						depth[i]--;
						sum--;
					}
				}
				boolean noFind = true;
				for (int i = 0; i < checked.length; i++) {
					if (checked[i] == false) {
						checked[i] = true;
						depth[i]--;
						sum--;
						noFind = false;
						break;
					}
				}
				if (noFind) {
					for (int i = 0; i < checked.length; i++) {
						if (checked[i] && depth[i] > 0) {
							depth[i]--;
							sum--;
						}
					}
				}
				answer++;
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int now, int parent, int d) {
		depth[d]++;
		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now].get(i);
			if (next == parent) {
				continue;
			}
			dfs(next, now, d + 1);
		}

	}
}