import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 50 x 500 x 500 하면 반드시 풀리게 된다.
 * @author leedongjun
 *
 */
public class MainLIS_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 입력의 첫 줄에는 테스트 케이스의 수 C (<= 50) 가 주어진다.
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[N];
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
			}
			// 각 테스트 케이스의 첫 줄에는 수열에 포함된 원소의 수 N (<= 500) 이 주어진다. 그 다음 줄에 수열이 N개의 정수가 주어진다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < i; j++) {
					if (A[j] < A[i]) {
						dp[i] = Math.max(dp[j] + 1, dp[i]);
					}
				}
			}
//			System.out.println(Arrays.toString(dp));

			int answer = 0;
			for (int i = 0; i < N; i++) {
				answer = Math.max(answer, dp[i]);
			}
			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}