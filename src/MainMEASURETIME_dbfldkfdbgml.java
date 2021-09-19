import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 세그트리로 구현함
 * 숫자를 받을 때마다 자기보다 더 큰 수의 개수를 세고
 * 해당 수의 카운트를 증가시켜주는 방식으로 세그트리를 구현함
 * 단순히 1을 업데이트 하면 안되는데, 동일한 수가 여러 개가 들어올 수 있으니,
 * 수의 빈도는 굳이 1이 아닐 수 있다.
 * 
 * @author leedongjun
 *
 */
public class MainMEASURETIME_dbfldkfdbgml {

	private static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0, N = 0; test_case < T; test_case++) {
			N = Integer.parseInt(br.readLine());
			int size = 1;
			while (size <= 1000000) {
				size *= 2;
			}
			int start = size;
			size *= 2;
			tree = new int[size];
			long answer = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0, num = 0; i < N; i++) {
				int A = Integer.parseInt(st.nextToken());
				num = query(start + A + 1, size - 1);
				update(start + A, tree[start + A] + 1);
				answer += num;
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void update(int i, int n) {
		tree[i] = n;
		i /= 2;
		while (i > 0) {
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