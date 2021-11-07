import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainBISHOPS_dbfldkfdbgml {

	/**
	 * Bishops (최대로 놓을 수 있는 비숍 수)
	 * https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=wnsgh224&logNo=220716544342
	 * 다음 블로그를 참고함
	 */

	private static int[][] obs;
	private static int N;
	private static int[][] leftIndex, rightIndex, graph;
	private static int leftNum, rightNum;
	private static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {

			N = Integer.parseInt(br.readLine());
			obs = new int[N][N];
			leftIndex = new int[N][N];
			rightIndex = new int[N][N];
			graph = new int[N * N * 3][N * N * 3];
			leftNum = 0;
			rightNum = 0;
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					obs[i][j] = (line[j] == '*' ? 1 : 0);
				}
			}

			findDiagonal();
			System.out.println("li is : ");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(leftIndex[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("ri is : ");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(rightIndex[i][j] + " ");
				}
				System.out.println();
			}

			setNetwork();
			int answer = networkFlow(0, leftNum + rightNum + 1);
			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static int networkFlow(int src, int sink) {
		int total = 0;
		for (int i = 0; i <= sink; i++) {
			check = new boolean[leftNum + rightNum + 2];
			if (dfs(src, sink) == false) {
				break;
			}
			total++;
		}
		return total;

	}

	private static boolean dfs(int now, int sink) {
		check[now] = true;
		if (now == sink) {
			return true;
		}
		for (int i = 0; i <= sink; i++) {
			if (graph[now][i] > 0 && check[i] == false) {
				if (dfs(i, sink)) {
					graph[now][i]--;
					graph[i][now]++;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 그래프 모델링하는 부분
	 * from -> to로 이어진다.
	 * 0 은 모든 정점의 출발점
	 * leftNum + rightNum + 1은 모든 정점의 종착점
	 */
	private static void setNetwork() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (obs[i][j] == 0) {
					int from = leftIndex[i][j];
					int to = leftNum + rightIndex[i][j];
					addEdge(from, to);
				}
		// 0 에서 leftNum은 왼쪽 leftIndex로 연결한다
		for (int i = 1; i <= leftNum; i++) {
			addEdge(0, i);
		}
		// leftNum + 1(rightIndex) 부터 leftNum + rightNum 까지 leftNum + rightNum + 1(=끝점)으로 연결한다.
		for (int i = leftNum + 1; i <= leftNum + rightNum; i++) {
			addEdge(i, leftNum + rightNum + 1);
		}
	}

	private static void addEdge(int i, int j) {
		graph[i][j] = 1;
		graph[j][i] = 0;
	}

	/**
	 * 번호를 매기는 부분
	 */
	private static void findDiagonal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (leftIndex[i][j] == 0 && obs[i][j] == 0) {
					leftNum++;
					for (int k = 0; k < N; k++) {
						if (i + k >= N || j + k >= N || obs[i + k][j + k] != 0) {
							break;
						}
						leftIndex[i + k][j + k] = leftNum;
					}
				}
				if (rightIndex[i][j] == 0 && obs[i][j] == 0) {
					rightNum++;
					for (int k = 0; k < N; k++) {
						if (i + k >= N || j - k < 0 || obs[i + k][j - k] != 0) {
							break;
						}
						rightIndex[i + k][j - k] = rightNum;
					}
				}
			}
		}
	}
}