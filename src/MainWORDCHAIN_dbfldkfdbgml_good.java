import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 간선을 가지고 출력함
 * 간선 그래프
 * 
 * @author leedongjun
 *
 */
public class MainWORDCHAIN_dbfldkfdbgml_good {

	// from 정점으로부터 to 정점까지 i 번째 간선을 이용해서 갈 수 있다.
	private static List<Integer>[][] graph;
	private static String[] wordArrayStrings;
	private static int[] indegree, outdegree;
	private static Stack<Integer> edgeIndexStack;
	private static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			graph = new ArrayList[26][26];
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					graph[i][j] = new ArrayList<>();
				}
			}

			int N = Integer.parseInt(br.readLine());
			wordArrayStrings = new String[N];
			indegree = new int[26];
			outdegree = new int[26];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				wordArrayStrings[i] = br.readLine();
				int from = wordArrayStrings[i].charAt(0) - 'a';
				int to = wordArrayStrings[i].charAt(wordArrayStrings[i].length() - 1) - 'a';
				graph[from][to].add(i);
				indegree[to]++;
				outdegree[from]++;
			}

			if (!isEulerGraph()) {
				bw.write("IMPOSSIBLE\n");
				continue;
			}

			edgeIndexStack = new Stack<>();
			getEulerTrailOrCircuit();
			if (edgeIndexStack.size() == N + 1) {
				bw.write("IMPOSSIBLE\n");
				continue;
			}

			while (!edgeIndexStack.isEmpty()) {
				bw.write(wordArrayStrings[edgeIndexStack.pop()] + " ");
			}
			bw.write("\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void getEulerTrailOrCircuit() {
		for (int i = 0; i < 26; i++) {
			// i번째 정점부터 무조건 가야 돌아올 수 있음
			if (outdegree[i] == indegree[i] + 1) {
				for (int j = 0; j < 26; j++) {
					List<Integer> edgeList = graph[i][j];
					for (Integer edgeIndex : edgeList) {
						getEulerCircuit(edgeIndex);
						return;
					}
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			// 그냥 아무 정점이나 갈 수 있으면 가는 경우
			if (outdegree[i] > 0) {
				for (int j = 0; j < 26; j++) {
					List<Integer> edgeList = graph[i][j];
					for (Integer edgeIndex : edgeList) {
						getEulerCircuit(edgeIndex);
						return;
					}
				}
			}
		}
	}

	/**
	 * i번째 글자로부터 시작하는 오일러 서킷
	 * 
	 * @param i
	 */
	private static void getEulerCircuit(int edgeIndex) {
		String edge = wordArrayStrings[edgeIndex];
		visited[edgeIndex] = true;
		int now = (edge.charAt(edge.length() - 1) - 'a');
		for (int i = 0; i < 26; i++) {
			List<Integer> edgeList = graph[now][i];
			for (Integer nextEdgeIndex : edgeList) {
				if (visited[nextEdgeIndex]) {
					continue;
				}
				getEulerCircuit(nextEdgeIndex);
			}
		}

		edgeIndexStack.add(edgeIndex);
	}

	private static boolean isEulerGraph() {
		int outCnt = 0, inCnt = 0;
		for (int i = 0; i < 26; ++i) {
			int delta = outdegree[i] - indegree[i];
			if (delta < -1 || 1 < delta) {
				return false;
			} else if (delta == 1) {
				outCnt++;
			} else if (delta == -1) {
				inCnt++;
			}
		}
		return (outCnt == 1 && inCnt == 1) || (outCnt == 0 && inCnt == 0);
	}

}