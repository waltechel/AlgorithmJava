import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainWILDCARD_dbfldkfdbgml {

	private static String pattern, line;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			pattern = br.readLine();
			int N = Integer.parseInt(br.readLine());
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				line = br.readLine();
				boolean ret = matching(0, 0);
				if (ret) {
					list.add(line);
				}
			}
			Collections.sort(list);
			for (String s : list) {
				bw.write(s + "\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

	/** 
	 * 시행 착오를 많이 해보고 그냥 만듦
	 * @param i pattern의 인덱스
	 * @param j line의 인덱스
	 * @return 이중에 하나라도 매칭이 되면 성공
	 */
	private static boolean matching(int i, int j) {
		boolean ret = false;
		// 끝 글자에 왔다
		if (i == pattern.length() - 1 && j == line.length() - 1) {
			// 별 문자면 통과
			if (pattern.charAt(i) == '*') {
				return true;
			// 와일드카드면 통과
			} else if (pattern.charAt(i) == '?') {
				return true;
			// 일치하면 통과
			} else if (pattern.charAt(i) == line.charAt(j)) {
				return true;
			}
			// 아니면 false
			return false;
		}
		// 끝 글자를 넘을 수 있는데 이러면 무조건 통과
		if (i >= pattern.length() && j >= line.length()) {
			return true;
		}
		// 패턴은 다 읽었는데도 글자가 남았으면 false
		if (i == pattern.length() && j < line.length()) {
			return false;
		}
		// 패턴이 마지막 글자, 문자는 다 읽었음
		if (i == pattern.length() - 1 && j <= line.length()) {
			// 별 문자면 통과
			if (pattern.charAt(i) == '*') {
				return true;
			// 아니면 말고
			} else {
				return false;
			}
		}

		// 지금 별 문자임
		if (pattern.charAt(i) == '*') {
			// 끝까지 읽어봄
			for (int next = j; next < line.length(); next++) {
				ret |= matching(i + 1, next);
			}
		// 와일드카드면 그냥 통과
		} else if (pattern.charAt(i) == '?') {
			ret |= matching(i + 1, j + 1);
		// 문자 일치하는 경우
		} else if (i < pattern.length()) {
			if (j < line.length() && pattern.charAt(i) == line.charAt(j)) {
				ret |= matching(i + 1, j + 1);
			}
		} else {
			ret |= false;
		}

		return ret;
	}

}