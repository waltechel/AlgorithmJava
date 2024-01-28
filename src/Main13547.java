import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main13547 {

	/*
	 * 수열과 쿼리 3과 비슷하게 푼되
	 * 1 1 2 1 3
	 * 을 
	 * 2 4 6 6 6(자기와 동일한 수가 나오는 인덱스)
	 */
	
	private static List<Integer>[] tree;
	private static int N;
	private static int [] A;
	private static int [] B;
	private static int [] C;
	
	public static void main(String args[]) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int size = 1;
		while (size <= N) {
			size *= 2;
		}
		int start = size;
		size *= 2;
		tree = new ArrayList[size];

		for (int i = 0; i < size; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		// B는 자기와 같은 수가 나오는 인덱스(없으면 최대의 숫자-N+1 찍기)
		// 1 1 2 1 3을
		// 2 4 6 6 6으로 만드는 로직
		B = new int[N];
		C = new int[1000001];
		for(int i = N - 1; i >= 0 ; i--) {
			int a = A[i];
			if(C[a] == 0) { // 이 수는 없는 것이므로 
				B[i] = N + 1;
				C[a] = i + 1;
			} else { // 이 수는 있는 것이므로
				B[i] = C[a];
				C[a] = i + 1;
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			update(start + i, B[i]);
		}
		for (int i = 0; i < start; i++) {
			if (tree[i].size() > 1) {
				tree[i] = sort(tree[i], 0, tree[i].size() - 1);
			}
		}
		int M = Integer.parseInt(br.readLine());
		for (int r = 0, last_ans = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int k = to;

			last_ans = query(start + from - 1, start + to - 1, k);
			bw.write(last_ans + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * list에서 k보다 작거나 같은 것들의 수
	 * 
	 * @param list
	 * @param k
	 * @return
	 */
	private static int calculate(List<Integer> list, int k) {

		if (list.size() == 0 || (list.size() > 0 && list.get(0) > k)) {
			return 0;
		}

		int ret_idx = 0;
		for (int i = list.size() / 2 * 2; i > 0; i /= 2) {
			if (ret_idx + i < list.size() && list.get(ret_idx + i) <= k) {
				ret_idx += i;
			}
		}
		if (ret_idx == 0) {
			if (list.get(0) <= k) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return ret_idx + 1;
		}

	}

	private static int query(int from, int to, int k) {

		int ret = 0;
		while (from <= to) {
			if (from % 2 == 1) {
				ret += tree[from].size() - calculate(tree[from], k);
				from++;
			}
			if (to % 2 == 0) {
				ret += tree[to].size() - calculate(tree[to], k);
				to--;
			}
			from /= 2;
			to /= 2;
		}
		return ret;
	}

	/**
	 * list 의 from~to를 오름차순으로 정리한 쿼리
	 * @param list
	 * @param from
	 * @param to
	 * @return
	 */
	private static List<Integer> sort(List<Integer> list, int from, int to) {

		List<Integer> ret = new ArrayList<Integer>();
		if (from >= to) {
			ret.add(list.get(to));
			return ret;
		}

		if (to - from == 1) {
			ret.add(Math.min(list.get(from), list.get(to)));
			ret.add(Math.max(list.get(from), list.get(to)));
			return ret;
		}

		List<Integer> left = sort(list, from, (from + to) / 2);
		List<Integer> right = sort(list, (from + to) / 2 + 1, to);
		int l = 0, r = 0;

		for (int i = 0; i < left.size() + right.size(); i++) {
			if (r >= right.size() || (l < left.size() && left.get(l) <= right.get(r))) {
				ret.add(left.get(l++));
			} else {
				ret.add(right.get(r++));
			}
		}

		return ret;
	}

	private static void update(int i, int n) {
		tree[i].add(n);
		i /= 2;
		while (i > 0) {
			tree[i].add(n);
			i /= 2;
		}
	}

}