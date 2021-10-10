import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        visited[0] = true;
        cost[0] = 1;

        while(!q.isEmpty()) {
            int now = q.poll();

            if (now == N - 1) return cost[now];

            int minComputer = -1;
            double minCost = INF;

            for (int next = 0; next < N; next++) {
                if (graph[now][next] == 0) continue; //not connected
                if (visited[next]) continue;

                double nextCost = cost[now] * graph[now][next];
                cost[next] = Math.min(nextCost, cost[next]);

                if (minCost > cost[next]) {
                    minCost = cost[next];
                    minComputer = next;
                }
            }

            if (minComputer != -1) {
                q.add(minComputer);
                visited[minComputer] = true;
            }
        }
        return -1.0;
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