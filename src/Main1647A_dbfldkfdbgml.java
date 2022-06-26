import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 *
 */
public class Main1647A_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		String[] A = new String[1001];
		A[1] = "1";
		A[2] = "2";
		A[3] = "21";

		for (int i = 4; i <= 1000; i++) {
			if (i % 3 == 1) {
				A[i] = "1" + A[i - 1];
				continue;
			} else if (i % 3 == 2) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < A[i - 1].length(); j++) {
					sb.append(A[i - 1].charAt(j) == '1' ? "2" : "1");
				}
				A[i] = sb.toString();
				continue;
			} else {
				A[i] = A[i - 1] + "1";
				continue;
			}
		}

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(A[N] + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
}