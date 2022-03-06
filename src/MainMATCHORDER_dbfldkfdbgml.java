import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * 상대와 나를 비교해서
 * 상대보다 크거나 같은 가장 작은 lower_bound를 찾아서 리턴
 * @author leedongjun
 *
 */
public class MainMATCHORDER_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int[] B = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}

			int answer = 0;
			boolean[] checked = new boolean[N];
			for (int i = 0; i < N; i++) {
				int a = A[i];
				int candi = Integer.MAX_VALUE;
				int candi_index = -1;
				for (int j = 0; j < N; j++) {
					if (checked[j]) {
						continue;
					}
					int b = B[j];
					if (a <= b && b < candi) {
						candi_index = j;
						candi = b;
					}
				}
				if (candi_index != -1) {
					checked[candi_index] = true;
					answer++;
				}
			}

			bw.write(answer + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

}