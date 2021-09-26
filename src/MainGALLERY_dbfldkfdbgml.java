import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 미술관은 한 번 관람한 갤러리를 다시 가기 위해서는 이전에 지나왔던
 * 복도를 반드시 한 번 지나야 하는 구조로 설계되어 있으며
 * 말단 노드는 택할 필요가 없고 부모 노드는 택할지 말지 결정하면 된다.
 * 트리 위에서 가장 적은 점으로 그래프의 모든 정점을 지배하는 정점들의 집합
 * 트리의 최소 지배 집합
 * 
 * @author leedongjun
 *
 */
public class MainGALLERY_dbfldkfdbgml {

	private static List<Integer>[] graph;
	private static int answer;
	private static boolean[] visited;
	private static final int UNWATCHED = 0, WATCHED = 1, INSTALLED = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
				graph[to].add(from);
			}
			
			visited = new boolean[N];
			answer = 0;
			for (int i = 0; i < N; i++) {
				if (!visited[i] && dfs(i) == 0) {
					answer++;
				}
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int dfs(int now) {
		visited[now] = true;
		boolean[] child = new boolean[3];
		for (int i = 0, next; i < graph[now].size(); i++) {
			next = graph[now].get(i);
			if (!visited[next]) {
				child[dfs(next)] = true;
			}
		}

		if (child[UNWATCHED]) {
			answer++;
			return INSTALLED;
		}
		if (child[INSTALLED]) {
			return WATCHED;
		}
		return UNWATCHED;
	}

}