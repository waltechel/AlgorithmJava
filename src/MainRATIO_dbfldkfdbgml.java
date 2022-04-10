import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author leedongjun
 *
 */
public class MainRATIO_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			long answer = 0;
			long left = 0;
			long right = 4_000_000_000l;
			// 된다 / 안된다 골라내다가 고정값이 된다.
			for (int i = 0; i < 100; i++) {
				answer = (left + right) / 2l;
				if (isSafe(answer, N, M)) {
					right = answer;
				} else {
					left = answer;
				}
			}

			if (answer + 1 <= 2_000_000_000l) {
				bw.write((answer + 1) + "\n");
			} else {
				bw.write(-1 + "\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isSafe(long answer, long N, long M) {
		long Z = (M * 100) / N;
		long Z1 = ((M + answer) * 100) / (N + answer);
		return Z1 > Z;
	}

}