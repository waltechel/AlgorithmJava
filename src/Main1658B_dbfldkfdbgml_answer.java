import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main1658B_dbfldkfdbgml_answer {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		long MOD = 998_244_353;
		long[] dp = new long[1001];
		for(int i = 1; i <= 1000 ; i++) {
			if(i % 2 == 0) {
				dp[i] = facto(i / 2, MOD) * facto(i / 2, MOD) % MOD;				
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int n = Integer.parseInt(br.readLine());
			bw.write(dp[n] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static long facto(long i, long MOD) {
		if(i == 1) {
			return 1;
		}
		if(i == 2) {
			return 2;
		}
		return i * facto(i - 1, MOD) % MOD;
	}

}
