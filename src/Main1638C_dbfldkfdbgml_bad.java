import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 역위의 개수가 몇 개 있는지 묻는 문제 같지만 시간 초과남
 * 
 * @author leedongjun
 *
 */
public class Main1638C_dbfldkfdbgml_bad {

	private static int[] tree;
	private static int start = 100000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			tree = new int[200001];

			int answer = 0;
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				int a = query(start + n + 1, 200000);
				answer += a;
				update(start + n, 1);
				if (a > 0 && n < 100000) {
					update(start + n + 1, -1);
				}
			}
			bw.write(N - answer + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void update(int i, int n) {
		tree[i] += n;
		i /= 2;
		while (i > 1) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
			i /= 2;
		}
	}

	private static int query(int from, int to) {
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