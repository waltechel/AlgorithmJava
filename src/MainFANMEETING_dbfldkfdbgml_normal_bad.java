import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainFANMEETING_dbfldkfdbgml_normal_bad {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			char[] members = br.readLine().toCharArray();
			char[] fans = br.readLine().toCharArray();

			boolean[] A = new boolean[members.length];
			for (int i = 0; i < members.length; i++) {
				A[i] = members[i] == 'M';
			}
			boolean[] B = new boolean[fans.length];
			for (int i = 0; i < fans.length; i++) {
				B[i] = fans[fans.length - 1 - i] == 'M';
			}

			int answer = 0;
			FOR: for (int i = 0; i < members.length + fans.length - 1; i++) {
				if (i < A.length - 1) {
					continue;
				}
				boolean check = true;
				for (int j = 0; j < A.length; j++) {
					int k = i - j;
					if (k >= B.length) {
						break FOR;
					}
					// 남자 남자가 만났다
					if (A[j] & B[k]) {
						check = false;
						break;
					}
				}
				if (check) {
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