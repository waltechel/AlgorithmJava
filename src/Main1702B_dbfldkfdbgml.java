import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 */
public class Main1702B_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			String s = br.readLine();

			int answer = 0;
			int nextIndex = 0;
			while (nextIndex < s.length()) {
				answer++;
				HashSet<Character> set = new HashSet<>();
				for (int i = nextIndex; i <= s.length(); i++) {
					if (i == s.length()) {
						nextIndex = i;
						break;
					}
					if (set.size() <= 3 && set.contains(s.charAt(i))) {
						continue;
					}
					if (!set.contains(s.charAt(i))) {
						if (set.size() < 3) {
							set.add(s.charAt(i));
						} else {
							nextIndex = i;
							break;
						}
					}
				}
			}

			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}