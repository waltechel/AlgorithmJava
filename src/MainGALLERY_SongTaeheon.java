import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MainGALLERY_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int g, h;
    static int[][] graph;
    static boolean[] visited;
    static int installed;

    static final int UNWATCHED = 0;
    static final int WATCHED = 1;
    static final int INSTALLED = 2;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
           input();
           solve();
        }

    }

    private static void solve() {
        for (int i = 0; i < g; i++) {
            if (!visited[i]) {
                int state = dfs(i);
                if (state == UNWATCHED) {
                    installed += 1;
                }
            }
        }

        System.out.println(installed);
    }

    private static int dfs(int now) {
        visited[now] = true;

        int[] children = new int[3]; // unwatched, watched, installed의 개수

        for (int i = 0; i < g; i++) {
            if (graph[now][i] == 0) continue; // 연결 안됨
            if (visited[i]) continue;

            int state = dfs(i);
            children[state] += 1;
        }

        if (children[UNWATCHED] != 0) {
            installed += 1;
            return INSTALLED;
        }

        if (children[INSTALLED] != 0) return WATCHED;

        return UNWATCHED;
    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        g = Integer.parseInt(st.nextToken()); // g
        h = Integer.parseInt(st.nextToken()); // h

        graph = new int[g][g];
        visited = new boolean[g];
        installed = 0;

        for (int i = 0; i < h; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int gallery1 = Integer.parseInt(st1.nextToken());
            int gallery2 = Integer.parseInt(st1.nextToken());

            graph[gallery1][gallery2] = 1;
            graph[gallery2][gallery1] = 1;
        }
    }
}