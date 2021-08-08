import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainCHRISTMAS_dbfldkfdbgml {

	/*
	 * 17.2 문제: 크리스마스 인형(문제 ID:CHRISTMAS, 난이도 : 중)
	 */
	private static int N, K;
	private static int[] A;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			// 공식을 약간 바꾸기 위해 0을 하나 더 깔아준다.
			A = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				A[i] = Integer.parseInt(st.nextToken()) % K;
			}
			for (int i = 1; i <= N; i++) {
				A[i] += A[i - 1];
				A[i] %= K;
			}
			int[] cnt = new int[K];
			for (int i = 0; i <= N; i++) {
				cnt[A[i]]++;
			}
			
			long answer1 = 0;
			for (int i = 0; i < K; i++) {
				if(cnt[i] >= 2) {
					answer1 += ((long) cnt[i] * (cnt[i] - 1)) / 2;					
				}
				answer1 %= 20091101;
			}

			int answer2 = 0;
			// D[] 의 부분합 배열 A와 k가 주어질 때 겹치지 않게 몇 번이나 살 수 있는지 확인한다.
			int [] dp = new int[A.length];
			// prev[i] = A값이 i였던 마지막 위치
			int [] lastIndexOfAi = new int[K];
			for(int i = 0 ; i < K ; i++) {
				lastIndexOfAi[i] = -1;
			}
			for(int i = 0 ; i < A.length ; i++) {
				if(i > 0) {
					dp[i] = dp[i - 1];
				}
				int location = lastIndexOfAi[A[i]];
				if(location != -1) {
					// 구간 하나 더하는 것이므로 + 1
					dp[i] = Math.max(dp[i], dp[location] + 1);
				}
				lastIndexOfAi[A[i]] = i;
			}
			answer2 = dp[N];
			bw.write(answer1 + " " + answer2 + "\n");
		}

		br.close();
		bw.close();

	}

}
