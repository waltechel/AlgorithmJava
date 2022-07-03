import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main1658B_dbfldkfdbgml {

	private static int visited = 0;
	private static Stack<Integer> stack;
	private static int answer = 0;
	private static final int MAX = 10;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		stack = new Stack<>();
		answer = 0;
		for (int i = 1; i <= MAX; i++) {
			dfs(i);
			visited &= ~(1 << i);
			stack.pop();
		}
		System.out.println(answer);

		bw.flush();
		bw.close();
		br.close();

	}

	private static void dfs(int now) {
		visited |= (1 << now);
		stack.add(now);
		if (visited == (1 << MAX + 1) - 2) {
			long gcd = stack.get(0) * 1;
			for (int i = 1; i < MAX; i++) {
				gcd = gcd(gcd, stack.get(i) * (i + 1));
			}
			if (gcd > 1) {
				answer++;
			}
			return;
		}

		for (int next = 1; next <= MAX; next++) {
			if ((visited & (1 << next)) == 0) {
				dfs(next);
				stack.pop();
				visited &= ~(1 << next);
			}
		}

	}

	private static long gcd(long a, long b) {
		if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}

}
