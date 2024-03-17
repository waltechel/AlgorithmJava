import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1933_dbfldkfdbgml {

	/**
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int[] tree = new int[30];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int L, H, R;
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			for (int j = L; j < R; j++) {
				tree[j] = Math.max(tree[j], H);
			}
		}

		System.out.println("answer");
		int beforeValue = 0;
		for (int i = 1; i < tree.length; i++) {
			if (beforeValue != tree[i]) {
				System.out.println(i + " " + tree[i]);
				beforeValue = tree[i];
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

}