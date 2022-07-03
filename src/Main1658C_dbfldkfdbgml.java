import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * if there exists a position i such that Ci+1 - Ci > 1, the answer is NO;
 * @author developer
 *
 */
public class Main1658C_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[] C = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				C[i] = Integer.parseInt(st.nextToken());
			}

			int cntOfOne = 0;
			for (int i = 0; i < N; i++) {
				if (C[i] == 1) {
					cntOfOne++;
				}
			}
			if (cntOfOne != 1) {
				bw.write("NO\n");
				continue;
			}

			// 1이 있는 곳으로 돌려야 하는 이유는 모르겠다.
			ArrayList<Integer> list = new ArrayList<>();
			int index = -1;
			for (int i = 0, flag = 0; i < N; i++) {
				if (C[i] == 1 || flag == 1) {
					list.add(C[i]);
					flag = 1;
				}
				if (C[i] == 1) {
					index = i;
				}
			}
			for (int i = 0; i < index; i++) {
				list.add(C[i]);
			}
			for (int i = 0; i < N; i++) {
				C[i] = list.get(i);
			}

			boolean flag = true;
			for (int i = 0; i < N - 1; i++) {
				if (C[i + 1] - C[i] > 1) {
					flag = false;
				}
			}
			if (flag == false) {
				bw.write("NO\n");
				continue;
			} else {
				bw.write("YES\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();

	}

}
