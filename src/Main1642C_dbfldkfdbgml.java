import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1642C_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			HashMap<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				if (map.containsKey(A[i])) {
					int count = map.get(A[i]);
					map.put(A[i], count + 1);
				} else {
					map.put(A[i], 1);
				}
			}

			PriorityQueue<Integer> queue = new PriorityQueue<>();
			for (Integer e : A) {
				queue.add(e);
			}

			int answer = 0;
			while (!queue.isEmpty()) {
				int key = queue.poll();
				// 이미 이 수가 소비되었다.
				if (map.get(key) <= 0) {
					continue;
				}
				int cnt = map.get(key);
				map.put(key, cnt - 1);

				int keyK = key * K;
				if (map.containsKey(keyK) && map.get(keyK) >= 1) {
					int cntK = map.get(keyK);
					map.put(keyK, cntK - 1);
				} else {
					answer++;
				}
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}