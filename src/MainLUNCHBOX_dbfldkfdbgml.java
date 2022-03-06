import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 이상한 문제
 * 입은 하나가 아닌데 전자는 하나임
 * 
 * 1
 * 4
 * 1 2 3 4
 * 2 3 4 5
 * 12
 *
 * @author leedongjun
 *
 */
public class MainLUNCHBOX_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			int N = Integer.parseInt(br.readLine());

			int[] M = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}

			int[] E = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				E[i] = Integer.parseInt(st.nextToken());
			}

			Pair[] P = new Pair[N];
			for (int i = 0; i < N; i++) {
				P[i] = new Pair(M[i], E[i]);
			}

			Arrays.sort(P, new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					return o2.eatingTime - o1.eatingTime;
				}
			});

			int makingTimeLine = 0;
			int totalTimeLine = 0;
			for (Pair p : P) {
				makingTimeLine += p.makingTime;
				totalTimeLine = Math.max(makingTimeLine + p.eatingTime, totalTimeLine);
			}
			bw.write(totalTimeLine + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static class Pair {

		int makingTime;
		int eatingTime;

		public Pair(int makingTime, int eatingTime) {
			this.makingTime = makingTime;
			this.eatingTime = eatingTime;
		}

	}

}