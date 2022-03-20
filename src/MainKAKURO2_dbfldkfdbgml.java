import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainKAKURO2_dbfldkfdbgml {

	private static int[][] map;
	private static int[][] answer;
	private static int N, Q;
	private static final int MAX = 100;
	private static ArrayList<int[]>[][] horizontal;
	private static ArrayList<int[]>[][] vertical;
	private static boolean[][] isHorizontalLastPoint;
	private static boolean[][] isVerticalLastPoint;
	private static int M;
	private static boolean find;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			M = 0;
			answer = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						M++;
					} else if (map[i][j] == 0) {
						answer[i][j] = MAX;
					}
				}
			}

			horizontal = new ArrayList[N][N];
			vertical = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					horizontal[i][j] = new ArrayList<int[]>();
					vertical[i][j] = new ArrayList<int[]>();
				}
			}

			isHorizontalLastPoint = new boolean[N][N];
			isVerticalLastPoint = new boolean[N][N];
			Q = Integer.parseInt(br.readLine());
			for (int a = 0; a < Q; a++) {
				int[] temp = new int[4];
				st = new StringTokenizer(br.readLine());
				temp[0] = Integer.parseInt(st.nextToken()) - 1;
				temp[1] = Integer.parseInt(st.nextToken()) - 1;
				temp[2] = Integer.parseInt(st.nextToken());
				temp[3] = Integer.parseInt(st.nextToken());

				if (temp[2] == 0) {
					for (int j = temp[1]; j < N; j++) {
						horizontal[temp[0]][j].add(temp);
						if (j + 1 >= N || map[temp[0]][j + 1] == 0) {
							isHorizontalLastPoint[temp[0]][j] = true;
							break;
						}
					}
				} else {
					for (int i = temp[0]; i < N; i++) {
						vertical[i][temp[1]].add(temp);
						if (i + 1 >= N || map[i + 1][temp[1]] == 0) {
							isVerticalLastPoint[i][temp[1]] = true;
							break;
						}
					}
				}
			}


			find = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						for (int k = 9; k >= 1; k--) {
							if (isSafe(i, j, k)) {
								dfs(i, j, k, 1);
							}
						}
					}
				}
			}

		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int r, int c, int n, int cnt) {

		answer[r][c] = n;

		if (find) {
			return;
		}
		if (cnt == M) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (answer[i][j] == MAX) {
						System.out.print("0 ");
					} else {
						System.out.print(answer[i][j] + " ");
					}
				}
				System.out.println();
			}
			find = true;
			return;
		}

		for (int i = r; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == r && j <= c) {
					continue;
				}
				if (map[i][j] == 1) {
					for (int k = 9; k >= 1; k--) {
						if (isSafe(i, j, k)) {
							dfs(i, j, k, cnt + 1);
						}
					}
				}
			}
		}

	}

	private static boolean isSafe(int r, int c, int k) {

		for (int[] horizontalCondition : horizontal[r][c]) {
			int fromRow = horizontalCondition[0];
			int fromCol = horizontalCondition[1];
			int max = horizontalCondition[3];

			int visited = 0;
			int sum = 0;
			for (int j = fromCol; j < c; j++) {
				int n = answer[fromRow][j];
				if (n == MAX) {
					continue;
				}
				visited |= (1 << n);
				sum += n;
			}
			if (sum + k > max || (isHorizontalLastPoint[r][c] && sum + k != max)) {
				return false;
			}
			if ((visited & (1 << k)) != 0) {
				return false;
			}
		}

		for (int[] verticalCondition : vertical[r][c]) {
			int fromRow = verticalCondition[0];
			int fromCol = verticalCondition[1];
			int max = verticalCondition[3];
			int visited = 0;
			int sum = 0;

			for (int i = fromRow; i < r; i++) {
				int n = answer[i][fromCol];
				if (n == MAX) {
					continue;
				}
				visited |= (1 << n);
				sum += n;
			}
			if (sum + k > max || (isVerticalLastPoint[r][c] && sum + k != max)) {
				return false;
			}
			if ((visited & (1 << k)) != 0) {
				return false;
			}

		}

		return true;
	}
}
