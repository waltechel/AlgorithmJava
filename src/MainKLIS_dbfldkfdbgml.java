import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainKLIS_dbfldkfdbgml {

	private static int N;
	private static long K;
	private static int[] A;
	private static int[] lis;
	private static long[] count;
	private static final long MAX = 10_000_000_000l;

	public static final void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < C; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Long.parseLong(st.nextToken());

			A = new int[N + 1];
			lis = new int[N + 1];
			count = new long[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i <= N; i++) {
				lis[i] = -1;
				count[i] = -1;
			}

			bw.write((lis(0) - 1) + "\n");
			ArrayList<Integer> result = new ArrayList<>();
			reconstruct(0, K - 1, result);
			for (Integer e : result) {
				bw.write(e + " ");
			}

			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void reconstruct(int startIndex, long skip, ArrayList<Integer> result) {
		if (startIndex != 0) {
			result.add(A[startIndex]);
		}
		ArrayList<int[]> followers = new ArrayList<>();
		for (int nextIndex = startIndex + 1; nextIndex <= N; nextIndex++) {
			if (A[startIndex] < A[nextIndex] && lis(startIndex) == lis(nextIndex) + 1) {
				followers.add(new int[] { A[nextIndex], nextIndex });
			}
		}
		Collections.sort(followers, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		for (int i = 0; i < followers.size(); i++) {
			int nextIndex = followers.get(i)[1];
			long cnt = count(nextIndex);
			if (cnt <= skip) {
				skip -= cnt;
			} else {
				reconstruct(nextIndex, skip, result);
				break;
			}
		}

	}

	private static long count(int startIndex) {
		if (lis(startIndex) == 1) {
			return 1;
		}
		if (count[startIndex] != -1) {
			return count[startIndex];
		}
		long ret = 0;
		for (int nextIndex = startIndex + 1; nextIndex <= N; nextIndex++) {
			if (A[startIndex] < A[nextIndex] && lis(startIndex) == lis(nextIndex) + 1) {
				ret = Math.min(MAX, ret + count(nextIndex));
			}
		}
		return count[startIndex] = ret;
	}

	private static int lis(int start) {
		if (lis[start] != -1) {
			return lis[start];
		}
		int ret = 1;
		for (int next = start + 1; next <= N; next++) {
			if (A[start] < A[next]) {
				ret = Math.max(ret, lis(next) + 1);
			}
		}
		return lis[start] = ret;
	}

}
