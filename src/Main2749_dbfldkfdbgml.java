import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		int[][] A = new int[][] { { 0, 1 }, { 1, 1 } };
		int[][] B = new int[][] { { 0, 1 }, { 1, 1 } };
		while (n > 0) {
			if (n % 2 == 1) {
				A = multiple(A, B);
				n--;
			} else {
				n /= 2;
				A = multiple(A, A);
			}
		}

		bw.write(A[0][0] + "");

		bw.flush();
		bw.close();
		br.close();
	}

	private static int[][] multiple(int[][] A, int[][] B) {
		int[][] ret = new int[2][2];
		ret[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
		ret[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % MOD;
		ret[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
		ret[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % MOD;
		return ret;
	}

}
