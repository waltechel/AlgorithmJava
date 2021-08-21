import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainITES_dbfldkfdbgml {
	
	private static final int SIZE = 5000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {
			int N, K;
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			long [] queue = new long[SIZE];
			int front = -1;
			int tail = -1;
			int answer = 0;
			for(long i = 1, A = 1983, sum = 0;  i < N ; i++) {
				queue[++front % SIZE] = A % 10000 + 1;
				front %= SIZE;
				sum += (A % 10000 + 1);
				if(sum >= K) {
					if(sum == K) {
						answer++;
					}
					sum -= queue[++tail % SIZE];
					tail %= SIZE;
					if(sum == K) {
						answer++;
					}
				}
				A = (A * 214013 + 2531011) % (1l << 32);
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	
}
