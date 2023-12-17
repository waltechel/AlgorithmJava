import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1113 {
	
	private static final int [][] DIRECTIONS = new int [][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void main(String args[]) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N, M;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int [][] map = new int[N][M];
		int [][] dp = new int[N][M];
		for(int i = 0; i < N ; i++) {
			String line = br.readLine();
			for(int j = 0 ; j < M; j++) {
				map[i][j] = (int) (line.charAt(j) - '0');
				dp[i][j] = map[i][j];
			}
		}
		
		int answer= 0;
		boolean[][] fixed = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		for(int r = 1 ; r < N - 1; r++) {
			for(int c = 1 ; c < M - 1 ; c++) {
				if(fixed[r][c] == false) {
					for(int k = 9 ; k > map[r][c] ; k--) {
						for(int i = 0 ; i < N ; i++) {
							for(int j = 0 ; j < M ; j++) {
								dp[i][j] = map[i][j];
							}
						}
						queue.clear();
						dp[r][c] = k;
						queue.add(new int[] {r, c});
						
						boolean isOver = false;
						while(!queue.isEmpty() && isOver == false) {
							int [] now = queue.poll();
							for(int d = 0 ; d < DIRECTIONS.length ; d++) {
								int newRow = now[0] + DIRECTIONS[d][0];
								int newCol = now[1] + DIRECTIONS[d][1];
								if(newRow >= 0 && newRow < N && newCol >= 0 && newCol < M && dp[newRow][newCol] < k) {
									if((newRow == 0 || newRow == N - 1 || newCol == 0 || newCol == M - 1) && dp[newRow][newCol] < k) {
										isOver = true;
										break;
									}
									dp[newRow][newCol] = k;
									queue.add(new int[] {newRow, newCol});
								}
								
							}
						}
						if(!isOver) {
							// 여기까지는 안전하다. 값이 바뀐 것들은 답이 되는 것들이다.
							for(int i = 0 ; i < N ; i++) {
								for(int j = 0 ; j < M ; j++) {
									if(dp[i][j] != map[i][j]) {
										answer += (dp[i][j] - map[i][j]);
										fixed[i][j] = true;
									}
								}
							}
							break;
						}						
					}
				}
			}
		}
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(fixed[i][j]) {
					System.out.print(1);
				}else {
					System.out.print(0);
				}
			}
			System.out.println();
		}
		
		bw.write(answer + "");
	

		bw.flush();
		bw.close();
		br.close();
	}

}