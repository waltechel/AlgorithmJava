import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 이 방법은 별로 좋지 않음
 * 배열을 쓰는데 이방법으로는 배열 복사하다가 시간이 다 가버린다
 * 따라서 각 정점까지의 최단 거리를 기록하는 데에도 map을 쓰는 대신 정수 배열을 사용할 수 있습니다.
 * @author leedongjun
 *
 */
public class MainHANOI_dbfldkfdbgml_bad {

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		HashMap<String, Integer> answerMap = new HashMap<>();
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 }), 0);
		answerMap.put(Arrays.toString(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 }), 0);

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0, 0, 2 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 });
		queue.add(new int[] { 0, 0, 0, 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 });

		final int MAX_DEPTH = 40;
		int[] next;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int d = answerMap.get(Arrays.toString(now));
			if (d == MAX_DEPTH) {
				continue;
			}

			if (now[0] > 0) {
				int p = (now[0] & -now[0]);
				now[0] &= ~p;
				next = new int[] { now[0], now[1] | p, now[2], now[3] };
				if (((now[1] & -now[1]) == 0 || (now[1] & -now[1]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1], now[2] | p, now[3] };
				if (((now[2] & -now[2]) == 0 || (now[2] & -now[2]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1], now[2], now[3] | p };
				if (((now[3] & -now[3]) == 0 || (now[3] & -now[3]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				now[0] |= p;
			}
			if (now[1] > 0) {
				int p = (now[1] & -now[1]);
				now[1] &= ~p;
				next = new int[] { now[0] | p, now[1], now[2], now[3] };
				if (((now[0] & -now[0]) == 0 || (now[0] & -now[0]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1], now[2] | p, now[3] };
				if (((now[2] & -now[2]) == 0 || (now[2] & -now[2]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1], now[2], now[3] | p };
				if (((now[3] & -now[3]) == 0 || (now[3] & -now[3]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				now[1] |= p;
			}
			if (now[2] > 0) {
				int p = (now[2] & -now[2]);
				now[2] &= ~p;
				next = new int[] { now[0] | p, now[1], now[2], now[3] };
				if (((now[0] & -now[0]) == 0 || (now[0] & -now[0]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1] | p, now[2], now[3] };
				if (((now[1] & -now[1]) == 0 || (now[1] & -now[1]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1], now[2], now[3] | p };
				if (((now[3] & -now[3]) == 0 || (now[3] & -now[3]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				now[2] |= p;
			}
			if (now[3] > 0) {
				int p = (now[3] & -now[3]);
				now[3] &= ~p;
				next = new int[] { now[0] | p, now[1], now[2], now[3] };
				if (((now[0] & -now[0]) == 0 || (now[0] & -now[0]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1] | p, now[2], now[3] };
				if (((now[1] & -now[1]) == 0 || (now[1] & -now[1]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				next = new int[] { now[0], now[1], now[2] | p, now[3] };
				if (((now[2] & -now[2]) == 0 || (now[2] & -now[2]) > p) && !answerMap.containsKey(Arrays.toString(next)) && d + 1 < MAX_DEPTH) {
					queue.add(next);
					answerMap.put(Arrays.toString(next), d + 1);
				}
				now[3] |= p;
			}
		}

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] start = new int[4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				int N2 = Integer.parseInt(st.nextToken());
				for (int j = 0; j < N2; j++) {
					int cnt = Integer.parseInt(st.nextToken());
					start[i] |= (1 << cnt);
				}
			}
			int answer = 0;

			HashMap<String, Integer> map = new HashMap<>();
			map.put(Arrays.toString(start), 0);
			queue = new LinkedList<>();
			queue.add(start);
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				int d = map.get(Arrays.toString(now));
				if (answerMap.containsKey(Arrays.toString(now))) {
					int d1 = answerMap.get(Arrays.toString(now));
					answer = d + d1;
					break;
				}

				if (now[0] > 0) {
					int p = (now[0] & -now[0]);
					now[0] &= ~p;
					next = new int[] { now[0], now[1] | p, now[2], now[3] };
					if (((now[1] & -now[1]) == 0 || (now[1] & -now[1]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1], now[2] | p, now[3] };
					if (((now[2] & -now[2]) == 0 || (now[2] & -now[2]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1], now[2], now[3] | p };
					if (((now[3] & -now[3]) == 0 || (now[3] & -now[3]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					now[0] |= p;
				}
				if (now[1] > 0) {
					int p = (now[1] & -now[1]);
					now[1] &= ~p;
					next = new int[] { now[0] | p, now[1], now[2], now[3] };
					if (((now[0] & -now[0]) == 0 || (now[0] & -now[0]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1], now[2] | p, now[3] };
					if (((now[2] & -now[2]) == 0 || (now[2] & -now[2]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1], now[2], now[3] | p };
					if (((now[3] & -now[3]) == 0 || (now[3] & -now[3]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					now[1] |= p;
				}
				if (now[2] > 0) {
					int p = (now[2] & -now[2]);
					now[2] &= ~p;
					next = new int[] { now[0] | p, now[1], now[2], now[3] };
					if (((now[0] & -now[0]) == 0 || (now[0] & -now[0]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1] | p, now[2], now[3] };
					if (((now[1] & -now[1]) == 0 || (now[1] & -now[1]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1], now[2], now[3] | p };
					if (((now[3] & -now[3]) == 0 || (now[3] & -now[3]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					now[2] |= p;
				}
				if (now[3] > 0) {
					int p = (now[3] & -now[3]);
					now[3] &= ~p;
					next = new int[] { now[0] | p, now[1], now[2], now[3] };
					if (((now[0] & -now[0]) == 0 || (now[0] & -now[0]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1] | p, now[2], now[3] };
					if (((now[1] & -now[1]) == 0 || (now[1] & -now[1]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					next = new int[] { now[0], now[1], now[2] | p, now[3] };
					if (((now[2] & -now[2]) == 0 || (now[2] & -now[2]) > p) && !map.containsKey(Arrays.toString(next))) {
						queue.add(next);
						map.put(Arrays.toString(next), d + 1);
					}
					now[3] |= p;
				}
			}
			bw.write(answer + "\n");

		}

		bw.flush();
		bw.close();
		br.close();

	}

}
