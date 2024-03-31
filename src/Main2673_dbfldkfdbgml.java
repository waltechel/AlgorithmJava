import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 잘하면 완전탐색이 되지 않을까 하여 DFS로 접근 
 * 최대 2^50 가지 경우의 수가 있지만 가지치기를 사정없이 한다면 그리디하게 할 수 있지 않을까
 * 종만북의 알러지 문제(떡볶이 고르는 문제)와 비슷한 것 같기도 하다
 * 되긴 한다!
 * 
 * @author developer
 *
 */
public class Main2673_dbfldkfdbgml {

	private static int N;
	private static List<int[]> list;
	private static int answer;
	private static Stack<Integer> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int from = Math.min(a, b);
			int to = Math.max(a, b);
			list.add(new int[] { from, to });
		}

		stack = new Stack<>();
		answer = 0;
		
		// 첫 번째 선분은 무조건 고를 수 있다
		dfs(0, true);
		stack.pop();
		
		dfs(0, false);
		
		bw.write(answer + "");
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int nowIndex, boolean flag) {
		if(flag) {
			stack.add(nowIndex);
		}
		
		if(nowIndex == N - 1) {
			answer = Math.max(answer, stack.size());
			return;
		}
		
		// 만약에 체크할 수 있으면 체크를 하되, 체크를 하지 못하면 뒤로 넘어간다.
		int nextIndex = nowIndex + 1;
		int [] nextLine = list.get(nextIndex);
	
		boolean overlapped = false;
		for(int i = 0 ; i < stack.size() ; i++) {
			int checkedIndex = stack.get(i);
			int [] checkedLine = list.get(checkedIndex);
			if(overlapped(nextLine, checkedLine)) {
				overlapped = true;
				break;
			}
		}
		if(overlapped == false) {
			dfs(nextIndex, true);
			stack.pop();	
		}
		
		// 다음 것을 선택하지 않는다
		dfs(nextIndex, false);
		
	}

	private static boolean overlapped(int[] nextLine, int[] checkedLine) {
		boolean firstLeft = true;
		if(checkedLine[0] < nextLine[0] && nextLine[0] < checkedLine[1]) {
			firstLeft = true;
		}else {
			firstLeft = false;
		}
		
		boolean secondLeft = true;
		if(checkedLine[0] < nextLine[1] && nextLine[1] < checkedLine[1]) {
			secondLeft = true;
		}else {
			secondLeft = false;
		}
		if(firstLeft == true && secondLeft == true) {
			return false;
		}
		if(firstLeft == false && secondLeft == false) {
			return false;
		}
		return true;
	}

}
