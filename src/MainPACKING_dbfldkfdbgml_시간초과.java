import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class MainPACKING_dbfldkfdbgml_시간초과 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		HashSet<String>[][] answer = new HashSet[100 + 1][1000 + 1];
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 1000; j++) {
				answer[i][j] = new HashSet<>();
			}
		}

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

			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= W; j++) {
					answer[i][j].clear();
				}
			}

			int[][] dp = new int[N + 1][W + 1];
			int max = 0, maxI = -1, maxJ = -1;
			for (int i = 0; i < N; i++) {
				int cost = list.get(i).getWeight();
				int value = list.get(i).getValue();
				for (int j = 0; j + cost <= W; j++) {
					if ((j == 0 || dp[i][j] != 0) && dp[i + 1][j + cost] < dp[i][j] + value) {
						dp[i + 1][j + cost] = dp[i][j] + value;
						answer[i + 1][j + cost].clear();
						answer[i + 1][j + cost].addAll(answer[i][j]);
						answer[i + 1][j + cost].add(list.get(i).getName());

					}
				}
				for (int j = 0; j <= W; j++) {
					if (dp[i + 1][j] < dp[i][j]) {
						dp[i + 1][j] = dp[i][j];
						answer[i + 1][j].clear();
						answer[i + 1][j].addAll(answer[i][j]);
					}
					if (max < dp[i + 1][j]) {
						max = dp[i + 1][j];
						maxI = i + 1;
						maxJ = j;
					}
				}
			}

			bw.write(max + " " + answer[maxI][maxJ].size() + "\n");
			for (String s : answer[maxI][maxJ]) {
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
