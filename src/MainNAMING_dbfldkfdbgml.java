import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainNAMING_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		String S = sb.append(br.readLine()).append(br.readLine()).toString();

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= S.length(); i++) {
			String prefix = S.substring(0, i);
			String suffix = S.substring(S.length() - i);
			if (prefix.equals(suffix)) {
				list.add(i);
			}
		}

		for (Integer e : list) {
			bw.write(e + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}