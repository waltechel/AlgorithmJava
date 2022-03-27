import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 원형 구간을 선형 구간으로 바꾸는 기법
 * 라인 스위핑 추가
 * 
 * @author leedongjun
 *
 */
public class MainMINASTIRITH_dbfldkfdbgml {

	private static final double PI = Math.PI;
	private static final int MAX = Integer.MAX_VALUE / 10;
	private static double[] y, x, r;
	private static int N;
	private static Pair[] ranges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {

			N = Integer.parseInt(br.readLine());
			y = new double[N];
			x = new double[N];
			r = new double[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				y[i] = Double.parseDouble(st.nextToken());
				x[i] = Double.parseDouble(st.nextToken());
				r[i] = Double.parseDouble(st.nextToken());
			}

			convertToRange();

			int ret = solve();

			if (ret != MAX) {
				bw.write(ret + "\n");
			} else {
				bw.write("IMPOSSIBLE\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int solve() {
		int ret = MAX;
		for (int i = 0; i < N; i++) {
			if (ranges[i].first <= 0 || ranges[i].second >= 2 * PI) {
				// i 번째 항목을 0 을 포함하는 값으로 선정하는 경우
				double begin = fmod(ranges[i].second, 2 * PI);
				double end = fmod(ranges[i].first + 2 * PI, 2 * PI);
				ret = Math.min(ret, 1 + linear(begin, end));
			}
		}
		return ret;
	}

	private static int linear(double begin, double end) {
		int used = 0, idx = 0;
		// 덮지 못한 선분이 남아 있는 동안 계속한다.
		while (begin < end) {
			// begin보다 이전에 시작하는 구간 중 가장 늦게 끝나는 구간을 찾는다.
			// 크게 덮을 수 있는 하나를 찾는 과정
			double maxCover = -1;
			while (idx < N && ranges[idx].first <= begin) {
				maxCover = Math.max(maxCover, ranges[idx++].second);
			}

			// 선분을 덮지 못하게 될 떄
			if (maxCover <= begin) {
				return MAX;
			} else {
				// 크게 덮을 수 있는 하나를 사용
				begin = maxCover;
				used++;
			}

		}

		return used;
	}

	private static void convertToRange() {
		ranges = new Pair[N];
		for (int i = 0; i < N; i++) {
			double loc = fmod(2 * PI + Math.atan2(y[i], x[i]), 2 * PI);
			double range = 2.0f * Math.asin(r[i] / 2.0 / 8.0);
			ranges[i] = new Pair(loc - range, loc + range);
		}
		Arrays.sort(ranges, (o1, o2) -> Double.compare(o1.first, o2.first) != 0 ? Double.compare(o1.first, o2.first) : Double.compare(o2.second, o1.second));
	}

	private static double fmod(double a, double b) {
		int result = (int) Math.floor(a / b);
		return a - result * b;
	}

	private static class Pair {
		double first;
		double second;

		public Pair(double first, double second) {
			super();
			this.first = first;
			this.second = second;
		}

	}

}