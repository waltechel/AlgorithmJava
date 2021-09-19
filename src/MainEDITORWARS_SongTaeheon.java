import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class MainEDITORWARS_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static boolean[] isAck;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }
    }

    private static void solve() {
        UnionFind unionFind = new UnionFind(N);
        boolean isPossible = true;

        int index;
        for (index = 0; index < M; index++) {
            if (isAck[index]) {
                isPossible = unionFind.ack(a[index], b[index]);

            } else {
                isPossible = unionFind.dis(a[index], b[index]);
            }

            if (!isPossible) break;
        }

        if (isPossible) {
            int result = findMaxParty(unionFind);
            System.out.println("MAX PARTY SIZE IS " + result);
        } else {
            System.out.println("CONTRADICTION AT " + (index + 1));
        }

    }

    private static int findMaxParty(UnionFind unionFind) {
        int ret = 0;
        for (int node = 0; node < N; node++) {
            if (unionFind.parent[node] == node) {
                int enemy = unionFind.enemy[node];

                // 같은 모임 쌍을 두 번 세지 않기 위해, enemy < node 인 경우만 센다.
                // enemy == -1인 경우도 정확히 한 번씩 세게 된다.
                if (enemy > node) continue;
                int mySize = unionFind.size[node];
                int enemySize = enemy == -1 ? 0 : unionFind.size[enemy];

                ret += Math.max(mySize, enemySize);
            }
        }
        return ret;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N
        M = Integer.parseInt(st.nextToken()); // M

        isAck = new boolean[M];
        a = new int[M];
        b = new int[M];

        for (int i = 0; i < M; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            String div  = st1.nextToken();
            isAck[i] = Objects.equals(div, "ACK");
            a[i] = Integer.parseInt(st1.nextToken());
            b[i] = Integer.parseInt(st1.nextToken());
        }
    }


    private static class UnionFind {
        int[] parent;
        int[] enemy;
        int[] size;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            enemy = new int[n];
            size = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            Arrays.fill(enemy, -1);
            Arrays.fill(rank, 0);
            Arrays.fill(size, 1);
        }

        int find(int u) {
            if (parent[u] == u) return u;
            return parent[u] = find(parent[u]);
        }

        // 둘 다 -1이면 -1이 나감!
        int merge(int u, int v) {
            if (u == -1 || v == -1)  return Math.max(u, v); // enemy 때문에 -1이 나오는 경우!

            u = find(u);
            v = find(v);

            if (u == v) return u;
            if (rank[u] > rank[v]) {
                int temp = v;
                v = u;
                u = temp;
            }

            if (rank[u] == rank[v]) rank[v]++;
            parent[u] = v;
            size[v] += size[u];
            return v;
        }

        boolean dis(int u, int v) {
            u = find(u);
            v = find(v);

            if (u == v) return false;

            int a = merge(u, enemy[v]);
            int b = merge(v, enemy[u]);
            enemy[a] = b;
            enemy[b] = a;
            return true;
        }

        boolean ack(int u, int v) {
            u = find(u);
            v = find(v);


            if (enemy[u] == v) return false;
//            if (enemy[v] == u) return false; // 이건 안해도 되나?

            int a = merge(u, v);
            int b = merge(enemy[u], enemy[v]);
            enemy[a] = b;

            if (b != -1)
                enemy[b] = a;
            return true;
        }
    }
}
