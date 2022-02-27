import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class MainPACKING_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());

		for (int test = 0; test < C; test++) {
			int N, W;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			ArrayList<Item> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Item(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			int[][] dp = new int[N + 1][W + 1];
			int[][][] prev = new int[N + 1][W + 1][2];
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= W; j++) {
					for (int k = 0; k < 2; k++) {
						prev[i][j][k] = -1;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				Item item = list.get(i);
				int value = item.getValue();
				int cost = item.getWeight();
				for (int j = 0; j <= W; j++) {
					if (j == 0 || dp[i][j] != 0) {
						if (j + cost <= W && dp[i + 1][j + cost] < dp[i][j] + value) {
							dp[i + 1][j + cost] = dp[i][j] + value;
							prev[i + 1][j + cost][0] = i;
							prev[i + 1][j + cost][1] = j;
						}
					}
				}
				for (int j = 0; j <= W; j++) {
					if (dp[i + 1][j] < dp[i][j]) {
						dp[i + 1][j] = dp[i][j];
						prev[i + 1][j][0] = prev[i][j][0];
						prev[i + 1][j][1] = prev[i][j][1];
					}
				}
			}

			int maxValue = 0;
			int maxWeight = -1;
			for (int i = 0; i <= W; i++) {
				if (dp[N][i] > maxValue) {
					maxValue = dp[N][i];
					maxWeight = i;
				}
			}

			HashSet<String> set = new HashSet<>();
			int maxN = N;
			while (prev[maxN][maxWeight][0] != -1 && prev[maxN][maxWeight][1] != -1) {
				int n = prev[maxN][maxWeight][0];
				int w = prev[maxN][maxWeight][1];
				set.add(list.get(n).getName());
				maxN = n;
				maxWeight = w;
			}

			bw.write(maxValue + " " + set.size() + "\n");
			for (String s : set) {
				bw.write(s + "\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static class Item {
		private String name;
		private int weight;
		private int value;

		public Item(String name, int weight, int value) {
			super();
			this.name = name;
			this.weight = weight;
			this.value = value;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

	}
}
