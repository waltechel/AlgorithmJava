import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 */
public class Main1702C_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int N, K;
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			HashMap<Integer, int[]> map = new HashMap<>();
			for (int index = 0; index < N; index++) {
				int key = A[index];
				if (map.containsKey(key)) {
					int[] value = map.get(key);
					value[0] = Math.min(value[0], index);
					value[1] = Math.max(value[1], index);
					map.put(key, value);
				} else {
					int[] value = new int[2];
					value[0] = index;
					value[1] = index;
					map.put(key, value);
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a, b;
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(!map.containsKey(a) || !map.containsKey(b)) {
					bw.write("NO\n");
					continue;
				}

				int from = map.get(a)[0];
				int to = map.get(b)[1];
				if (from < to) {
					bw.write("YES\n");
				} else {
					bw.write("NO\n");
				}
			}

		}

		bw.flush();
		br.close();
		bw.close();
	}

}