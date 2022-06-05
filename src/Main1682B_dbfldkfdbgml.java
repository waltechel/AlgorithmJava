import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main1682B_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			int[] B = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = A[i];
			}
			int answer = 1;
			while (answer < N) {
				answer *= 2;
			}
			answer -= 1;

			Arrays.sort(B);
			for (int i = 0; i < N; i++) {
				if (A[i] != B[i]) {
					answer &= A[i];
				}
			}

			bw.write(answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}