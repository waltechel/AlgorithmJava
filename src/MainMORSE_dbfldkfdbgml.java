import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainMORSE_dbfldkfdbgml {

	private static final int MAX = 1_000_000_000;
	private static int[][] C = new int[201][101];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 조합을 다시 만들기
		for (int i = 1; i <= 200; i++) {
			for (int j = 0; j <= 100; j++) {
				if (j == 0) {
					C[i][j] = 1;
					continue;
				}
				if (i == j) {
					C[i][j] = 1;
					continue;
				}
				C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
				if (C[i][j] >= MAX) {
					C[i][j] = MAX;
				}
			}
		}
		int T = Integer.parseInt(br.readLine());

		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int n, m, k;
			// n개의 장점과 m개의 단점으로 구성
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			solution(n, m, k);
			System.out.println();
		}

		bw.flush();
		bw.close();
		br.close();

	}

	/**
	 * (n+1)Cm의 경우의 수 중 k 번째
	 * 
	 * @param n
	 * @param m
	 * @param k
	 */
	private static void solution(int n, int m, int k) {
		// 단점만 있을 때
		if (n > 0 && m == 0) {
			while (n > 0) {
				System.out.print("-");
				n--;
			}
			return;
		}
		// 장점만 있을 때
		if (m > 0 && n == 0) {
			while (m > 0) {
				System.out.print("o");
				m--;
			}
			return;
		}

		// k 개 보다 작다면 장점이 먼저 나오세요
		if (C[m + n - 1][n - 1] < k) {
			System.out.print("o");
			solution(n, m - 1, k - C[m + n - 1][n - 1]);
		} else {
			System.out.print("-");
			solution(n - 1, m, k);
		}
	}

}
