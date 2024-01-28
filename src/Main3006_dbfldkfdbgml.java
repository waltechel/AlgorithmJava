import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3006_dbfldkfdbgml {

	private static int[] tree;
	private static int[] B;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int size = 1;
		while (size <= N) {
			size *= 2;
		}
		int start = size;
		size *= 2;

		B = new int[N + 1];
		tree = new int[size];
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			B[n] = i;
			update(start + i, 1);
		}

		for (int i = 1; i <= (N + 1) / 2; i++) {
			int index = B[i];
			int answer = sum(start, start + index - 1);
			bw.write(answer + "\n");
			update(start + index, 0);

			if (i <= N / 2) {
				index = B[N + 1 - i];
				answer = sum(start + index + 1, size - 1);
				bw.write(answer + "\n");
				update(start + index, 0);
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void update(int i, int n) {
		tree[i] = n;
		i /= 2;
		while (i > 1) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
			i /= 2;
		}
	}

	private static int sum(int from, int to) {
		int ret = 0;
		while (from <= to) {
			if (from % 2 == 1) {
				ret += tree[from++];
			}
			if (to % 2 == 0) {
				ret += tree[to--];
			}
			from /= 2;
			to /= 2;
		}
		return ret;
	}
}
