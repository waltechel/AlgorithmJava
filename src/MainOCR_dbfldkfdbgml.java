import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainOCR_dbfldkfdbgml {

	private static int m, q, n;
	private static String[] S;
	private static int[] R;
	private static double[] B;
	private static double[][] T;
	private static double[][] M;

	private static int[][] choice;
	private static double[][] cache;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		S = new String[m + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			S[i] = st.nextToken();
		}

		// 각 단어가 문장의 처음에 출현할 확률
		B = new double[m + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			B[i] = Double.parseDouble(st.nextToken());
		}

		// 전이 확률Transitive Probability
		T = new double[m + 1][m + 1];
		for (int i = 0; i <= m; i++) {
			if (i == 0) {
				for (int j = 1; j <= m; j++) {
					T[i][j] = Math.log(B[j]);
				}
			} else {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= m; j++) {
					T[i][j] = Math.log(Double.parseDouble(st.nextToken()));
				}
			}
		}

		M = new double[m + 1][m + 1];
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				M[i][j] = Math.log(Double.parseDouble(st.nextToken()));
			}
		}

		for (int test = 0; test < q; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			cache = new double[102][502];
			for (int i = 0; i <= 101; i++) {
				for (int j = 0; j <= 501; j++) {
					cache[i][j] = 1.0;
				}
			}

			R = new int[101];
			for (int i = 0; i < n; i++) {
				String query = st.nextToken();
				for (int j = 1; j <= m; j++) {
					if (query.equals(S[j])) {
						R[i] = j;
						break;
					}
				}
			}

			choice = new int[102][502];
			recognize(0, 0);
			reconstruct(0, 0);
			System.out.println();
		}

		bw.flush();
		bw.close();
		br.close();

	}

	/**
	 * previousMatch에 segment를 채워서 얻을 수 있는 확률의 최댓값
	 * 
	 * @param segment
	 * @param previousMatch
	 * @return
	 */
	private static double recognize(int segment, int previousMatch) {
		if (segment == n) {
			return 0.0;
		}

		double ret = cache[segment][previousMatch];
		if (ret != 1.0) {
			return ret;
		}
		ret = Math.log(0);

		for (int thisMatch = 1; thisMatch <= m; thisMatch++) {
			// 로그 값을 구하면 오차를 줄일 수 있습니다.
			double candi = T[previousMatch][thisMatch] + M[thisMatch][R[segment]] + recognize(segment + 1, thisMatch);
			if (ret < candi) {
				ret = candi;
				choice[segment][previousMatch] = thisMatch;
			}
		}

		return cache[segment][previousMatch] = ret;
	}

	private static void reconstruct(int segment, int previousMatch) {
		if (segment == n) {
			return;
		}
		int choose = choice[segment][previousMatch];
		System.out.print(S[choose] + " ");
		reconstruct(segment + 1, choose);
	}

}
