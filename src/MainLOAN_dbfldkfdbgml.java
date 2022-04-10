import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 대충 짜본 Kmeans 알고리즘
 * 
 * @author leedongjun
 *
 */
public class MainLOAN_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			double N, P;
			int M;
			st = new StringTokenizer(br.readLine());
			N = Double.parseDouble(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			P = Double.parseDouble(st.nextToken());
			
			double answer = 0;
			double left, right;
			left = 0;
			// 초깃값을 1억을 넘게 줘야 하는 것 같다.
			right = 100_000_000f * 1200;
			for(int i = 0 ; i < 1000 ; i++) {
				answer = (left + right) / 2;
				if(isOkay(answer, N, M, P)) {
					right = answer;
				}else {
					left = answer;
				}
			}
			
			bw.write(String.format("%.10f\n", answer));
			
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isOkay(double answer, double N, int M, double P) {
		for(int i = 0 ; i < M ; i++) {
			N += (N * P / 1200);
			N -= answer;
		}
		if(N < 0) {
			return true;
		}else {
			return false;
		}
	}

}