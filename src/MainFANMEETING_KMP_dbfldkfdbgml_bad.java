import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainFANMEETING_KMP_dbfldkfdbgml_bad {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			String P = br.readLine().trim();
			String S = br.readLine().trim();
			int[] f = new int[P.length()];
			for (int i = 1, j = 0; i < P.length();) {
				while (i < P.length() && j < P.length() && !isMatchM(P.charAt(i), P.charAt(j))) {
					f[i++] = ++j;
				}
				if (j == 0) {// 첫 글자에서 틀렸으면 S를 바로 다음으로 넘긴다.
					i++;
				} else {// 첫 글자 아니면 f[j-1]로 보낸다.
					j = f[j - 1];
				}
			}

			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0, j = 0; i < S.length();) {
				while (i < S.length() && j < P.length() && !isMatchM(S.charAt(i), P.charAt(j))) {
					i++;
					j++;
					if (j == P.length()) {
						list.add(i - P.length() + 1);
					}
				}
				if (j == 0) { // 첫 글자에서 틀렸으면 다음 글자로 보낸다.
					i++;
				} else { // 첫 글자가 아니면 이전에 맞은 데까지 보낸다.
					j = f[j - 1];
				}
			}

			bw.write("fail function is : " + Arrays.toString(f) + "\n");
			bw.write(list.size() + " " + list.toString() + "\n");

		}
		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean isMatchF(char c1, char c2) {
		if (c1 == 'F' && c2 == 'F') {
			return true;
		}
		return false;
	}
	
	private static boolean isMatchM(char c1, char c2) {
		if (c1 == 'M' && c2 == 'M') {
			return true;
		}
		return false;
	}

//	private static boolean isMatch(char c1, char c2) {
//		if (c1 == 'F' && c2 == 'M') {
//			return true;
//		}
//		if (c1 == 'M' && c2 == 'F') {
//			return true;
//		}
//		if (c1 == 'F' && c2 == 'F') {
//			return true;
//		}
//		return false;
//	}

}