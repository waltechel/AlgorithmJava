import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 * B번은 못 풀겠음
 * 직사각형이 있는 곳은 모두 1로 채워져야 함
 * 1로 채워지지 않으면 아예 떨어지기라도 해야 함
 * 11111
 * 01010
 * 01000
 * 01000
 * 이거는 안되는데 구현을 못하겠다.
 */
public class Main1647B_dbfldkfdbgml {

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