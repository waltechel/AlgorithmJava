import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainITES_dbfldkfdbgml {

	private static final int SIZE = 5000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {
			int N, K;
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			long[] queue = new long[SIZE];
			int head = -1;
			int tail = -1;
			long M = 0;

			int answer = 0;
			long[] A = new long[2];
			A[0] = 1983;
			for (int i = 1; i <= N; i++) {
				long temp = ((A[(i - 1) % 2] % 10000) + 1);
				M += temp;
				queue[(++head) % SIZE] = temp;
				while (M >= K) {
					if (M == K) {
						answer++;
					}
					M -= queue[(++tail) % SIZE];
				}
				A[i % 2] = (A[(i - 1) % 2] * 214013l + 2531011) % (1l << 32);
			}

			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
