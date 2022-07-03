import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 이 문제는 틀리는데 일부 케이스는 맞는다.
 * @author developer
 *
 */
public class Main1658D1_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int l, r;
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			int[] A = new int[r - l + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < A.length; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			int[][] dp = new int[17][2];
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < A.length; j++) {
					if (A[j] >= (1 << i) && (A[j] & (1 << i)) != 0) {
						dp[i][1]++;
					}
					if (A[j] >= (1 << i) && (A[j] & (1 << i)) == 0) {
						dp[i][0]++;
					}
				}
			}
			int x = 0;
			for (int i = 0; i < 17; i++) {
				if (dp[i][0] + dp[i][1] > 0) {
					if (dp[i][1] > dp[i][0]) {
						x |= (1 << i);
					}
				}
			}
			bw.write(x + "\n");

		}

		bw.flush();
		bw.close();
		br.close();

	}

}
