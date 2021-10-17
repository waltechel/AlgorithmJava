import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 4진법의 수로 표현함
 * 이번에는 short 배열로 줘도 안돼 ㅠㅠ
 */
public class MainHANOI_dbfldkfdbgml_bad3 {

	private static final int MAX = 13;

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		short[][] answerMap = new short[13][];
		int[] B = new int[] { MAX, MAX, MAX, MAX };
		for (int idx = 0, start = 0; idx <= 12; idx++) {
			answerMap[idx] = new short[1 << (idx * 2)];
			for (int i = 0; i < answerMap[idx].length; i++) {
				answerMap[idx][i] = (1 << MAX);
			}
			answerMap[idx][start] = 0;
			Queue<Integer> queue = new LinkedList<>();
			queue.add(start);
			//
			while (!queue.isEmpty()) {
				int now = queue.poll();
				if (answerMap[idx][now] >= 60) {
					break;
				}
				int temp = now;

				Arrays.fill(B, MAX);
				for (int i = 1; i <= idx; i++) {
					int index = temp % 4;
					B[index] = Math.min(B[index], i);
					temp /= 4;
				}
				// i번째 위치에서 j번째 위치로 돌을 옮긴다.
				for (int i = 0; i < 4; i++) {
					for (int j = 0; B[i] != MAX && j < 4; j++) {
						if (i == j) {
							continue;
						}
						// 돌이 더 클 때
						if (B[i] < B[j]) {
							int next = now;
							int iSize = B[i];
							next -= (i << ((iSize - 1) * 2));
							next += (j << ((iSize - 1) * 2));
							if (answerMap[idx][next] == (1 << MAX)) {
								answerMap[idx][next] = (short) (answerMap[idx][now] + 1);
								queue.add(next);
							}
						}
					}
				}
			}
			//
			start *= 4;
			start += 3;
		}

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int totalNum = Integer.parseInt(br.readLine());
			int[] A = new int[totalNum + 1];
			for (int index = 0; index < 4; index++) {
				st = new StringTokenizer(br.readLine());
				int N = Integer.parseInt(st.nextToken());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					A[n] = index;
				}
			}
			int start = 0;
			for (int i = totalNum; i >= 1; i--) {
				start *= 4;
				start += (A[i]);
			}

			Queue<Integer> queue = new LinkedList<>();
			queue.add(start);
			short[] map = new short[1 << (totalNum * 2)];
			Arrays.fill(map, (short) (1 << MAX));
			map[start] = 0;
			int answer = 0;
			while (!queue.isEmpty()) {
				int now = queue.poll();
				if (answerMap[totalNum][now] != (1 << MAX)) {
					answer = answerMap[totalNum][now] + map[now];
					break;
				}
				int temp = now;
				Arrays.fill(B, MAX);
				for (int i = 1; i <= totalNum; i++) {
					int index = temp % 4;
					B[index] = Math.min(B[index], i);
					temp /= 4;
				}
				// i번째 위치에서 j번째 위치로 돌을 옮긴다.
				for (int i = 0; i < 4; i++) {
					for (int j = 0; B[i] != MAX && j < 4; j++) {
						if (i == j) {
							continue;
						}
						// 돌이 더 클 때
						if (B[i] < B[j]) {
							int next = now;
							next -= (i << ((B[i] - 1) * 2));
							next += (j << ((B[i] - 1) * 2));
							if (map[next] == (1 << MAX)) {
								map[next] = (short) (map[now] + 1);
								queue.add(next);
							}
						}
					}
				}
			}

			bw.write(answer + "\n");

		}

		bw.flush();
		bw.close();
		br.close();

	}

}
