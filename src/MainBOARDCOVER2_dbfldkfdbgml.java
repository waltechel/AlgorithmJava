import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.sound.sampled.Line;

/**
 * 
 * 판 돌려서 기초적으로 구현
 * 최대 판을 채우는 방법은 추가해봄
 * 하지만 다음 사례에서 시간 초과가 남
 * 1
 * 10 10 1 1
 * ..........
 * ..........
 * ..........
 * ..........
 * ..........
 * ..........
 * ..........
 * ..........
 * ..........
 * ..........
 * #
 * 
 * 1. 판이 겹치게 되면 판을 볼 필요도 없다
 * 2. if(Empty / blockSize + placed < best) 
 * 3. max 를 가지고 해결한다.
 * 
 * @author leedongjun
 *
 */
public class MainBOARDCOVER2_dbfldkfdbgml {

	private static int R, C, H, W;
	private static boolean[][] checked, pad1, pad2, pad3, pad4;
	private static int answer;
	private static int maxCountForPruning;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			checked = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					char c = line.charAt(j);
					if (c == '.') {
						checked[i][j] = false;
					} else {
						checked[i][j] = true;
					}
				}
			}

			pad1 = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					char c = line.charAt(j);
					if (c == '.') {
						pad1[i][j] = false;
					} else {
						pad1[i][j] = true;
					}
				}
			}

			pad2 = rotate(pad1);
			pad3 = rotate(pad2);
			pad4 = rotate(pad3);

			answer = 0;
			maxCountForPruning = calculate();
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (canPruning(1)) {
						continue;
					}
					if (isSafe(i, j, pad1)) {
						dfs(i, j, pad1, 1);
						clear(i, j, pad1);
					}
					if (isSafe(i, j, pad2)) {
						dfs(i, j, pad2, 1);
						clear(i, j, pad2);
					}
					if (isSafe(i, j, pad3)) {
						dfs(i, j, pad3, 1);
						clear(i, j, pad3);
					}
					if (isSafe(i, j, pad4)) {
						dfs(i, j, pad4, 1);
						clear(i, j, pad4);
					}
				}
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean canPruning(int count) {
		return maxCountForPruning < count;
	}

	private static int calculate() {
		int a = 0;
		for (int i = 0; i < checked.length; i++) {
			for (int j = 0; j < checked.length; j++) {
				if (checked[i][j] == false) {
					a++;
				}
			}
		}
		int b = 0;
		for (int i = 0; i < pad1.length; i++) {
			for (int j = 0; j < pad1[i].length; j++) {
				if (pad1[i][j]) {
					b++;
				}
			}
		}
		return a / b;
	}

	private static void clear(int r, int c, boolean[][] pad) {
		for (int i = 0; i < pad.length; i++) {
			for (int j = 0; j < pad[i].length; j++) {
				if (pad[i][j]) {
					checked[r + i][c + j] = false;
				}
			}
		}

	}

	private static void dfs(int r, int c, boolean[][] pad, int count) {
		if (count >= answer) {
			answer = count;
		}

		for (int i = 0; i < pad.length; i++) {
			for (int j = 0; j < pad[i].length; j++) {
				if (pad[i][j]) {
					checked[r + i][c + j] = true;
				}
			}
		}

		for (int i = r; i < checked.length; i++) {
			for (int j = 0; j < checked[i].length; j++) {
				if (i == r && j <= c) {
					continue;
				}
				if (canPruning(count + 1)) {
					continue;
				}
				if (isSafe(i, j, pad1)) {
					dfs(i, j, pad1, count + 1);
					clear(i, j, pad1);
				}
				if (isSafe(i, j, pad2)) {
					dfs(i, j, pad2, count + 1);
					clear(i, j, pad2);
				}
				if (isSafe(i, j, pad3)) {
					dfs(i, j, pad3, count + 1);
					clear(i, j, pad3);
				}
				if (isSafe(i, j, pad4)) {
					dfs(i, j, pad4, count + 1);
					clear(i, j, pad4);
				}
			}
		}

	}

	private static boolean isSafe(int r, int c, boolean[][] pad) {
		if (r + pad.length - 1 >= checked.length) {
			return false;
		}
		if (c + pad[0].length - 1 >= checked[0].length) {
			return false;
		}

		for (int i = 0; i < pad.length; i++) {
			for (int j = 0; j < pad[i].length; j++) {
				if (checked[r + i][c + j] && pad[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static void println(boolean[][] pad) {
		for (int i = 0; i < pad.length; i++) {
			System.out.println(Arrays.toString(pad[i]));
		}
		System.out.println();
	}

	private static boolean[][] rotate(boolean[][] pad) {
		boolean[][] ret = new boolean[pad[0].length][pad.length];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[i].length; j++) {
				ret[i][j] = pad[pad.length - 1 - j][i];
			}
		}
		return ret;
	}

}