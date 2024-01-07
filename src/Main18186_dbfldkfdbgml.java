import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * small을 풀면 살짝 바꾸면 되는 도적 문제 1. B가 C보다 좋다면 무조건 B로 산다 2. C가 B보다 좋다면 기존의 로직을 넣는다
 * 
 * @author developer
 *
 */
public class Main18186_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long[] A = new long[N + 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		long answer = 0;
		if (B < C) {
			for (int i = 0; i < N; i++) {
				answer += A[i] * B;
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (A[i + 1] > A[i + 2]) {
					long min5 = Math.min(A[i], A[i + 1] - A[i + 2]);
					A[i] -= min5;
					A[i + 1] -= min5;
					answer += min5 * (B + C);

					long min7 = min(A[i], A[i + 1], A[i + 2]);
					A[i] -= min7;
					A[i + 1] -= min7;
					A[i + 2] -= min7;
					answer += min7 * (B + C * 2);

					answer += A[i] * B;
				} else {
					long min7 = min(A[i], A[i + 1], A[i + 2]);
					A[i] -= min7;
					A[i + 1] -= min7;
					A[i + 2] -= min7;
					answer += min7 * (B + C * 2);

					long min5 = Math.min(A[i], A[i + 1]);
					A[i] -= min5;
					A[i + 1] -= min5;
					answer += min5 * (B + C);

					answer += A[i] * B;
				}

			}
		}

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static long min(long i, long j, long k) {
		return Math.min(i, Math.min(j, k));
	}

}