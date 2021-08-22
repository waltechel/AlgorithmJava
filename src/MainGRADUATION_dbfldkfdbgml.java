import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainGRADUATION_dbfldkfdbgml {

	/*
	 * 비트마스크를 쓰는 코드는 다음과 같은 장점을 가진다.
	 * 1. 더 빠른 수행시간
	 * 2. 더 간결한 코드
	 * 3. 작은 메모리 사용량
	 * 4. 레퍼런스 타입을 primitive type으로 대체할 수 있다.
	 */

	private static int[] pre;
	private static int[] cemester;
	private static int N, K, M, L;
	private static int[][] dp;
	private static final int MAX = Integer.MAX_VALUE / 10000;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			/*
			 * N : 전공 과목의 수(1 <= N <= 12)
			 * K : 들어야 할 과목의 수
			 * M : 학기의 수 (1 <= M <= 10)
			 * L : 한 학기에 최대로 들을 수 있는 과목의 수(1 <= L <= 10)
			 */
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			// 과목은 12개이므로 비트마스크로 표현 가능하다.
			pre = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int R = Integer.parseInt(st.nextToken());
				for (int j = 0; j < R; j++) {
					pre[i] |= (1 << (Integer.parseInt(st.nextToken())));
				}
			}

			cemester = new int[M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken());
				for (int j = 0; j < C; j++) {
					cemester[i] |= (1 << (Integer.parseInt(st.nextToken())));
				}
			}

			dp = new int[M][1 << 12];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < (1 << 12); j++) {
					dp[i][j] = MAX;
				}
			}
			int answer = dfs2(0, 0);
			if (answer == MAX) {
				bw.write("IMPOSSIBLE\n");
			} else {
				bw.write("" + answer + "\n");
			}
		}

		br.close();
		bw.close();

	}

	/**
	 * 현재 i번째 학기에서 수강한 class들의 상태가 visited일 때
	 * k 개 이상의 과목을 모두 들으려면 몇 학기나 있어야 하는가?
	 * 불가능하다면 Integer.MAX_VALUE를 반환한다.
	 * 
	 * @param index
	 * @param visited
	 * @return
	 */
	private static int dfs2(int index, int visited) {

		if (bitCount(visited) >= K) {
			return 0;
		}
		if (index == M) {
			return MAX;
		}
		int ret = dp[index][visited];
		if (ret != MAX) {
			return ret;
		}

		int canTake = (cemester[index] & ~visited);
		for (int i = 0; i < N; i++) {
			if (((canTake & (1 << i)) > 0) && ((pre[i] & visited) != pre[i])) {
				canTake &= (~(1 << i));
			}
		}
		if(bitCount(canTake) <= L) {
			ret = Math.min(ret, dfs2(index + 1, visited | canTake) + 1);
		}else {
			for (int course = canTake; course > 0; course = ((course - 1) & canTake)) {
				if (bitCount(course) > L) {
					continue;
				}
				ret = Math.min(ret, dfs2(index + 1, visited | course) + 1);
			}
		}
		
		ret = Math.min(ret, dfs2(index + 1, visited));
		return dp[index][visited] = ret;
	}

	private static int bitCount(int visited) {
		int ret = 0;
		for (int i = 0; i < 12; i++) {
			if ((visited & (1 << i)) > 0) {
				ret++;
			}
		}
		return ret;
	}

	
	
	private static int answer;
	private static boolean isFinished;
	/**
	 * 이 소스는 돌지 않았습니다.
	 * @param index index 번째 학기
	 * @param flag 수강할까 말까
	 * @param visited 여태까지 들은 과목의 상태
	 * @param cntOfCemester 학기의 수
	 */
	private static void dfs(int index, boolean flag, int visited, int cntOfCemester) {

		if (isFinished && answer < cntOfCemester) {
			return;
		}
		if (index == M) {
			return;
		}

		// 최대 L개 과목까지 학습할 수 있다.
		if (flag) {
			int maxCoursesOfthisCemester = cemester[index];
			for (int courses = 0; courses <= maxCoursesOfthisCemester; courses++) {
				if (courses != (courses & maxCoursesOfthisCemester)) {
					continue;
				}

				int count = 0;
				for (int i = 0; i < N; i++) {
					if ((courses & (1 << i)) == (1 << i)) {
						count++;
					}
				}
				int next = visited;
				for (int i = 0; count <= L && i < N; i++) {
					if ((courses & (1 << i)) != 0) {
						if (pre[i] == 0 || (pre[i] == (pre[i] & visited))) {
							next |= (1 << i);
						}
					}
				}

				for (int i = 0, cnt = 0; i < N; i++) {
					if ((next & (1 << i)) == (1 << i)) {
						cnt++;
					}
					if (cnt >= K) {
						isFinished = true;
						answer = Math.min(answer, cntOfCemester);
					}
				}

				dfs(index + 1, true, next, cntOfCemester + 1);
				dfs(index + 1, false, next, cntOfCemester);

			}

		}

		for (int i = 0, cnt = 0; i < N; i++) {
			if ((visited & (1 << i)) == (1 << i)) {
				cnt++;
			}
			if (cnt >= K) {
				isFinished = true;
				answer = Math.min(answer, cntOfCemester);
				return;
			}
		}

		dfs(index + 1, true, visited, cntOfCemester + 1);
		dfs(index + 1, false, visited, cntOfCemester);

	}

}
