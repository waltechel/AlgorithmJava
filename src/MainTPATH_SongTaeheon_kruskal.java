import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MainTPATH_SongTaeheon_kruskal {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, N;
    static int[][] edges = new int[4000][3];

    static int[] parent = new int[2000];
    static int[] rank = new int[2000];

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }
    }

    private static void solve() {
        int result = Integer.MAX_VALUE;

        Arrays.sort(edges, 0, M, Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < edges.length; i++) {
            result = Math.min(result, minUpperBound(i) - edges[i][2]);
        }

        System.out.println(result);
    }

    private static int minUpperBound(int lowIndex) {
        initDisjointSet(N);

        int startPoint = 0;
        int endPoint = N - 1;

        // edges를 돈다. (edges는 cost로 정렬된 상태)
        for (int[] edge: edges) {

            int fromIndex = edge[0];
            int toIndex = edge[1];
            int cost = edge[2];

            if (edges[lowIndex][2] > cost) continue; //lower bound보다 작은 경우

            // 이미 같은 그룹인 경우 (연결하면 cycle 발생)
            if (findParent(fromIndex) == findParent(toIndex)) continue;

            merge(fromIndex, toIndex);

            if (findParent(startPoint) == findParent(endPoint)) return cost;
        }

        return Integer.MAX_VALUE;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 철도 수
        M = Integer.parseInt(st.nextToken()); // 연결 수


        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int cost = Integer.parseInt(st2.nextToken());
            edges[i] = new int[]{a, b, cost};
        }
    }

    static public int findParent(int v) {
        if (v == parent[v]) return v;
        parent[v] = findParent(parent[v]);
        return parent[v];
    }

    static public void merge(int a, int b) {
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

    static void initDisjointSet(int size) {
        for (int i = 0 ; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
}
