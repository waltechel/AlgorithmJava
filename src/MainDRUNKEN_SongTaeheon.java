import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainDRUNKEN_SongTaeheon {
    final static int INF = 1_000_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E;
    static int[] vertex;
    static int[][] graph;

    static int startPoint, endPoint;

    static int[][] W;
    static int[][] distance;

    public static void main(String[] args) throws IOException {

        inputGraph();
        init();

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {
        System.out.println(W[startPoint][endPoint]);
    }

    private static void init() {
        W = new int[V][V];
        distance = new int[V][V];

        for (int i = 0; i < V; i++) {
            Arrays.fill(W[i], INF);
            Arrays.fill(distance[i], INF);
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                    W[i][j] = 0;
                } else {
                    distance[i][j] = graph[i][j];
                    W[i][j] = graph[i][j];
                }
            }
        }

        List<Node> order = new ArrayList<>();
        for (int i = 0; i < vertex.length; i++) {
            order.add(new Node(i, vertex[i]));
        }
        order.sort(Comparator.comparingInt(o -> o.time));

        for (int k = 0; k < V; k++) {
            int w = order.get(k).index; // 작은 거부터 경로에 넣는다. (이러면 w보다 큰 건 경로에 없음 -> w가 가장 큼) -> 출발 장소와 도착 장소에서는 단속을 안하기 때문에 가능
            for (int start = 0; start < V; start++) {
                for (int end = 0; end < V; end++) {
                    distance[start][end] = Math.min(distance[start][end], distance[start][w] + distance[w][end]);
                    W[start][end] = Math.min(W[start][end], distance[start][w] + distance[w][end] + vertex[w]);
                }
            }
        }
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void inputGraph() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        vertex = new int[V];
        graph = new int[V][V];

        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], INF);
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            vertex[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st3.nextToken()) - 1;
            int end = Integer.parseInt(st3.nextToken()) - 1;
            int value = Integer.parseInt(st3.nextToken());
            graph[start][end] = value;
            graph[end][start] = value;
        }
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        startPoint = Integer.parseInt(st.nextToken()) - 1;
        endPoint = Integer.parseInt(st.nextToken()) - 1;
    }

    private static class Node {
        int index;
        int time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
