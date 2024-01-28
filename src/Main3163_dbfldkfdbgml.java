import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main3163_dbfldkfdbgml {

	private static int L, N, K;
	private static Ant A[];
	private static Falling B[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int test = 0 ; test < T ; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			A = new Ant[N + 1];
			B = new Falling[N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				char dir = 'L';
				if (a < 0) {
					dir = 'L';
				} else {
					dir = 'R';
				}
				A[i] = new Ant(p, a, dir);
				
				if (dir == 'L') {
					B[i] = new Falling(p, A[i], dir);
				} else {
					B[i] = new Falling(L - p, A[i], dir);
				}
			}

			// 사실 이거 정렬 안해도 된다
			Arrays.sort(A, 1, N + 1, new Comparator<Ant>() {
				@Override
				public int compare(Ant a, Ant b) {
					if(a.p != b.p) {
						return a.p - b.p;						
					}else {
						return a.a - b.a;
					}
				}

			});
			Arrays.sort(B, 1, N + 1, new Comparator<Falling>() {
				@Override
				public int compare(Falling a, Falling b) {
					return a.time - b.time;
				}
			});

			int l = 1, r = N;
			for (int i = 1; i < K; i++) {
				if (B[i].dir == 'L') {
					l++;
				} else {
					r--;
				}
			}
			
			int criFalling = B[K].time;
			int anotherIndex = -1;
			for(int i = 1; i <= N ; i++) {
				if(B[i].time == criFalling && i != K) {
					anotherIndex = i;
				}
			}
			
			int answer = 0;
			if(anotherIndex == -1) {
				if (B[K].dir == 'L') {
					answer = A[l].a;
				} else {
					answer = A[r].a;
				}	
			}else {
				int l2 = 1, r2 = N;
				for (int i = 1; i < anotherIndex; i++) {
					if (B[i].dir == 'L') {
						l2++;
					} else {
						r2--;
					}
				}
				
				int candi1 = 0;
				if (B[anotherIndex].dir == 'L') {
					candi1 = A[l2].a;
				} else {
					candi1 = A[r2].a;
				}	
				int candi2 = 0;
				if (B[K].dir == 'L') {
					candi2 = A[l].a;
				} else {
					candi2 = A[r].a;
				}	
				
				if(anotherIndex > K) {
					answer = Math.min(candi1, candi2);
				}else {
					answer = Math.max(candi1, candi2);
				}
				
			}
			
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static class Ant {

		int p, a;
		char dir;

		public Ant(int p, int a, char dir) {
			super();
			this.p = p;
			this.a = a;
			this.dir = dir;
		}

	}

	private static class Falling {
		int time;
		Ant ant;
		char dir;

		public Falling(int time, Ant ant, char dir) {
			super();
			this.time = time;
			this.ant = ant;
			this.dir = dir;
		}

	}

}
