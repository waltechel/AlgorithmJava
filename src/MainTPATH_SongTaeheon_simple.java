import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainTPATH_SongTaeheon_simple {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, N;
    static List<List<int[]>> graph;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {

        Arrays.sort(edges, Comparator.comparingInt(o -> o.cost));

        int result = Integer.MAX_VALUE;

        if (N == 2 && edges.length == 1) {
            System.out.println(0);
            return;
        }

        int maxHigh = 1;

        for (int lo = 0; lo < M; lo++) {

            boolean hasResult = false;

            for (int hi = maxHigh; hi < M; hi++) {
                if (hasPath(lo, hi)) {
                    result = Math.min(result, edges[hi].cost - edges[lo].cost);
                    maxHigh = hi;
                    hasResult = true;
                    break;
                }
            }

            if (!hasResult) break; // 상한을 최대로 높여도 없음!
        }

        System.out.println(result);
    }

    private static boolean hasPath(int lo, int hi) {

        boolean[] visited = new boolean[N];
        int start = 0;
        int end = N - 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            if (now == end) return true;

            for (int[] next: graph.get(now)) {
                int nextV = next[0];
                int cost = next[1];

                if (visited[nextV]) continue;
                if (cost < edges[lo].cost || cost > edges[hi].cost) continue;

                q.add(nextV);
                visited[nextV] = true;
            }
        }
        return false;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 철도 수
        M = Integer.parseInt(st.nextToken()); // 연결 수

        edges = new Edge[M];

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());

            int cost = Integer.parseInt(st2.nextToken());
            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
            edges[i] = new Edge(a, b, cost);
        }
    }

    static class Edge {
        int fromIndex;
        int toIndex;
        int cost;

        public Edge(int fromIndex, int toIndex, int cost) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.cost = cost;
        }
    }
}
