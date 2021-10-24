import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class MainLAN_SongTaeheon_kruskal_time_exceeds {
    private static final int INF = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, N;
    static int[] x;
    static int[] y;

    static double[][] adj;
    static boolean[][] selected;
    static InputConnected[] alreadyConnected;
    static DisjointSet disjointSet;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {

        double result = 0;

        selectConnectedEdges();

        // 1. 간선들을 저장 + 정렬 (selected는 제외)
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) { // j가 i보다 크게 하면, 간선이 중복 저장 안됨
                if (!selected[i][j]) {
                    list.add(new Edge(adj[i][j], i, j));
                }
            }
        }

        list.sort(Comparator.comparingDouble(o -> o.distance));

        // 2. 작은 간선부터 선택한다.
        for (Edge edge: list) {
            int fromParent = disjointSet.findParent(edge.fromIndex);
            int toParent = disjointSet.findParent(edge.toIndex);

            if (fromParent == toParent) continue; // 같은 그룹.

            disjointSet.merge(fromParent, toParent);
            result += adj[edge.fromIndex][edge.toIndex];
        }

        System.out.println(result);
    }

    private static void selectConnectedEdges() {
        for (InputConnected input: alreadyConnected) {
            int v1 = input.a;
            int v2 = input.b;
            selected[v1][v2] = true;
            selected[v2][v1] = true;

            disjointSet.merge(v1, v2);
        }
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 건물 수
        M = Integer.parseInt(st.nextToken()); // 이미 연결된 케이블 수

        adj = new double[N][N];
        selected = new boolean[N][N];
        alreadyConnected = new InputConnected[M];
        disjointSet = new DisjointSet(N);

        x = new int[N];
        y = new int[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st2.nextToken());
        }

        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            y[i] = Integer.parseInt(st3.nextToken());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st4 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st4.nextToken());
            int b = Integer.parseInt(st4.nextToken());
            alreadyConnected[i] = new InputConnected(a, b);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double distance = calcDistance(i, j);
                adj[i][j] = distance;
                adj[j][i] = distance;
            }
        }
    }

    private static double calcDistance(int i, int j) {
        return Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
    }

    static class Edge {
        double distance;
        int fromIndex;
        int toIndex;

        public Edge(double distance, int fromIndex, int toIndex) {
            this.distance = distance;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
    }
    
    static class DisjointSet {
        private int[] parent;
        private int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];

            for (int i = 0 ; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int findParent(int v) {
            if (v == parent[v]) return v;
            parent[v] = findParent(parent[v]);
            return parent[v];
        }

        public void merge(int a, int b) {
            int rootA = findParent(a);
            int rootB = findParent(b);

            if (rank[rootA] < rank[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            rank[rootA] += rank[rootB];
            parent[rootB] = rootA; // findParent할 때, 자식들도 다 바뀜!
        }
    }
    
    static class InputConnected {
        int a;
        int b;

        public InputConnected(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
