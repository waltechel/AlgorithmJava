import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainMATCHFIX_dbfldkfdbgml_bad {

	private static final int END = 200;
	/*
	 * 12번째 항이 항상 스타트 포인트
	 * 13~13+100-1번째 항이 시작점
	 * 0~12번째 항이 두번째 계층
	 * 200번째 항이 항상 엔드 포인트
	 * 
	 * 나동빈의 네트워크 플로우를 기본으로 소스 작성함
	 * https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=ndb796&logNo=221237111220
	 */

	private static final int START = 12;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int[][] capa = new int[201][201];

			ArrayList<Integer>[] graph = new ArrayList[201];
			for (int i = 0; i <= 200; i++) {
				graph[i] = new ArrayList<>();
			}

			int N, M;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[] wins = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				wins[i] = Integer.parseInt(st.nextToken());
			}

			int maximum = 0;
			for (int from = 13; from < M + 13; from++) {
				st = new StringTokenizer(br.readLine());
				int to1 = Integer.parseInt(st.nextToken());
				int to2 = Integer.parseInt(st.nextToken());
				capa[from][to1] = 1;
				capa[from][to2] = 1;
				capa[START][from] = 1;
				graph[from].add(to1);
				graph[from].add(to2);
				graph[START].add(from);
				if (to1 == 0) {
					maximum++;
				}
				if (to2 == 0) {
					maximum++;
				}
			}
			for (int i = 0; i < N; i++) {
				graph[i].add(END);
			}

			int answer = wins[0] - 1;
			for (int ret = 512; ret > 0; ret /= 2) {
				int[][] flow = new int[201][201];
				int[] parent = new int[201];
				for (int i = 0; i < N; i++) {
					flow[i][END] = wins[i];
				}
				for (int i = 0; i < N; i++) {
					if (i == 0) {
						capa[i][END] = answer + ret;
					} else {
						capa[i][END] = answer + ret - 1;
					}
				}

				int totalStream = 0;
				while (true) {
					for (int i = 0; i <= 200; i++) {
						parent[i] = -1;
					}
					Queue<Integer> queue = new LinkedList<>();
					queue.add(START);
					while (!queue.isEmpty()) {
						int now = queue.poll();
						for (int i = 0; i < graph[now].size(); i++) {
							int next = graph[now].get(i);
							if (capa[now][next] - flow[now][next] > 0 && parent[next] == -1) {
								queue.add(next);
								parent[next] = now;
								if (next == END) {
									break;
								}
							}
						}
					}
					if (parent[END] == -1) {
						break;
					}
					int stream = 1000;
					for (int i = END; i != START; i = parent[i]) {
						stream = Math.min(stream, capa[parent[i]][i] - flow[parent[i]][i]);
					}

					for (int i = END; i != START; i = parent[i]) {
						flow[parent[i]][i] += stream;
						flow[i][parent[i]] -= stream;
					}

					totalStream += stream;
				}

				if (totalStream != M) {
					answer += ret;
				}
			}

			bw.write((answer + 1 <= wins[0] + maximum ? answer + 1 : -1) + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}