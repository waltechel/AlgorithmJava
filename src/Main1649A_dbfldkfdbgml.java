import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1649A_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				int cntOfZero = 0;
				for (int j = i - 1; j >= 0; j--) {
					if (A[j] == 0) {
						cntOfZero++;
					} else {
						if(j != i - 1) {
							answer += (cntOfZero + 1);							
						}
						break;
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