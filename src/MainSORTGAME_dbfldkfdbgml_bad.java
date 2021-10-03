import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainSORTGAME_dbfldkfdbgml_bad {

	/**
	 * 어차피 8! 이므로 그래프의 사이즈가 크지 않아 BFS를 돌려서 끝낸다.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> list = new ArrayList<>();
			String[] A = new String[N];
			
			
			for (int i = 0; i < N; i++) {
				A[i] = st.nextToken();
				list.add(Integer.parseInt(A[i]));
			}
			HashMap<Integer, Integer> tempMap = new HashMap<>();
			Collections.sort(list);
			for (int i = 1; i <= N; i++) {
				int n = list.get(i - 1);
				tempMap.put(n, i);
			}
			String start = "";
			for (int i = 0; i < N; i++) {
				int n = tempMap.get(Integer.parseInt(A[i]));
				start += n;
			}

			HashMap<Integer, Integer> map = new HashMap<>();
			map.put(Integer.parseInt(start), 0);
			Queue<Integer> queue = new LinkedList<>();
			queue.add(Integer.parseInt(start));

			while (!queue.isEmpty()) {

				String now = "" + queue.poll();
				int d = map.get(Integer.parseInt(now));

				if (map.containsKey(getAnswerKey(Integer.parseInt(now)))) {
					break;
				}

				for (int i = 0; i < now.length(); i++) {
					for (int j = i + 1; j < now.length(); j++) {
						int next = getNext(now, i, j);
						if (map.containsKey(next)) {
							continue;
						}
						map.put(next, d + 1);
						queue.add(next);
					}
				}
			}

			int key = getAnswerKey(N);
			int answer = map.get(key);
			bw.write(answer + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int getNext(String now, int from, int to) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < from; i++) {
			sb.append(now.charAt(i));
		}
		for (int i = to; i >= from; i--) {
			sb.append(now.charAt(i));
		}
		for (int i = to + 1; i < now.length(); i++) {
			sb.append(now.charAt(i));
		}
		return Integer.parseInt(sb.toString());
	}

	private static int getAnswerKey(int N) {
		if (N == 1) {
			return 1;
		} else if (N == 2) {
			return 12;
		} else if (N == 3) {
			return 123;
		} else if (N == 4) {
			return 1234;
		} else if (N == 5) {
			return 12345;
		} else if (N == 6) {
			return 123456;
		} else if (N == 7) {
			return 1234567;
		} else {
			return 12345678;
		}
	}

}
