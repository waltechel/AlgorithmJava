import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainMORDOR_dbfldkfdbgml {

	private static int[] maxTree, minTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N, Q;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			int size = 1;
			while (size < N) {
				size *= 2;
			}
			int start = size;
			size *= 2;
			minTree = new int[size];
			maxTree = new int[size];
			for (int i = 0; i < size; i++) {
				minTree[i] = 20001;
				maxTree[i] = -1;
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				update(start + i, num);
			}

			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int maxElevation = queryMax(start + a, start + b);
				int minElevation = queryMin(start + a, start + b);
				bw.write(maxElevation - minElevation + "\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int queryMin(int from, int to) {
		int ret = 20001;
		while (from <= to) {
			if (from % 2 == 1) {
				ret = Math.min(ret, minTree[from++]);
			}
			if (to % 2 == 0) {
				ret = Math.min(ret, minTree[to--]);
			}
			from /= 2;
			to /= 2;
		}
		return ret;
	}

	private static int queryMax(int from, int to) {
		int ret = -1;
		while (from <= to) {
			if (from % 2 == 1) {
				ret = Math.max(ret, maxTree[from++]);
			}
			if (to % 2 == 0) {
				ret = Math.max(ret, maxTree[to--]);
			}
			from /= 2;
			to /= 2;
		}
		return ret;
	}

	private static void update(int i, int num) {
		minTree[i] = num;
		maxTree[i] = num;
		i /= 2;
		while (i > 0) {
			minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
			maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
			i /= 2;
		}
	}

}