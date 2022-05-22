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

public class Main1638B_dbfldkfdbgml_bad {

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

			int oddCount = 0;
			int falseCount = 0;
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(A[i]);
			}
			Collections.sort(list);
			for (int i = 0; i < N; i++) {
				int a = A[i];
				int b = list.get(i);
				if (a != b) {
					falseCount++;
					if (a % 2 != 0) {
						oddCount++;
					}
				}
			}

			if (falseCount <= oddCount * 2) {
				bw.write("Yes\n");
			} else {
				bw.write("No\n");
			}

		}

		bw.flush();
		br.close();
		bw.close();
	}

}