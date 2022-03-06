import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 작은 애를 많이 더하는 것이 좋다.
 *
 * @author leedongjun
 *
 */
public class MainSTRJOIN_dbfldkfgbdml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> queue = new PriorityQueue<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}

			int answer = 0;
			while (queue.size() >= 2) {
				int n1 = queue.poll();
				int n2 = queue.poll();
				answer += (n1 + n2);
				queue.add((n1 + n2));
			}

			bw.write(answer + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

}