import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainTRAVERSAL_dbfldkfdbgml {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] preOrder;
	static int[] inOrder;

	public static void main(String[] args) throws Exception {
		StringTokenizer stringTokenizer;
		int C = Integer.parseInt(br.readLine());
		for (int test = 0; test < C; test++) {
			int N = Integer.parseInt(br.readLine());
			stringTokenizer = new StringTokenizer(br.readLine());
			preOrder = new int[N];
			for (int i = 0; i < N; i++) {
				preOrder[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			stringTokenizer = new StringTokenizer(br.readLine());
			inOrder = new int[N];
			for (int i = 0; i < N; i++) {
				inOrder[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			dfs(0, N - 1, 0);
			bw.write("\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int fromIndex, int toIndex, int rootIndex) throws Exception{
		
		if(toIndex < fromIndex) {
			return;
		}
		
		// 더 이상 좌우로 갈 수 없을 때 그대로 출력하고 리턴함.
		if(fromIndex == toIndex) {
			bw.write(preOrder[rootIndex] + " ");
			return;
		}
		
		/*
		 * 케이스를 쪼갬
		 */
		for (int i = fromIndex; i <= toIndex; i++) {
			if (inOrder[i] == preOrder[rootIndex]) {
				// 맨 처음에서 루트를 찾았을 때
				if(i == fromIndex) {
					dfs(fromIndex + 1, toIndex, rootIndex + 1);
				// 맨 끝에서 루트를 찾았을 때
				}else if(i == toIndex) {
					dfs(fromIndex, i - 1, rootIndex + 1);
				// 중간에서 루트를 찾았을 때	
				}else {
					dfs(fromIndex, i - 1, rootIndex + 1);
					dfs(i + 1, toIndex, rootIndex + (i - fromIndex) + 1);					
				}
				bw.write(preOrder[rootIndex] + " ");
				break;
			}
		}

	}

}