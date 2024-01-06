import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 */
public class Main1665B_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			Map<Integer, Integer> map = new HashMap<>();
			for (Integer i : A) {
				if (map.containsKey(i)) {
					int value = map.get(i);
					map.put(i, value + 1);
				} else {
					map.put(i, 1);
				}
			}

			int max = 0;
			for (Entry<Integer, Integer> e : map.entrySet()) {
				max = Math.max(max, e.getValue());
			}

			int answer = 0;
			int a = N;
			int b = max;
			while (a > b) {
				// 복사
				answer += 1;
				int b2 = b * 2;
				if (a >= b2) {
					answer += b;
					b += b;
					continue;
				} else {
					int diff = a - b;
					answer += diff;
					b += diff;
				}
			}

			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}
}