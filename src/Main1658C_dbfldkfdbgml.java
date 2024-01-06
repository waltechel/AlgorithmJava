import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * if there exists a position i such that Ci+1 - Ci > 1, the answer is NO;
 * 1부터 앞으로 설정해야 하는 이유는 1에서부터 체크해야 체크하는 경우의 수가 줄어든다.
 * 요 문제도 열심히 잘 뚫어지게 관찰하면 풀 수가 있었다.
 * a1, 1, a3, a4 라면 두 가지 경우의 수를 방향체크 해야 했었다.
 * 1이 가장 작은 수라는 것을 알아야 한다.
 * 1이 가장 오른쪽으로 가면 오른쪽과 왼쪽 차이도 구해야 한다.
 * 
 * 로테이션 돌려보면 Ci, Ci+1 간의 차는 1 초과 나지 않는다. 
 * @author developer
 *
 */
public class Main1658C_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[] C = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				C[i] = Integer.parseInt(st.nextToken());
			}

			int cntOfOne = 0;
			for (int i = 0; i < N; i++) {
				if (C[i] == 1) {
					cntOfOne++;
				}
			}
			if (cntOfOne != 1) {
				bw.write("NO\n");
				continue;
			}

			// 1이 있는 곳으로 돌려야 하는 이유는 모르겠다.
			ArrayList<Integer> list = new ArrayList<>();
			int index = -1;
			for (int i = 0, flag = 0; i < N; i++) {
				if (C[i] == 1 || flag == 1) {
					list.add(C[i]);
					flag = 1;
				}
				if (C[i] == 1) {
					index = i;
				}
			}
			for (int i = 0; i < index; i++) {
				list.add(C[i]);
			}
			for (int i = 0; i < N; i++) {
				C[i] = list.get(i);
			}

			boolean flag = true;
			for (int i = 0; i < N - 1; i++) {
				if (C[i + 1] - C[i] > 1) {
					flag = false;
				}
			}
			if (flag == false) {
				bw.write("NO\n");
				continue;
			} else {
				bw.write("YES\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();

	}

}
