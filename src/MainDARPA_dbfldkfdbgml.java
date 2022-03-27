import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainDARPA_dbfldkfdbgml {

	private static int numberOfCameras, numberOfTowers;
	private static int[] A;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			numberOfCameras = Integer.parseInt(st.nextToken());
			numberOfTowers = Integer.parseInt(st.nextToken());
			A = new int[numberOfTowers];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < numberOfTowers; i++) {
				// 소숫점 둘째 자리까지 주어질 수 있습니다.
				A[i] = (int) Double.parseDouble(st.nextToken()) * 100;
			}

			int answer = -1;
			for (int i = 0; i < numberOfTowers; i++) {
				int candi = search(i + 1, numberOfCameras - 1);
				if (candi != -1) {
					answer = Math.max(answer, Math.min(A[i + 1] - A[i], candi));
				}
			}

			bw.write(((double) answer) / 100 + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * fromIndex번째부터 cnt개를 선택했을 때의 정답, 단 fromIndex는 반드시 선택하여야 한다.
	 * 
	 * @param fromIndex
	 * @param cnt
	 * @return
	 */
	private static int search(int fromIndex, int cnt) {
		if (fromIndex == numberOfCameras) {
			return -1;
		}
		// 아무리 해도 선택할 수 없을 때
		if (numberOfTowers - fromIndex < cnt) {
			return -1;
		}
		if (cnt == 2) {
			return A[numberOfTowers - 1] - A[fromIndex];
		}

		int ret = -1;
		for (int next = fromIndex + 1; next < numberOfTowers; next++) {
			int candi = search(next, cnt - 1);
			if (candi != -1) {
				ret = Math.max(ret, Math.min(A[next] - A[fromIndex], candi));
			}
		}

		return ret;
	}

}