import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1931_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Pair[] A = new Pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			A[i] = new Pair(from, to);
		}

		Arrays.sort(A, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.to - o2.to != 0) {
					return o1.to - o2.to;
				} else {
					return o1.from - o2.from;
				}
			}
		});

		// 일찍 끝나는 거부터 회의 갖다 넣는다.
		int timecut = -1;
		int answer = 0;
		for (Pair p : A) {
			if (timecut <= p.from) {
				timecut = p.to;
				answer++;
			}
		}
		bw.write(answer + "");

		bw.flush();
		bw.close();
		br.close();
	}

	private static class Pair {
		int from, to;

		public Pair(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
}
