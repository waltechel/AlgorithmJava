import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 쿼리를 따로 받아서 두 번 보는 것으로 한다.
 * 맨 처음 사용자를 영역 지정한다.
 * 
 * @author leedongjun
 *
 */
public class MainEDITORWARS_dbfldkfdbgml {

	private static int[] parent;
	// 0 (미정), 1, 2(기정), 3, 4(미정-대립)
	private static int[] status;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0, N = 0, M = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			String[] list = new String[M];
			for (int i = 0; i < M; i++) {
				list[i] = br.readLine();
			}

			parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			status = new int[N];

			st = new StringTokenizer(list[0]);
			st.nextToken();
			int A = Integer.parseInt(st.nextToken());
			status[A] = 1;

			boolean isContradiction = false;
			int index = 0;
			for (int i = 1; i <= list.length; i++) {
				String query = list[i - 1];
				st = new StringTokenizer(query);
				String command = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if ("ACK".equals(command)) {
					if (isDifferentStatus(a, b)) {
						isContradiction = true;
						index = i;
						break;
					}
					union(a, b);
					check(a, b);
				} else if ("DIS".equals(command)) {
					if (isSameParent(a, b) || isSameStatus(a, b)) {
						isContradiction = true;
						index = i;
						break;
					}
					split(a, b);
				}
			}
			if (isContradiction) {
				bw.write("CONTRADICTION AT " + index + "\n");
				continue;
			}

			int candiA = 0;
			int candiB = 0;
			for (int i = 0; i < N; i++) {
				int rootI = find(i);
				if (status[rootI] == 1 || status[rootI] == 3) {
					candiA++;
				} else if (status[rootI] == 2 || status[rootI] == 4) {
					candiB++;
				} else if (status[rootI] == 0) {
					candiA++;
					candiB++;
				}
			}
			bw.write("MAX PARTY SIZE IS " + Math.max(candiA, candiB) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void check(int a, int b) {
		a = find(a);
		b = find(b);
		if ((status[a] == 1 || status[a] == 2) && (status[b] == 0 || status[b] == 3 || status[b] == 4)) {
			status[b] = status[a];
		} else if ((status[a] == 0 || status[a] == 3 || status[a] == 4) && (status[b] == 1 || status[b] == 2)) {
			status[a] = status[b];
		}
	}

	private static void split(int a, int b) {
		a = find(a);
		b = find(b);
		if ((status[a] == 1 || status[a] == 2) && (status[b] == 0 || status[b] == 3 || status[b] == 4)) {
			status[b] = 3 - status[a];
		} else if ((status[a] == 0 || status[a] == 3 || status[a] == 4) && (status[b] == 1 || status[b] == 2)) {
			status[a] = 3 - status[b];
		} else if (status[a] == 0 && status[b] == 0) {
			status[a] = 3;
			status[b] = 4;
		} else if (status[a] == 0 && status[b] == 3) {
			status[a] = 4;
		} else if (status[a] == 0 && status[b] == 4) {
			status[a] = 3;
		} else if (status[b] == 0 && status[a] == 3) {
			status[b] = 4;
		} else if (status[b] == 0 && status[a] == 4) {
			status[b] = 3;
		}
	}

	private static boolean isSameStatus(int a, int b) {
		a = find(a);
		b = find(b);
		return (status[a] == 1 || status[a] == 2) && (status[b] == 1 || status[b] == 2) && status[a] == status[b];
	}

	private static boolean isDifferentStatus(int a, int b) {
		a = find(a);
		b = find(b);
		return (status[a] == 1 || status[a] == 2) && (status[b] == 1 || status[b] == 2) && status[a] != status[b];
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA < rootB) {
			parent[rootB] = rootA;
		} else {
			parent[rootA] = rootB;
		}
	}

	private static boolean isSameParent(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		return rootA == rootB;
	}

	private static int find(int a) {
		if (parent[a] == a) {
			return a;
		} else {
			int pa = find(parent[a]);
			if ((status[a] == 1 || status[a] == 2) && (status[pa] == 0 || status[pa] == 3 || status[pa] == 4)) {
				status[pa] = status[a];
			} else if ((status[a] == 0 || status[a] == 3 || status[a] == 4) && (status[pa] == 1 || status[pa] == 2)) {
				status[a] = status[pa];
			}
			return parent[a] = pa;
		}
	}

}