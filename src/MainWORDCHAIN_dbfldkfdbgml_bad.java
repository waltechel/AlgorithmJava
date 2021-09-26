import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainWORDCHAIN_dbfldkfdbgml_bad {

	private static List<String> graph;
	private static boolean[] visited;
	private static boolean isOver;
	private static int cnt;
	private static Stack<String> stack;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			visited = new boolean[N];
			graph = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				graph.add(line);
			}

			stack = new Stack<>();
			cnt = 0;
			isOver = false;
			for (int i = 0; i < N; i++) {
				if (!isOver) {
					dfs(i, graph.get(i).charAt(graph.get(i).length() - 1));
					visited[i] = false;
					cnt--;
					stack.pop();
				}
			}

			if (isOver) {
				bw.write("\n");
			} else {
				bw.write("IMPOSSIBLE\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int now, char c) throws IOException {
		visited[now] = true;
		stack.add(graph.get(now));
		cnt++;
		if (cnt == graph.size()) {
			isOver = true;
			for (String s : stack) {
				bw.write(s + " ");
			}
			return;
		}

		for (int i = 0; i < graph.size(); i++) {
			if (!visited[i] && graph.get(i).charAt(0) == c) {
				dfs(i, graph.get(i).charAt(graph.get(i).length() - 1));
				visited[i] = false;
				cnt--;
				stack.pop();
			}
			if(isOver) {
				return;
			}
		}
	}

}