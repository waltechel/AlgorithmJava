import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainQUANTIZATION_dbfldkfdbgml {

	private static int[] A;
	private static int N, S;
	private static long [][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			// 정렬을 반드시 해줘야 한다.
			Arrays.sort(A);
			
			// 탑다운 dp
			dp = new long[N][S + 1];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j <= S ; j++) {
					dp[i][j] = -1;
				}
			}
			
			long answer = 0;
			// 코너 케이스 : N <= S라면 정답은 0이 나온다.
			if(N <= S) {
				answer = 0;
			}else {
				answer = dfs(0, S);				
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * index (inclusive) 번째 이후의 수열을 모두 cnt로 양자화했을 때의 결과
	 * 
	 * @param index
	 * @param cnt
	 * @return
	 */
	private static long dfs(int index, int cnt) {
		if (cnt == 1) {
			return dp[index][cnt] = calculateSSE(index, N);
		}
		if(dp[index][cnt] != -1) {
			return dp[index][cnt];
		}
		long ret = Integer.MAX_VALUE;
		for (int i = index + 1; i < N; i++) {
			ret = Math.min(ret, calculateSSE(index, i) + dfs(i, cnt - 1));
		}
		return dp[index][cnt] = ret;
	}

	/**
	 * SSE : Sum of Squared Error
	 * 
	 * @param fromIndex(inclusive)
	 * @param toIndex(exclusive)
	 * @return
	 */
	private static long calculateSSE(int fromIndex, int toIndex) {
		if (fromIndex >= toIndex) {
			return 0;
		}
		long ret = 0;
		int sum = 0;
		for (int i = fromIndex; i < toIndex; i++) {
			sum += A[i];
		}
		// aver에 정수형 오차가 있을 것이므로 
		// +- 1 값까지 세 개의 값을 aver로 설정하고,
		// 개중에 가장 작은 값을 답으로 리턴
		int aver = sum / (toIndex - fromIndex);
		long candiMinusOne = 0;
		long candiZero = 0;
		long candiPlusOne = 0;
		for (int i = fromIndex; i < toIndex; i++) {
			candiMinusOne += (A[i] - (aver - 1)) * (A[i] - (aver - 1));
			candiZero += (A[i] - aver) * (A[i] - aver);
			candiPlusOne += (A[i] - (aver + 1)) * (A[i] - (aver + 1));
		}
		ret = min(candiMinusOne, candiZero, candiPlusOne);
		return ret;
	}

	private static long min(long a, long b, long c) {
		return (long) Math.min(c, Math.min(a, b));
	}

}