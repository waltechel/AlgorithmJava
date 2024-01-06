import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 */
public class Main1702A_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			String s = br.readLine();
			String answer = "";
			if (isOkay(s)) {
				answer = "0";
			} else {
				answer = s.charAt(0) - '1' + s.substring(1);
				answer = Integer.parseInt(answer) + "";
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean isOkay(String s) {
		int n = Integer.parseInt(s);

		if (n == 1) {
			return true;
		}
		if (n == 10) {
			return true;
		}
		if (n == 100) {
			return true;
		}
		if (n == 1000) {
			return true;
		}
		if (n == 10000) {
			return true;
		}
		if (n == 100000) {
			return true;
		}
		if (n == 1000000) {
			return true;
		}
		if (n == 10000000) {
			return true;
		}
		if (n == 100000000) {
			return true;
		}
		if (n == 1000000000) {
			return true;
		}
		return false;
	}

}