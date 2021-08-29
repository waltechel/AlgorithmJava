import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainJAEHASAFE_dbfldkfdbgml2 {

	/*
	 * KMP 알고리즘은 f fail 함수에서 다음과 같이 정의되는 f 배열을 구해냅니다. 
	 * f[i] : S[...i] 의 접두사도 되고 접미사도 되는 문자열의 최대 길이 
	 * : 최대한 많이 부분 일치하는 테이블의 값 
	 * : KMP의 핵심은, 어찌되었든지 간에 S의 길이에만 비례하게 소스가 구현되어야 한다.
	 * 
	 * 재하의 금고(P.658 쪽) 환형 시프트를 구현할 생각하지 말고, original String을 두 번 붙인 문자열에서 P를 찾는 것이다.
	 * 시계 방향으로 돌리는 것은 쉽게 구할 수가 있는데, 
	 * 반시계 방향으로 돌리는 것은 어떻게 구할 수가 있는가 
	 * S를 가지고 P + P 에서 가장 빠른 매치를 찾는 것이 시계 방향 
	 * P를 가지고 S + S 에서 가장 빠른 매치를 찾는 것이 반시계 방향
	 * 
	 * 다른 아이디어
	 * 1. 시계방향일 때는 맨 처음에 나오는 것, 그렇다면 반시계 방향은 맨 나중에 나오는 것
	 * 2. 아예 겹치는 경우의 수를 다 가져온 다음에 거기서 가장 작은 tick을 뽑는다. 
	 *
	 */


	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {

			int N = Integer.parseInt(br.readLine());
			String [] lines = new String[N + 1];
			for(int i = 0 ; i <= N ; i++) {
				lines[i] = br.readLine();
			}
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0) {
					String P = lines[i];
					String S = lines[i + 1];
					answer += kmp(P, S + S);
				} else {
					String P = lines[i];
					String S = lines[i + 1];
					answer += kmp(S, P + P);
				}
				
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int kmp(String P, String S) {

		int[] f = new int[P.length()];
		for (int i = 1, j = 0; i < P.length();) {
			while (i < P.length() && j < P.length() && P.charAt(i) == P.charAt(j)) {
				f[i++] = ++j;
			}
			if (j == 0) {
				i++;
			} else {
				j = f[j - 1];
			}
		}

		int answer = 0;
		for (int i = 0, j = 0; i < S.length();) {
			while (i < S.length() && j < P.length() && S.charAt(i) == P.charAt(j)) {
				i++;
				j++;
				// 처음 나오는 것이 정답이므로
				// 시계방향/반시계방향을 풀 수 있을 것 같은데 쉽지는 않아 보인다.
				if (j == P.length()) {
					answer = i - P.length();
					return answer;
				}
			}
			if (j == 0) {
				i++;
			} else {
				j = f[j - 1];
			}
		}
		return answer;
	}

}