import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1638A_dbfldkfdbgml {

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

			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(A[i]);
			}
			Collections.sort(list);

			boolean[] check = new boolean[N];
			for (int i = 0; i < N; i++) {
				if (list.get(i) != A[i]) {
					check[i] = true;
				}
			}

			int from = -1, to = -1;
			FOR: for (int i = 0; i < N; i++) {
				// 만약 있어야 할 자리에 없다면
				if (check[i]) {
					for (Integer e : list) {
						for (int j = 0; j < N; j++) {
							if (i == j) {
								continue;
							}
							if (check[j] && A[j] == e) {
								from = i;
								to = j;
								break FOR;
							}
						}
					}
				}
			}

			if (from == -1 && to == -1) {
				for (int i = 0; i < N; i++) {
					bw.write(A[i] + " ");
				}
				bw.write("\n");
			} else {
				for (int i = 0; i < from; i++) {
					bw.write(A[i] + " ");
				}
				for (int i = to; i >= from; i--) {
					bw.write(A[i] + " ");
				}
				for (int i = to + 1; i < N; i++) {
					bw.write(A[i] + " ");
				}
				bw.write("\n");
			}

		}

		bw.flush();
		br.close();
		bw.close();
	}

}