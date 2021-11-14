import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 4의 10제곱이므로 1,000,000 정도이므로 그냥 진행하면 되겠다
 * 
 * @author leedongjun
 *
 */
public class MainCLOCKSYNC_dbfldkfdbgml {

	private static final int CLOCKS_NUM = 16;
	private static final int FINAL_PHASE = 9;
	private static int[] clocks;
	private static int answer;
	private static ArrayList<int[]> buttons;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		buttons = new ArrayList<>();
		buttons.add(new int[] { 0, 1, 2 });
		buttons.add(new int[] { 3, 7, 9, 11 });
		buttons.add(new int[] { 4, 10, 14, 15 });
		buttons.add(new int[] { 0, 4, 5, 6, 7 });
		buttons.add(new int[] { 6, 7, 8, 10, 12 });
		buttons.add(new int[] { 0, 2, 14, 15 });
		buttons.add(new int[] { 3, 14, 15 });
		buttons.add(new int[] { 4, 5, 7, 14, 15 });
		buttons.add(new int[] { 1, 2, 3, 4, 5 });
		buttons.add(new int[] { 3, 4, 5, 9, 13 });

		// 첫 줄에 테스트 케이스의 개수 C (<= 30) 가 주어진다.
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {

			clocks = new int[CLOCKS_NUM];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < CLOCKS_NUM; i++) {
				clocks[i] = Integer.parseInt(st.nextToken()) / 3;
				clocks[i] %= 4;
			}
			// System.out.println(Arrays.toString(clocks));

			answer = Integer.MAX_VALUE;
			for (int i = 0; i < 4; i++) {
				// 0번 버튼을 i번 클릭하였고, 클릭한 버튼의 수는 sum 번이다.
				dfs(0, i, i);
				back(0, i);
			}
			bw.write((answer == Integer.MAX_VALUE ? -1 : answer) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 
	 * @param index
	 *                  index번째 버튼을
	 * @param re
	 *                  re번 눌렀고
	 * @param sum
	 *                  토탈
	 */
	private static void dfs(int index, int re, int sum) {
		if (answer != Integer.MAX_VALUE && answer < sum) {
			return;
		}
		click(index, re);
		if (index == FINAL_PHASE) {
			if (isSafe()) {
				answer = Math.min(answer, sum);
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			dfs(index + 1, i, sum + i);
			back(index + 1, i);
		}

	}

	private static void back(int index, int re) {
		int[] button = buttons.get(index);
		for (Integer b : button) {
			clocks[b] += (3 * re);
			clocks[b] %= 4;
		}
	}

	private static void click(int index, int re) {
		int[] button = buttons.get(index);
		for (Integer b : button) {
			clocks[b] += (1 * re);
			clocks[b] %= 4;
		}

	}

	private static boolean isSafe() {
		for (int i = 0; i < CLOCKS_NUM; i++) {
			if (clocks[i] != 0) {
				return false;
			}
		}
		return true;
	}

}
