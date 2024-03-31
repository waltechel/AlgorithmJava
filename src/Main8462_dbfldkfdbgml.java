import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://nahwasa.com/entry/%EC%9E%90%EB%B0%94-%EB%B0%B1%EC%A4%80-8462-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%9E%98-boj-java
 * 위 사이트에서 소스코드 변형
 */
public class Main8462_dbfldkfdbgml {

	private static int N, T;
	private static int[] cnt = new int[1000001];
	private static long answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		Query.setSqrtN(N);
		int[] A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Query[] queries = new Query[T];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			queries[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(queries);
		for (int i = queries[0].a; i <= queries[0].b; i++) {
			push(A[i]);
		}

		long[] answers = new long[T];
		answers[queries[0].idx] = answer;
		int a = queries[0].a;
		int b = queries[0].b;
		for (int i = 1; i < T; i++) {
			Query q = queries[i];
			for (int x = q.a; x < a; x++) {
				push(A[x]);
			}
			for (int x = b + 1; x <= q.b; x++) {
				push(A[x]);
			}
			for (int x = a; x < q.a; x++) {
				pop(A[x]);
			}
			for (int x = q.b + 1; x <= b; x++) {
				pop(A[x]);
			}
			a = q.a;
			b = q.b;
			answers[q.idx] = answer;
		}
		for (int i = 0; i < T; i++) {
			bw.write(answers[i] + "\n");			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Query implements Comparable<Query> {
		int a, b, idx, compFactor;
		static int sqrtN;

		public static void setSqrtN(int n) {
			sqrtN = (int) Math.sqrt(n);
		}

		public Query(int a, int b, int idx) {
			this.a = a;
			this.b = b;
			this.idx = idx;
			this.compFactor = this.a / sqrtN;
		}

		@Override
		public int compareTo(Query o) {
			if (this.compFactor == o.compFactor)
				return this.b - o.b;
			return this.compFactor - o.compFactor;
		}
	}

	private static void push(int num) {
		answer -= 1l * num * cnt[num] * cnt[num];
		answer += 1l * num * ++cnt[num] * cnt[num];
	}

	private static void pop(int num) {
		answer -= 1l * num * cnt[num] * cnt[num];
		answer += 1l * num * --cnt[num] * cnt[num];
	}
}
