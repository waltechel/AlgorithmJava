import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main1638D_dbfldfkdbgml_bad {

	private static int N, M;
	private static int[][] map;
	private static final int[][] DIRECTIONS = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				if (check(i, j)) {
					queue.add(new int[] { i, j });
				}
			}
		}

		Stack<int[]> stack = new Stack<>();
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];
			stack.add(new int[] { r, c, map[r][c] });
			clear(r, c);
			for (int d = 0; d < DIRECTIONS.length; d++) {
				int newRow = r + DIRECTIONS[d][0];
				int newCol = c + DIRECTIONS[d][1];
				if (check(newRow, newCol)) {
					queue.add(new int[] { newRow, newCol });
				}
			}
		}

		while (!stack.isEmpty()) {
			int[] temp = stack.pop();
			bw.write(temp[0] + " " + temp[1] + " " + temp[2] + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void clear(int i, int j) {
		map[i][j] = 0;
		map[i][j + 1] = 0;
		map[i + 1][j] = 0;
		map[i + 1][j + 1] = 0;
	}

	private static boolean check(int i, int j) {
		if (i < 0 || j < 0 || i >= N || j >= M) {
			return false;
		}
		if (i + 1 < 0 || j + 1 < 0 || i + 1 >= N || j + 1 >= M) {
			return false;
		}
		if (map[i][j] == 0 && map[i][j + 1] == 0 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0) {
			return false;
		}

		return map[i][j] == (map[i][j + 1] == 0 ? map[i][j] : map[i][j + 1]) && map[i][j] == (map[i + 1][j] == 0 ? map[i][j] : map[i + 1][j]) && map[i][j] == (map[i + 1][j + 1] == 0 ? map[i][j] : map[i + 1][j + 1]);
	}

}