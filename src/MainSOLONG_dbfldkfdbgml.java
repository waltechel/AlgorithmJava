import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 알파벳 트라이를 구성하여 문제를 푼다.
 * 
 * @author leedongjun
 *
 */
public class MainSOLONG_dbfldkfdbgml {

	private static String[] list;
	private static int answer;
	private static Map<String, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0, N = 0, M = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			Node now = new Node();
			Node root = now;
			list = new String[N];
			map = new HashMap<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String line = st.nextToken();
				list[i] = line;
				int value = Integer.parseInt(st.nextToken());
				map.put(line, value);
				for (int j = 0; j < line.length(); j++) {
					if (now.next[line.charAt(j) - 'A'] == null) {
						now.next[line.charAt(j) - 'A'] = new Node();
						now.next[line.charAt(j) - 'A'].tab = i;
					} else {
						if (map.get(list[now.next[line.charAt(j) - 'A'].tab]) < map.get(list[i])) {
							now.next[line.charAt(j) - 'A'].tab = i;
						}
						if (map.get(list[now.next[line.charAt(j) - 'A'].tab]) == map.get(list[i]) && list[i].compareTo(list[now.next[line.charAt(j) - 'A'].tab]) < 0) {
							now.next[line.charAt(j) - 'A'].tab = i;
						}
					}
					now = now.next[line.charAt(j) - 'A'];
				}
				now = root;
			}

			answer = 0;
			String queryString = br.readLine();
			for (int i = 0; i < queryString.length(); i++) {
				if (queryString.charAt(i) == ' ') {
					answer++;
				}
			}
			st = new StringTokenizer(queryString);
			for (int i = 0; i < M; i++) {
				now = root;
				String line = st.nextToken();
				for (int j = 0; j < line.length(); j++) {
					// 이거는 쳐야되는 단어다.
					if (now.next[line.charAt(j) - 'A'] == null) {
						answer += line.length();
						break;
					} else {
						// 어쩌다보니 끝까지 쳐야만 했다.
						if (j == line.length() - 1) {
							answer += line.length();
							break;
						}
						// 탭을 누르면 끝이다.
						if (list[now.next[line.charAt(j) - 'A'].tab].equals(line)) {
							answer += j + 1 + 1;
							break;
						}
					}
					now = now.next[line.charAt(j) - 'A'];
				}
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static class Node {
		Node[] next;
		int tab;

		public Node() {
			this.next = new Node[26];
			this.tab = -1;
		}
	}

}