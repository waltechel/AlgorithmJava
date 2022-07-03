import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1658A_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int n = Integer.parseInt(br.readLine());
			String line = br.readLine();
			int answer = 0;
			for (int i = 0; i < line.length() - 1; i++) {
				if (line.charAt(i) == line.charAt(i + 1) && line.charAt(i) == '0') {
					answer += 2;
				}

			}
			for (int i = 0; i < line.length() - 2; i++) {
				if (line.charAt(i) == '0' && line.charAt(i + 1) == '1' && line.charAt(i + 2) == '0') {
					answer += 1;
				}
			}
			
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

}
