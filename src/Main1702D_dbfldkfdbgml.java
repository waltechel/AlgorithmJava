import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author leedongjun
 */
public class Main1702D_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			String w = br.readLine();
			int p = Integer.parseInt(br.readLine());

			Queue<Integer>[] list = new LinkedList[26];
			for (int i = 0; i < 26; i++) {
				list[i] = new LinkedList<>();
			}

			int sum = 0;
			for (int i = 0; i < w.length(); i++) {
				char c = w.charAt(i);
				int n = (int) (c - 'a');
				list[n].add(i);
				sum += (n + 1);
			}

			if (p >= sum) {
				bw.write(w + "\n");
				continue;
			}

			boolean[] checked = new boolean[w.length()];
			FOR : for (char c = 'z'; c >= 'a'; c--) {
				int n = (int) (c - 'a');
				while(!list[n].isEmpty()) {
					int index = list[n].poll();
					checked[index] = true;
					sum -= (n + 1);
					if(p >= sum) {
						break FOR;
					}
				}				
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < w.length() ; i++) {
				if(checked[i]) {
					continue;
				}
				sb.append(w.charAt(i));
			}
			bw.write(sb.toString() + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

}