import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainROUTING_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static double[][] graph;
    static double[] cost;
    static boolean[] visited;
    static final double INF = Double.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            System.out.println(solve());
        }
    }

    private static double solve() {

        cost[0] = 1;

        while(true) {

            double closest = INF;
            int here = 0;

            for (int i = 0; i < N; i++) {
                if (cost[i] < closest && !visited[i]) {
                    closest = cost[i];
                    here = i;
                }
            }

            if (closest == INF) break;

            visited[here] = true;
            for (int next = 0; next < N; next++) {
                if (graph[here][next] == 0) continue; //not connected
                if (visited[next]) continue;

                double nextCost = cost[here] * graph[here][next];
                cost[next] = Math.min(nextCost, cost[next]);
            }
        }
        return cost[N - 1];
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N
        M = Integer.parseInt(st.nextToken()); // M

        graph = new double[N][N];
        visited = new boolean[N];
        cost = new double[N];

        for (int i = 0; i < M; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            double value = Double.parseDouble(st1.nextToken());

            graph[a][b] = value;
            graph[b][a] = value;
        }

        Arrays.fill(cost, INF);
    }
}