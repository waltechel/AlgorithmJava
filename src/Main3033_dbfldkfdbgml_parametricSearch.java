import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main3033_dbfldkfdbgml_parametricSearch {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int L = Integer.parseInt(br.readLine());
		String line = br.readLine();

		int answer = 0;
		int cri = 1;
		while (cri < line.length()) {
			cri *= 2;
		}
		for (int diff = cri; diff > 0; diff /= 2) {
			if(isSafe(line, answer + diff)) {
				answer += diff;
			}
		}
		
		bw.write(answer + "");
		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * line으로 문자열이 주어질 때, 길이 N인 문자열은 중복이 되나 안 되나를 판단한다.
	 * @param line 문자열
	 * @param N 길이
	 * @return 되는지 안되는지에 대한 판단
	 */
	private static boolean isSafe(String line, int N) {
		
		HashSet<Long> set = new HashSet<>();
		long hash = 0;
		int base = 31;
		long MOD = 99_999_999_999_999_997l;
		
		
		long multi = 1;
		for(int i = 0 ; i < N ; i++) {
			multi *= 31;
			multi %= MOD;
		}
		
		for(int i = 0 ; i < line.length() ; i++) {
			hash *= 31; 
			hash += (line.charAt(i) - 'a' + 1);
			hash %= MOD;
			if(i >= N) {
				hash -= (line.charAt(i - N) - 'a' + 1) * multi;
				hash += MOD;
				hash %= MOD;
			}
			if(set.contains(hash)) {
				return true;
			}
			set.add(hash);
		}
		
		return false;
	}

}