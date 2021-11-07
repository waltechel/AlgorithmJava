import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 
 * @author leedongjun
 *
 * 짝꿍을 다 만들어주고, 그 짝꿍이 서로 친구인지를 검색해본다.       
 */
public class MainPICNIC_dbfldkfdbgml {

	private static boolean[][] isRelated;
	private static int checked;
	private static int N, M;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			isRelated = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				isRelated[from][to] = true;
				isRelated[to][from] = true;
			}

			checked = 0;
			answer = 0;
			for (int i = 0; i < N; i++) {
				if ((checked & (1 << i)) == 0) {
					for (int j = i + 1; j < N; j++) {
						if ((checked & (1 << j)) == 0) {
							if (isRelated[i][j]) {
								dfs(i, j);
								clear(i, j);
							}
						}
					}
					break;
				}
			}
			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void clear(int i, int j) {
		checked &= ~(1 << i);
		checked &= ~(1 << j);
	}

	private static void check(int i, int j) {
		checked |= (1 << i);
		checked |= (1 << j);
	}

	private static void dfs(int i1, int i2) {
		check(i1, i2);

		if (checked == (1 << N) - 1) {
			answer++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((checked & (1 << i)) == 0) {
				for (int j = i + 1; j < N; j++) {
					if ((checked & (1 << j)) == 0) {
						if (isRelated[i][j]) {
							dfs(i, j);
							clear(i, j);
						}
					}
				}
				break;
			}
		}

	}

}