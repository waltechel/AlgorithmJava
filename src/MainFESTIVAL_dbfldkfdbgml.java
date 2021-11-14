import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 그냥 막 하면 되는 거 같은데?
 * @author leedongjun
 *
 */
public class MainFESTIVAL_dbfldkfdbgml {

	private static double[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 입력의 첫 줄에는 테스트 케이스의 수 C (C ≤ 100)가 주어집니다.
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {

			st = new StringTokenizer(br.readLine());
			int N, L;
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			double[] A = new double[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			// 각 테스트 케이스의 첫 줄에는 공연장을 대여할 수 있는 날들의 수 N (1 ≤ N ≤ 1000)과 
			// 이미 섭외한 공연 팀의 수 L (1 ≤ L ≤ 1000, L ≤ N)이 주어집니다.
			double answer = Integer.MAX_VALUE;
			for (int i = 0; i < N - L + 1; i++) {
				int candi = 0;
				for (int j = i; j < N; j++) {
					candi += A[j];
					if (j - i + 1 >= L) {
						answer = Math.min(answer, (double) candi / (j - i + 1));
					}
				}
			}

			bw.write(String.format("%.10f", answer) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}
