import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainJOSEPHUS_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int C = Integer.parseInt(br.readLine());
		for(int test = 0 ; test < C ; test++) {
			st = new StringTokenizer(br.readLine());
			int N, K;
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Queue<Integer> queue = new LinkedList<Integer>();
			for(int i = 1; i <= N ; i++) {
				queue.add(i);
			}
			queue.poll();
			while(queue.size() > 2) {
				for(int i = 0 ; i < K - 1; i++) {
					queue.add(queue.poll());
				}
				queue.poll();
			}
			int a = queue.poll();
			int b = queue.poll();
			bw.write("" + Math.min(a, b) + " " + Math.max(a, b) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
