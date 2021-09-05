import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainINSERTION_dbfldkfdbgml {

	/**
	 * 세그트리로 품
	 * 카운트를 기록한 세그트리
	 * query : k번째 수의 위치를 알 수 있다
	 */
	
	private static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			int[] answer = new int[N];

			int size = 1;
			while (size < N) {
				size *= 2;
			}
			int start = size;
			size *= 2;
			tree = new int[size];
			for (int i = 0; i < N; i++) {
				update(start + i, 1);
			}

			// 뒤에서부터 본다
			for (int i = N - 1, cnt = 0; i >= 0; i--, cnt++) {
				int n = A[i];
				// N 개 중에 k 번째로 작은 수를 뽑기로 한다.
				int k = N - n - cnt;
				// n 개 중에 k 번째로 작은 수의 인덱스(사실 그게 수)
				int p = query(k) - start + 1; 
				answer[i] = p;
				// 해당 위치의 수는 세었기 때문에 0으로 바꾼다.
				update(start + p - 1, 0);
			}

			for (int i = 0; i < N; i++) {
				bw.write(answer[i] + " ");
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * N 개 중 k 번째 수의 위치(곧 그 수)를 알려주는 쿼리
	 * @param k : k 번째 수의 위치
	 * @return
	 */
	private static int query(int k) {
		if (k <= 0 || tree[1] < k) {
			return -1;
		}
		int i = 1;
		while (i * 2 < tree.length) {
			if (tree[i * 2] < k) {
				k -= tree[i * 2];
				i = i * 2 + 1;
			} else {
				i = i * 2;
			}
		}
		return i;
	}

	private static void update(int i, int n) {
		tree[i] = n;
		i /= 2;
		while (i > 0) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
			i /= 2;
		}
	}

}