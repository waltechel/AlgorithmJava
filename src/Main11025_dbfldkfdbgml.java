import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main11025_dbfldkfdbgml {
	
	/**
	 * https://rapun7el.tistory.com/302 블로그 보고 품
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = (K + answer) % i;
		}
		bw.write(answer + 1 + "");

		bw.flush();
		br.close();
		bw.close();
	}

}