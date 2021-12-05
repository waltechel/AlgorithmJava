import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainPI_dbfldkfdbgml {

	private static final int MAX = Integer.MAX_VALUE / 10;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {
			String line = br.readLine().trim();
			int[] A = new int[line.length() + 1];
			int N = A.length;
			for (int i = 1; i < N; i++) {
				A[i] = (int) (line.charAt(i - 1) - '0');
			}
			int[] dp = new int[A.length];
			for (int i = 0; i < A.length; i++) {
				dp[i] = MAX;
			}
			dp[0] = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 3; j <= 5; j++) {
					if (matchFrom(i + 1, j, A)) {
						dp[i + j] = min(dp[i + j], dp[i] + 1);
					}
				}
				for (int j = 3; j <= 5; j++) {
					if (netOneIncreaseFrom(i + 1, j, A) || netOneDecreaseFrom(i + 1, j, A)) {
						dp[i + j] = min(dp[i + j], dp[i] + 2);
					}
				}
				for (int j = 3; j <= 5; j++) {
					if (zigzagFrom(i + 1, j, A)) {
						dp[i + j] = min(dp[i + j], dp[i] + 4);
					}
				}
				for (int j = 3; j <= 5; j++) {
					if (isArithmeticSequenceFrom(i + 1, j, A)) {
						dp[i + j] = min(dp[i + j], dp[i] + 5);
					}
				}
				for (int j = 3; j <= 5; j++) {
					if (i + j < dp.length) {
						dp[i + j] = min(dp[i + j], dp[i] + 10);
					}
				}
			}

			bw.write(dp[dp.length - 1] + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean zigzagFrom(int from, int re, int[] A) {
		if (from + re > A.length) {
			return false;
		}
		int cri1 = A[from];
		int cri2 = A[from + 1];
		for (int i = 2; i < re; i++) {
			if (i % 2 == 0) {
				if (A[from + i] != cri1) {
					return false;
				}
			} else {
				if (A[from + i] != cri2) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isArithmeticSequenceFrom(int from, int re, int[] A) {
		if (from + re > A.length) {
			return false;
		}
		int diffCri = A[from + 1] - A[from];
		for (int i = 2; i < re; i++) {
			if (A[from + i] - A[from + i - 1] != diffCri) {
				return false;
			}
		}
		return true;
	}

	private static boolean netOneDecreaseFrom(int from, int re, int[] A) {
		if (from + re > A.length) {
			return false;
		}
		int cri = A[from];
		for (int i = 1; i < re; i++) {
			if (A[from + i] != cri - i) {
				return false;
			}
		}
		return true;
	}

	private static boolean netOneIncreaseFrom(int from, int re, int[] A) {
		if (from + re > A.length) {
			return false;
		}
		int cri = A[from];
		for (int i = 1; i < re; i++) {
			if (A[from + i] != cri + i) {
				return false;
			}
		}
		return true;
	}

	private static boolean matchFrom(int from, int re, int[] A) {
		if (from + re > A.length) {
			return false;
		}
		int cri = A[from];
		for (int i = 1; i < re; i++) {
			if (A[from + i] != cri) {
				return false;
			}
		}
		return true;
	}

	private static int min(int i, int j) {
		return Math.min(i, j);
	}

}