import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 */
public class Main1702F_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			int[] B = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				while (A[i] % 2 == 0) {
					A[i] /= 2;
				}
			}
			// System.out.println(Arrays.toString(A));

			HashMap<Integer, Integer> C = new HashMap<>();
			for (int i = 0; i < N; i++) {
				int key = A[i];
				if (C.containsKey(key)) {
					int value = C.get(key);
					C.put(key, value + 1);
				} else {
					C.put(key, 1);
				}
			}
			// System.out.println(C.toString());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				B[i] = Integer.parseInt(st.nextToken());
				while (B[i] % 2 == 0) {
					B[i] /= 2;
				}
			}

			boolean flag = true;
			for (int i = 0; i < N; i++) {
				int key = B[i];
				while (key > 0) {
					if (!C.containsKey(key) || (C.containsKey(key) && C.get(key) == 0)) {
						key /= 2;
					} else {
						break;
					}
				}
				if(key == 0) {
					flag = false;
					break;
				}else {
					int value = C.get(key);
					C.put(key, value - 1);
				}
			}

			if (flag) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}

		}

		bw.flush();
		br.close();
		bw.close();
	}

}