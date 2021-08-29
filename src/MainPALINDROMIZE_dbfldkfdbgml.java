import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainPALINDROMIZE_dbfldkfdbgml {
	
	/*
	 * 전체문자열 S와 패턴문자열 P가 주어질 때
	 * S의 부분문자열 중에서 패턴문자열 P와 같은 것을 구하는 문제이다.
	 * 
	 * 0부터 S - 1까지의 시작 위치를 전부 시도하면서, P가 S의 남은 부분들과
	 * 전부 일치하는 위치를 찾는데, 첫 번째 찾은 위치가 가장 많이 겹치는 곳이 되겠다.
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < C; test_case++) {
			String S = br.readLine();
			String P = new StringBuilder(S).reverse().toString();
			int [] f = new int[P.length()];
			// cnt와 j가 사실 같은 수이므로 넣을 필요가 없다.
			for(int i = 1, j = 0; i < P.length();) {
				while(i < P.length() && j < P.length() && P.charAt(i) == P.charAt(j)) {
					f[i++] = ++j;
				}
				if(j == 0) {// 첫 글자에서 틀렸으면 S를 바로 다음으로 보낸다.
					i++;
				}else { // 첫 글자가 아니면 f[j - 1]로 보낸다.
					j = f[j - 1];	
				}
			}
			
			int answer = S.length() + P.length() ;
			for(int i = 0, j = 0; i < S.length() ; ) {
				while(i < S.length() && j < P.length() && S.charAt(i) == P.charAt(j)) {
					i++;
					j++;
					// 어찌되었든 끝까지 비교한 것이다. 
//					if(j == P.length() || i == S.length()) {
					// j를 비교할 필요가 사실 없다, 팰린드롬이므로 S의 끝까지만 가면 된다.
					if(i == S.length()) {
						answer -= j;
					}
				}
				if(j == 0) {// 첫 글자에서 틀렸으면 다음 글자로 보낸다.
					i++;
				}else {// 첫 글자가 아니면 f[i - 1]로 보낸다.
					j = f[j - 1];
				}
			}
			
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}