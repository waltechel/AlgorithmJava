import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainDICTIONARY_dbfldkfdbgml {
	
	/*
	 * 26에 착안하여 플로이드로 사이클 체크 하였다.
	 * 플로이드로 사이클 되는지 보고 위상 정렬 하였다.
	 * 인접행렬 스타일로 위상정렬함 
	 */

	private static boolean[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			graph = new boolean[26][26];
			List<String> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(br.readLine());
			}

			for (int i = 0; i < N - 1; i++) {
				// 특정 글과 그 다음 글만 보면 된다.
				// 책에서는 이 내용을 논리로 접근했는데, 여기서는 플로이드를 이용해서 풀었으므로 
				// 어차피 특정 글과 그 다다음 글, 그 이후의 글들도 파악이 된다.
				String A = list.get(i);
				String B = list.get(i + 1);
				for (int j = 0; j < Math.min(A.length(), B.length()); j++) {
					if (A.charAt(j) != B.charAt(j)) {
						graph[A.charAt(j) - 'a'][B.charAt(j) - 'a'] = true;
						break;
					}
				}
			}

			for (int k = 0; k < 26; k++) {
				for (int i = 0; i < 26; i++) {
					for (int j = 0; j < 26; j++) {
						graph[i][j] |= graph[i][k] & graph[k][j];
					}
				}
			}

			boolean isCycle = false;
			for (int i = 0; i < 26; i++) {
				for (int j = i; j < 26; j++) {
					if (graph[i][j] && graph[j][i]) {
						isCycle = true;
					}
				}
			}

			if (isCycle) {
				bw.write("INVALID HYPOTHESIS\n");
				continue;
			}

			int[] inDegree = new int[26];
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (graph[i][j]) {
						inDegree[j]++;
					}
				}
			}

			Queue<Integer> queue = new LinkedList<>();
			for (int i = 0; i < 26; i++) {
				if (inDegree[i] == 0) {
					queue.add(i);
				}
			}

			List<Character> answerList = new LinkedList<>();
			while (!queue.isEmpty()) {
				int now = queue.poll();
				answerList.add((char) ('a' + now));
				for (int i = 0; i < 26; i++) {
					if (graph[now][i]) {
						inDegree[i]--;
						if (inDegree[i] == 0) {
							queue.add(i);
						}
					}
				}
			}

			for (Character c : answerList) {
				bw.write(c + "");
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}