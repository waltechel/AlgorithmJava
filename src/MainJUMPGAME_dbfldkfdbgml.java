import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainJUMPGAME_dbfldkfdbgml {

	// 이 때 각 칸에 적혀 있는 숫자만큼 오른쪽이나 아래 칸으로 움직일 수 있으며, 중간에 게임판 밖으로 벗어나면 안 됩니다.
	private static int[][] directions = new int[][] { { 1, 0 },  { 0, 1 } };

	// 매우 빠르게 풀 수 있음
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// C(C <= 50)가 주어집니다.
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			//  테스트 케이스의 첫 줄에는 격자의 크기 n(2 <= n <= 100)이 주어지고,
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			dist[0][0] = 0;
			Queue<int[]> queue = new LinkedList<>();
			queue.add(new int[] { 0, 0, dist[0][0] });
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				int r = now[0];
				int c = now[1];
				int depth = now[2];
				if (dist[r][c] < depth) {
					continue;
				}
				for (int d = 0; d < directions.length; d++) {
					int newRow = r + directions[d][0] * map[r][c];
					int newCol = c + directions[d][1] * map[r][c];
					if (newRow < N && newCol < N && newRow >= 0 && newCol >= 0 && dist[newRow][newCol] > depth + 1) {
						dist[newRow][newCol] = depth + 1;
						queue.add(new int[] { newRow, newCol, dist[newRow][newCol] });
					}
				}
			}

			if (dist[N - 1][N - 1] != Integer.MAX_VALUE) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

}