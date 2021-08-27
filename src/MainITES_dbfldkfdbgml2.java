import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MainITES_dbfldkfdbgml2 {

	/**
	 * 큐를 짜지 말고 Deque으로 문제를 풀면 풀리는 수도 있었다.
	 * (하지만 이 소스는 되지 않는다 ㅠㅠ)
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {
			int N, K;
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			Deque<Integer> queue = new LinkedList<>();
			long M = 0;

			int answer = 0;
			long[] A = new long[2];
			A[0] = 1983;
			for (int i = 1; i <= N; i++) {
				int temp = (int) ((A[(i - 1) % 2] % 10000) + 1);
				M += temp;
				queue.addLast(temp);
				while (M >= K) {
					if (M == K) {
						answer++;
					}
					M -= queue.removeFirst();
				}
				A[i % 2] = (A[(i - 1) % 2] * 214013l + 2531011) % (1l << 32);
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
