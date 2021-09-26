import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class MainArrayDifferentiation_dbfldkfdbgml {
 
	private static int N;
	private static boolean isFind;
	private static int sum;
	private static int[] A;
	private static int visited;
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			N = Integer.parseInt(br.readLine());
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			// thus. there must be an nonempty subset {T1, T2 , ... Tm}
			// ⊆ { 1, 2, ... , n }
			// and a choice of signs S1, ... Sm(Si ∈ {+1, -1})
			// S1A[T1] + S2A[T2] + ... SnA[Tn] = 0;
			isFind = false;
			sum = 0;
			visited = 0;
			for (int i = 0; i < N; i++) {
				dfs(0, i, -1);
				sum += A[i];
				visited &= ~(1 << i);
				dfs(0, i, 1);
				sum -= A[i];
				visited &= ~(1 << i);
			}
			if (isFind) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}
		}
 
		bw.flush();
		bw.close();
		br.close();
	}
 
	/**
	 * phase 번째 페이스에서 i 번째 수를 flag 상태에 따라 더하고 뺌
	 * 
	 * @param phase
	 * @param index
	 * @param flag
	 */
	private static void dfs(int phase, int index, int flag) {
		sum += (A[index] * flag);
		visited |= (1 << index);
		if (sum == 0) {
			isFind = true;
		}
		if (phase == N - 1 || isFind) {
			return;
		}
		for (int i = index + 1; i < N; i++) {
			if((visited & (1 << i)) != 0) {
				continue;
			}
			dfs(phase + 1, i, -1);
			sum += A[i];
			visited &= ~(1 << i);
			dfs(phase + 1, i, 1);
			sum -= A[i];
			visited &= ~(1 << i);
		}
	}
}
 