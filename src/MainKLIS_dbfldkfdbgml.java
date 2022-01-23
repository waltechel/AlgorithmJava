import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MainKLIS_dbfldkfdbgml {

	private static int N;
	private static long K;
	private static int[] A;
	private static int[] lis;
	private static long[] count;

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
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i <= N; i++) {
				lis[i] = -1;
				count[i] = -1;
			}

			bw.write((lis(-1) - 1) + "\n");
			ArrayList<Integer> result = new ArrayList<>();
			reconstruct(-1, K - 1, result);
			for (Integer e : result) {
				bw.write(e + " ");
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void reconstruct(int start, long skip, ArrayList<Integer> result) {
		if (start != -1) {
			result.add(A[start]);
		}
		ArrayList<int[]> followers = new ArrayList<>();
		for (int next = start + 1; next < N; next++) {
			if ((start == -1 || A[start] < A[next]) && lis(start) == lis(next) + 1) {
				followers.add(new int[] { A[next], next });
			}
		}
		Collections.sort(followers, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (Integer.compare(o1[0], o2[0]) == 0) {
					return Integer.compare(o1[1], o2[1]);
				} else {
					return Integer.compare(o1[0], o2[0]);
				}
			}
		});
		for (int i = 0; i < followers.size(); i++) {
			int index = followers.get(i)[1];
			long cnt = count(index);
			if (cnt <= skip) {
				skip -= cnt;
			} else {
				reconstruct(index, skip, result);
				break;
			}
		}

	}

	private static long count(int start) {
		if (lis(start) == 1) {
			return 1;
		}
		if (count[start + 1] != -1) {
			return count[start + 1];
		}
		long ret = 0;
		for (int next = start + 1; next < N; next++) {
			if ((start == -1 || A[start] < A[next]) && lis(start) == lis(next) + 1) {
				ret = Math.min(Long.MAX_VALUE, ret + count(next));
			}
		}
		return count[start + 1] = ret;
	}

	private static int lis(int start) {
		if (lis[start + 1] != -1) {
			return lis[start + 1];
		}
		int ret = 1;
		for (int next = start + 1; next < N; next++) {
			if (start == -1 || A[start] < A[next]) {
				ret = Math.max(ret, lis(next) + 1);
			}
		}
		return lis[start + 1] = ret;
	}

}
