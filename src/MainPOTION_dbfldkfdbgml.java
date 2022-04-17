import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 이거 막 풀어도 풀리는 문제다. 아이 달다 ㅎㅎ
 * @author developer
 *
 */
public class MainPOTION_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int[] B = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}

			// B를 A로 나누었을 때 딱 떨어지도록 B에 추가하는 문제
			// 1. A가 B보다 전부 큰 경우 : B를 A에 채우면 한 병은 나온다.
			// 2. B가 A보다 하나라도 큰 게 있는 경우
			// : A를 최대 공약수로 나누어서 한 병은 더 나오게 해준다.

			int GCD = A[0];
			for (int i = 1; i < N; i++) {
				GCD = gcd(GCD, A[i]);
			}
			int[] C = new int[N];
			for (int i = 0; i < N; i++) {
				C[i] = A[i] / GCD;
			}

			// 1. A가 B보다 전부 큰 경우
			if (isFirstAGreaterThanSecondA(A, B)) {
				for (int i = 0; i < N; i++) {
					bw.write(A[i] - B[i] + " ");
				}
				bw.write("\n");
				continue;
			}

			for (int j = 2; j <= 1000; j++) {
				int[] D = multiply(C, j);
				if (isFirstAGreaterThanSecondA(D, B)) {
					for (int i = 0; i < N; i++) {
						bw.write(D[i] - B[i] + " ");
					}
					bw.write("\n");
					break;
				}
			}

		}
		
		bw.flush();
		bw.close();
		br.close();

	}

	private static int[] multiply(int[] C, int j) {
		int[] ret = new int[C.length];
		for (int i = 0; i < C.length; i++) {
			ret[i] = C[i] * j;
		}
		return ret;
	}

	private static boolean isFirstAGreaterThanSecondA(int[] A, int[] B) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] < B[i]) {
				return false;
			}
		}
		return true;
	}

	private static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}

}
