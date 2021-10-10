import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainFIRETRUCKS_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E;
    static int N, M;
    static int[][] graph;
    static int[] firedPlace;
    static int[] fireStation;

    static int[] cost;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            System.out.println(solve());
        }
    }

    private static int solve() {

        for (int i = 0; i < M; i++) {
            cost[fireStation[i]] = 0;
        }


        while(true) {

            int closest = INF;
            int here = -1;

            for (int i = 1; i <= V; i++) {
                if (cost[i] < closest && !visited[i]) {
                    closest = cost[i];
                    here = i;
                }
            }

            if (closest == INF) break;

            visited[here] = true;
            for (int next = 1; next <= V; next++) {
                if (graph[here][next] == 0) continue; //not connected
                if (visited[next]) continue;

                int nextCost = cost[here] + graph[here][next];
                cost[next] = Math.min(nextCost, cost[next]);
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            res += cost[firedPlace[i]];
        }
        return res;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[V + 1][V + 1];
        firedPlace = new int[N];
        fireStation = new int[M];
        cost = new int[V + 1];
        visited = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int cost = Integer.parseInt(st1.nextToken());

            graph[a][b] = cost;
            graph[b][a] = cost;
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int fired = Integer.parseInt(st2.nextToken());
            firedPlace[i] = fired;
        }

        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int station = Integer.parseInt(st3.nextToken());
            fireStation[i] = station;
        }

        Arrays.fill(cost, INF);
    }
}