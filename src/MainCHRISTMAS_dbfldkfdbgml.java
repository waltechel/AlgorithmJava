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
			int [] B = new int[A.length];
			// prev[s] = A가 s였던 마지막 위치
			int [] prev = new int[K];
			for(int i = 0 ; i < K ; i++) {
				prev[i] = -1;
			}
			for(int i = 0 ; i < A.length ; i++) {
				// i번째 상자를 아예 고려하지 않는 경우
				if(i > 0) {
					B[i] = B[i - 1];
				}
				int location = prev[A[i]];
				if(location != -1) {
					B[i] = Math.max(B[i], B[location] + 1);
				}
				prev[A[i]] = i;
			}
			answer2 = B[N];
			bw.write(answer1 + " " + answer2 + "\n");
		}

		br.close();
		bw.close();

	}

}
