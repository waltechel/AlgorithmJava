import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1642B_dbdlfkdfbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
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

			PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[1], o2[1]);
				}
			});
			for (Entry<Integer, Integer> e : map.entrySet()) {
				int key = e.getKey();
				int value = e.getValue();
				queue.add(new int[] { key, value });
			}

			int answer = map.size();
			while (!queue.isEmpty()) {
				bw.write(answer + " ");
				int[] temp = queue.poll();
				int cnt = temp[1];
				if (cnt >= 2) {
					++answer;
					temp[1]--;
					queue.add(temp);
				}
			}
			bw.write("\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}