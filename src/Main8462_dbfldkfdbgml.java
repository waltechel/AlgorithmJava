import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
<<<<<<< HEAD
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
=======
import java.util.Comparator;
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

		// 쿼리를 받되 몫으로 정렬하고 끝점으로 정렬한다
		Query[] queries = new Query[T];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			queries[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(queries, new Comparator<Query>() {
			@Override
			public int compare(Query o1, Query o2) {
				if (o1.compFactor == o2.compFactor) {
					return o1.to - o2.to;
				} else {
					return o1.compFactor - o2.compFactor;
				}
			}
		});
		
		// 첫 번째 쿼리부터 순차적으로 처리하기 위해 첫 번째 쿼리는 모두 계산해준다
		for (int i = queries[0].from; i <= queries[0].to; i++) {
			push(A[i]);
		}

		long[] answers = new long[T];
		answers[queries[0].idx] = answer;
		int prevFrom = queries[0].from;
		int prevTo = queries[0].to;
		for (int i = 1; i < T; i++) {
			Query nowQuery = queries[i];
			// 새로운 쿼리가 이전 쿼리보다 시작이 작으면 더한다
			for (int x = nowQuery.from; x < prevFrom; x++) {
				push(A[x]);
			}
			// 새로운 쿼리가 이전 쿼리보다 끝이 크면 더한다
			for (int x = prevTo + 1; x <= nowQuery.to; x++) {
				push(A[x]);
			}
			// 새로운 쿼리가 이전 쿼리보다 시작이 크면 뺀다
			for (int x = prevFrom; x < nowQuery.from; x++) {
				pop(A[x]);
			}
			// 새로운 쿼리가 이전 쿼리보다 끝이 작으면 뺀다
			for (int x = nowQuery.to + 1; x <= prevTo; x++) {
				pop(A[x]);
			}
			// 예전 것들을 재활용한다
			prevFrom = nowQuery.from;
			prevTo = nowQuery.to;
			answers[nowQuery.idx] = answer;
		}
		for (int i = 0; i < T; i++) {
			bw.write(answers[i] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static class Query  {
		int from, to, idx, compFactor;
		static int sqrtN;

		public static void setSqrtN(int n) {
			sqrtN = (int) Math.sqrt(n);
		}

		public Query(int from, int to, int idx) {
			this.from = from;
			this.to = to;
			this.idx = idx;
			this.compFactor = this.from / sqrtN;
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
>>>>>>> branch 'master' of https://github.com/waltechel/AlgorithmStudyJava.git
