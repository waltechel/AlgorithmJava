import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author developer
 * 8
 * 1 2 3 3 3 4 4 7
 * 1 3 3 3 2 4 4 7
 * 정답 : 1 3 2 4 3 3 7 4
 * 
 */
public class Main1071_dbfldkfdbgml_bad {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[2003];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[Integer.parseInt(st.nextToken())]++;
		}

		for (int i = 0; i <= 1000;) {
			if (A[i] > 0 && A[i + 1] > 0) { // 연속된 값이 있다 하면
				// 그 뒤에 숫자가 있을 때
				boolean find = false;
				int k = 2;
				for(; k < 1000; k++) {
					if(A[i + k] > 0) {
						find = true;
						break;
					}
				}
				if (find && k == 2) {
					for (int j = 0; j < A[i]; j++) {
						bw.write(i + " ");
					}
					for (int j = 0; j < A[i + k]; j++) {
						bw.write(i + k + " ");
					}
					for (int j = 0; j < A[i + 1]; j++) {
						bw.write(i + 1 + " ");
					}
					A[i] = 0;
					A[i + k] = 0;
					A[i + 1] = 0;
					i += k;
				} else if(find && k > 2) {
					for (int j = 0; j < A[i]; j++) {
						bw.write(i + " ");
					}
					for (int j = 0; j < 1; j++) {
						bw.write(i + k + " ");
					}
					for (int j = 0; j < A[i + 1]; j++) {
						bw.write(i + 1 + " ");
					}
					for (int j = 0; j < A[i + k] - 1; j++) {
						bw.write(i + k + " ");
					}
					A[i] = 0;
					A[i + k] = 0;
					A[i + 1] = 0;
					i += k;
				} else {
					for (int j = 0; j < A[i + 1]; j++) {
						bw.write(i + 1 + " ");
					}
					for (int j = 0; j < A[i]; j++) {
						bw.write(i + " ");
					}
					A[i] = 0;
					A[i + 1] = 0;
					i += 2;
				}
			} else {
				for (int j = 0; j < A[i]; j++) {
					bw.write(i + " ");
				}
				A[i] = 0;
				i += 1;
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

}