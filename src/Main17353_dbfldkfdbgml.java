import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 레이지 세그를 쓰되 결국에는 계차수열의 레이지세그다~
 * 
 * @author leedongjun
 *
 */
public class Main17353_dbfldkfdbgml {

	private static int N, Q;
	private static long[] tree, lazy;

	public static void main(String args[]) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int size = 1;
		while (size < N) {
			size *= 2;
		}
		int start = size;
		size *= 2;

		tree = new long[size];
		lazy = new long[size];
		
		long [] A = new long[size];
		st = new StringTokenizer(br.readLine());
		for (int i = start; i < start + N; i++) {
			long n = Long.parseLong(st.nextToken());
			A[i] = n;
		}
		for(int i = start; i < start + N ; i++) {
			update(i, A[i] - A[i - 1]);			
		}

		Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				update_range(1, start, size - 1, start + b - 1, start + c - 1, 1);
				update_range(1, start, size - 1, start + c, start + c, -(c - b + 1));
			} else {
				int b = Integer.parseInt(st.nextToken());
				bw.write(sum(1, start, size - 1, start, start + b - 1) + "\n"); // 이걸 sum으로 표현하다니!
			}
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static void update_lazy(int i, int start, int end) {
		if (lazy[i] != 0) {
			tree[i] += (end - start + 1) * lazy[i];
			if (start != end) {
				lazy[i * 2] += lazy[i];
				lazy[i * 2 + 1] += lazy[i];
			}
			lazy[i] = 0;
		}
	}

	private static long sum(int i, int start, int end, int from, int to) {
		update_lazy(i, start, end);
		if (to < start || end < from) {
			return 0;
		}
		if (from <= start && end <= to) {
			return tree[i];
		}
		return sum(i * 2, start, (start + end) / 2, from, to) + sum(i * 2 + 1, (start + end) / 2 + 1, end, from, to);
	}

	private static void update_range(int i, int start, int end, int from, int to, long n) {
		update_lazy(i, start, end);
		if (to < start || end < from) {
			return;
		}
		if (from <= start && end <= to) {
			tree[i] += (end - start + 1) * n;
			if (start != end) {
				lazy[i * 2] += n;
				lazy[i * 2 + 1] += n;
			}
			return;
		}
		update_range(i * 2, start, (start + end) / 2, from, to, n);
		update_range(i * 2 + 1, (start + end) / 2 + 1, end, from, to, n);
		tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}

	private static void update(int i, long n) {
		tree[i] = n;
		i /= 2;
		while (i > 0) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
			i /= 2;
		}
	}
}
