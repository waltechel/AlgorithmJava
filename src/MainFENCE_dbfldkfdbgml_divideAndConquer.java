import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 분할 정복을 이용하여 문제를 풀어 보았다
 * 
 * @author leedongjun
 *
 */
public class MainFENCE_dbfldkfdbgml_divideAndConquer {

	private static int N;
	private static int[] A;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 첫 줄에 테스트 케이스의 개수 C (C≤50)가 주어집니다.
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {
			N = Integer.parseInt(br.readLine());
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int maximum = dfs(0, N - 1);
			bw.write(maximum + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * fromIndex ~ toIndex까지의 최대 넓이
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	private static int dfs(int fromIndex, int toIndex) {

		if (fromIndex > toIndex) {
			return 0;
		}
		if (fromIndex == toIndex) {
			return A[fromIndex]; // A[toIndex];
		}
		if (toIndex - fromIndex == 1) {
			return Math.min(A[fromIndex], A[toIndex]) * 2;
		}

		int middleIndex = (fromIndex + toIndex) / 2;
		int leftMax = dfs(fromIndex, middleIndex - 1);
		int rightMax = dfs(middleIndex + 1, toIndex);
		int ret = Math.max(leftMax, rightMax);

		int leftIndex = middleIndex;
		int rightIndex = middleIndex;
		int height = A[middleIndex];
		ret = Math.max(ret, height * (rightIndex - leftIndex + 1));
		
		while (fromIndex <= leftIndex && rightIndex <= toIndex) {
			if (leftIndex == fromIndex) {
				if (rightIndex + 1 <= toIndex) {
					height = Math.min(A[++rightIndex], height);
					ret = Math.max(ret, height * (rightIndex - leftIndex + 1));
				} else {
					break;
				}
			} else if (rightIndex == toIndex) {
				if (leftIndex - 1 >= fromIndex) {
					height = Math.min(A[--leftIndex], height);
					ret = Math.max(ret, height * (rightIndex - leftIndex + 1));
				}else {
					break;
				}
			} else {
				if (A[leftIndex - 1] < A[rightIndex + 1]) {
					height = Math.min(A[++rightIndex], height);
					ret = Math.max(ret, height * (rightIndex - leftIndex + 1));
				} else {
					height = Math.min(A[--leftIndex], height);
					ret = Math.max(ret, height * (rightIndex - leftIndex + 1));
				}
			}
		}

		return ret;
	}

}
