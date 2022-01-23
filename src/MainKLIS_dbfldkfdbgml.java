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
		for (int next = startIndex + 1; next <= N; next++) {
			if (A[startIndex] < A[next] && lis(startIndex) == lis(next) + 1) {
				followers.add(new int[] { A[next], next });
			}
		}
		Collections.sort(followers, (o1, o2) -> Integer.compare(o1[0], o2[0]));
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
			return count[start] = 1;
		}
		if (count[start] != -1) {
			return count[start];
		}
		long ret = 0;
		for (int next = start + 1; next <= N; next++) {
			if (A[start] < A[next] && lis(start) == lis(next) + 1) {
				ret = Math.min(Long.MAX_VALUE, ret + count(next));
			}
		}
		return count[start] = ret;
	}

	private static int lis(int now) {
		if (lis[now] != -1) {
			return lis[now];
		}
		int ret = 1;
		for (int next = now + 1; next <= N; next++) {
			if (A[now] < A[next]) {
				ret = Math.max(ret, lis(next) + 1);
			}
		}
		return lis[now] = ret;
	}

}
