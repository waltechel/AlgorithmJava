import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainFENCE_dbfldkfdbgml {

	private static int[] tree, A;
	private static int start;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {
			int N = Integer.parseInt(br.readLine());
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int size = 1;
			while (size < N) {
				size *= 2;
			}
			start = size;
			size *= 2;
			tree = new int[size];
			for (int i = start; i < size; i++) {
				update(i, Math.min(i - start, N - 1));
			}

			bw.write(querySum(start, start + N - 1) + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * fromIndex 와 toIndex 사이의 최대 직사각형의 넓이를 가져온다.
	 * 
	 * @param fromIndex 시작 인덱스
	 * @param toIndex   끝 인덱스
	 * @return 최대 직사각형의 넓이
	 */
	private static long querySum(int fromIndex, int toIndex) {

		if (toIndex < fromIndex) {
			return 0l;
		}

		if (fromIndex == toIndex) {
			return A[tree[toIndex]];
		}

		if (toIndex - fromIndex == 1) {
			return (long) (tree[toIndex] - tree[fromIndex] + 1) * Math.min(A[tree[fromIndex]], A[tree[toIndex]]);
		}

		int minIndex = queryIndex(fromIndex, toIndex);
		long ret = (long) (tree[toIndex] - tree[fromIndex] + 1) * A[minIndex];

		ret = Math.max(querySum(start + minIndex + 1, toIndex), ret);

		ret = Math.max(querySum(fromIndex, start + minIndex - 1), ret);

		return ret;
	}

	/**
	 * fromIndex와 toIndex 사이의 가장 최솟값에 해당하는 Index를 가져온다. 이걸 적당히 잘 잘라야 한다.
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	private static int queryIndex(int fromIndex, int toIndex) {
		int ret = tree[(fromIndex + toIndex) / 2];
		while (fromIndex <= toIndex) {
			if (fromIndex % 2 == 1) {
				if (A[ret] > A[tree[fromIndex]]) {
					ret = tree[fromIndex++];
				} else {
					fromIndex++;
				}
			}
			if (toIndex % 2 == 0) {
				if (A[ret] > A[tree[toIndex]]) {
					ret = tree[toIndex--];
				} else {
					toIndex--;
				}
			}
			fromIndex /= 2;
			toIndex /= 2;
		}
		return ret;
	}

	private static void update(int i, int n) {
		tree[i] = n;
		i /= 2;
		while (i > 0) {
			if (A[tree[i * 2]] <= A[tree[i * 2 + 1]]) {
				tree[i] = tree[i * 2];
			} else {
				tree[i] = tree[i * 2 + 1];
			}
			i /= 2;
		}

	}

}
