import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainRUNNINGMEDIAN_dbfldkfdbgml {
	
	/**
	 * left  : maxheap
	 * right : minheap
	 * queue : 3개의 대소 비교
	 */

	private static final int MOD = 20090711;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N, a, b;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			long[] A = new long[N];
			A[0] = 1983l;
			for (int i = 1; i < N; i++) {
				A[i] = (A[i - 1] * a + b) % MOD;
			}

			PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
			left.add(-1);
			PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
			right.add(20090711);
			PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
			long answer = 0;
			for (int i = 0, c1 = 0, c2 = 0, c3 = 0; i < N; i++) {
				c1 = left.poll();
				c2 = right.poll();
				c3 = (int) A[i];
				queue.add(c1);
				queue.add(c2);
				queue.add(c3);
				c1 = queue.poll();
				c2 = queue.poll();
				c3 = queue.poll();
				if (left.size() <= right.size()) {
					left.add(c1);
					left.add(c2);
					right.add(c3);
				} else {
					left.add(c1);
					right.add(c2);
					right.add(c3);
				}
				answer += left.peek();
				answer %= MOD;
			}
			bw.write(answer + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

}