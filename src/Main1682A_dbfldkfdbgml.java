import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main1682A_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			String line = br.readLine();
			if (N % 2 == 1) {
				int answer = 1;
				int cri = N / 2;
				int right = N / 2 + 1;
				int left = N / 2 - 1;
				while (left >= 0) {
					if (line.charAt(cri) == line.charAt(left) && line.charAt(left--) == line.charAt(right++)) {
						answer += 2;
					} else {
						break;
					}
				}
				bw.write(answer + "\n");
			} else {
				int answer = 0;
				int cri = N / 2;
				int right = N / 2;
				int left = N / 2 - 1;
				while (left >= 0) {
					if (line.charAt(cri) == line.charAt(left) && line.charAt(left--) == line.charAt(right++)) {
						answer += 2;
					} else {
						break;
					}
				}
				bw.write(answer + "\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

}