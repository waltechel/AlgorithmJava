import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * If max(a)⋅2≤sum(a), we can always prove that we can only use one ball.
 * For other cases, the number of balls is determined by 2⋅max(a)−sum(a).
 * @author leedongjun
 *
 */
public class Main1649B_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			int [] A = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			long sumA = 0;
			long maxA = 0;
			for(int i = 0 ; i < N ; i++) {
				sumA += A[i];
				maxA = Long.max(maxA, A[i]);
			}
			if(sumA == 0) {
				bw.write("0\n");
			}else 	if(maxA * 2 <= sumA) {
				bw.write("1\n");
			}else {
				bw.write(maxA * 2 - sumA + "\n");
			}
			
		}

		bw.flush();
		br.close();
		bw.close();
	}
}