import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main7476_dbfldkfdbgml {

	private static int[][] dp;
	private static int[] A, B;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int m1 = Integer.parseInt(br.readLine());
		A = new int[m1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int m2 = Integer.parseInt(br.readLine());
		B = new int[m2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m2; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[m1][m2];
		for (int i = 0; i < m1; i++) {
			for (int j = 0; j < m2; j++) {
				dp[i][j] = -1;
			}
		}
		bw.write(dfs(m1 - 1, m2 - 1) + "\n");
		bw.write(dfs2(m1 - 1, m2 - 1) + "\n");

		bw.flush();
		bw.close();
		br.close();

	}

	private static String dfs2(int a1, int b1) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int dfs(int a1, int b1) {
		if (a1 == 0 || b1 == 0) {
			if (A[a1] == B[b1]) {
				return dp[a1][b1] = 1;
			} else {
				return dp[a1][b1] = 0;
			}
		}
		if (dp[a1][b1] != -1) {
			return dp[a1][b1];
		}
		int ret = 0;
		for (int i = 0; i < a1; i++) {
			for (int j = 0; j < b1; j++) {
				ret = Math.max(ret, dfs(i, j) + (A[a1] == B[b1] ? 1 : 0));
			}
		}
		return dp[a1][b1] = ret;
	}

}
