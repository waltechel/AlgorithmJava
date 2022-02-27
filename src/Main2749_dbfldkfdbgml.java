import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 *
 * 행렬곱을 이용한 피보나치
 * 
 * @author leedongjun
 *
 */
public class Main2749_dbfldkfdbgml {

	private static final int MOD = 1_000_000;

	public static final void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		long n = Long.parseLong(br.readLine());
		long[][] A = new long[][] { { 0, 1 }, { 1, 1 } };
		long[][] B = new long[][] { { 0, 1 }, { 1, 1 } };
		while (n > 0) {
			if (n % 2 == 1) {
				A = multiple(A, B);
				n--;
			} else if ((n / 2) > 0) {
				B = multiple(B, B);
				n /= 2;
			}
		}

		bw.write(A[0][0] + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static long[][] multiple(long[][] A, long[][] B) {
		long[][] ret = new long[2][2];
		ret[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
		ret[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % MOD;
		ret[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
		ret[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % MOD;
		return ret;
	}

}
