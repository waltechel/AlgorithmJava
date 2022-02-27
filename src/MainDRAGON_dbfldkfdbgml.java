import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 재귀로 풀면 풀린다
 * 
 * @author leedongjun
 *
 */

public class MainDRAGON_dbfldkfdbgml {

	private static final int NUM = 11;
	private static final int MAX = 51;
	// 초깃값은 그냥 한다
	private static String[] lines = new String[NUM];
	private static long[] A = new long[MAX];
	// 잘 생각해보면 마이너스 위치 인덱스와 플러스 위치 인덱스는 사실 고정값이다.
	private static HashSet<Long> minusIndices = new HashSet<>();
	private static HashSet<Long> plusIndices = new HashSet<>();

	public static final void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		lines[0] = "FX";
		for (int i = 0; i < NUM - 1; i++) {
			lines[i + 1] = parse(lines[i]);
		}
		A[0] = 2;
		for (int i = 0; i < MAX - 1; i++) {
			A[i + 1] = A[i] * 2 + 1;
		}
		for (long n = 8, i = 2; i < MAX; i++) {
			minusIndices.add(n);
			n *= 2;
		}
		for (long n = 2, i = 1; i < MAX; i++) {
			plusIndices.add(n);
			n = n * 2 + 1;
		}

		int C = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < C; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N, P, L;
			N = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken()) - 1;
			L = Integer.parseInt(st.nextToken());
			// 초깃값은 그냥 해도 된다
			if (N < NUM) {
				bw.write(lines[N].substring(P, P + L) + "\n");
				continue;
			} else {
				for (int i = P; i < P + L; i++) {
					bw.write(dfs(N, i));
				}
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static String dfs(int generation, long index) {
		// base case
		if (generation == NUM - 1) {
			return "" + lines[generation].charAt((int) index);
		}
		// 한 세대 이전으로 넘어갈 수 있으면 넘어간다
		if (index < A[generation - 1]) {
			return dfs(generation - 1, index);
		}
		// 마이너스 면 마이너스로 출력한다
		if (minusIndices.contains((long) index)) {
			return "-";
		}
		// 플러스면 플러스로 출력한다 
		if (plusIndices.contains((long) index)) {
			return "+";
		}
		// 그게 아니면 이전 세대의 값과 중복되므로 출력한다.
		return dfs(generation - 1, index - A[generation - 1] - 1);
	}

	private static String parse(String line) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == 'X') {
				sb.append("X+YF");
			} else if (line.charAt(i) == 'Y') {
				sb.append("FX-Y");
			} else {
				sb.append(line.charAt(i));
			}
		}
		return sb.toString();
	}

}